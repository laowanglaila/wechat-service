package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.common.ErrorCodes;
import com.hualala.app.wechat.common.WechatBaseApi;
import com.hualala.app.wechat.common.WechatErrorCode;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.mapper.mp.MpInfoModelMapper;
import com.hualala.app.wechat.model.mp.MpInfoModel;
import com.hualala.app.wechat.model.mp.MpInfoModelQuery;
import com.hualala.app.wechat.util.HttpApiUtil;
import com.hualala.app.wechat.util.MpIDProducer;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WechatCacheUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 开放平台相关服务类
 * Created by xkia on 2017/3/22.
 */
@Service
public class ComponentTokenService implements WechatBaseApi {

    private Logger logger = LoggerFactory.getLogger( ComponentTokenService.class );

    @Value("${wechat.data.componentAppID}")
    private String componentAppID;
    @Value("${wechat.data.componentAppSecret}")
    private String componentAppSecret;

    @Autowired
    private MpInfoModelMapper mpInfoModelMapper;
    @Autowired
    private MpInfoService mpInfoService;

//    @Autowired
//    private WechatCacheUtil WechatCacheUtil;

    /**
     * 获取预授权码
     * 预授权码用于公众号授权时的第三方平台方安全验证。
     *
     * @author renjianfei
     */
    public String createPreAuthCode() throws WechatInnerException {
        String componentAccessToken = initComponentToken();
        String param = "{\n" +
                "\"component_appid\":\"" + componentAppID + "\" \n" +
                "}";
        JSONObject jsonObject = this.componentPost( API_CREATE_PREAUTHCODE, componentAccessToken, param, componentAppID );
        if (!jsonObject.getBoolean( WechatMessageType.IS_SUCCESS )) {
            throw WechatInnerException.of(  "获取预授权码失败", jsonObject);
        }

        return jsonObject.getString( "pre_auth_code" );
    }

    /**
     * 使用授权码换取公众号的授权信息
     * 用于使用授权码换取授权公众号的授权信息，并换取authorizer_access_token和authorizer_refresh_token
     *
     * @author renjianfei
     */
    public String queryAuth(String authorizationCode) throws WechatInnerException {
        String componentAccessToken = this.initComponentToken();
        String params = "{\n" +
                "\"component_appid\":\"" + componentAppID + "\" ,\n" +
                "\"authorization_code\": \"" + authorizationCode + "\"\n" +
                "}";
        JSONObject result = this.componentPost( API_QUERY_AUTH, componentAccessToken, params, componentAppID );
        if (!result.getBoolean( WechatMessageType.IS_SUCCESS )) {
            throw  WechatInnerException.of( result );
        }
        JSONObject authorizationInfo = result.getJSONObject( "authorization_info" );
        String authorizerAccessToken = authorizationInfo.getString( "authorizer_access_token" );
        String authorizerAppID = authorizationInfo.getString( "authorizer_appid" );
        Long expiresIn = authorizationInfo.getLong( "expires_in" );
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            //使用runnable接口作为构造参数
            //真正的任务在这里执行
            try {
                this.getAuthorizerInfo( authorizerAppID, authorizerAccessToken );
            } catch (WechatInnerException e) {
                logger.error( e.getMessage(),e );
            }
        } );
        // 缓存
        WechatCacheUtil.setData( authorizerAppID, "authorizerAcToken", authorizerAccessToken, expiresIn );
        // 刷新令牌
        String authorizerRefreshToken = authorizationInfo.getString( "authorizer_refresh_token" );
        WechatCacheUtil.setData( authorizerAppID, "authorizerReToken", authorizerRefreshToken );
        return authorizerAccessToken;
    }

    /**
     * 获取授权方的账户信息并保存到数据库
     * 用于获取授权方的公众号基本信息，包括头像、昵称、帐号类型、认证类型、微信号、原始ID和二维码图片URL
     * @param authorizerAppID 公众号appID
     * @param authorizerRefreshToken 刷新令牌
     * @author
     */
    public void getAuthorizerInfo(String authorizerAppID,String authorizerRefreshToken) throws WechatInnerException {
        String componentAccessToken = this.initComponentToken();
        if (logger.isDebugEnabled())
            logger.debug( "---------componentAccessToken:" + componentAccessToken );
        String params = new StringBuilder( "{" )
                .append( "\"component_appid\":\"" ).append( componentAppID ).append( "\"," )
                .append( "\"authorizer_appid\":\"" ).append( authorizerAppID ).append( "\"" )
                .append( "}" ).toString();
        JSONObject resultJson = this.componentPost( API_GET_AUTHORIZER_INFO, componentAccessToken, params, componentAppID );

        if (logger.isDebugEnabled())
            logger.debug( "------resultJson : " + resultJson.toJSONString() );
        if (WechatMessageType.FALSE == resultJson.getBoolean( "isSuccess" )) {
            throw WechatInnerException.of( "获取公众号基本信息失败",resultJson );
        }
        JSONObject authorizerInfo = resultJson.getJSONObject( "authorizer_info" );
        String nickName = authorizerInfo.getString( "nick_name" ); // 授权方昵称
        String headImg = authorizerInfo.getString( "head_img" ); // 授权方头像
        JSONObject serviceTypeInfo = authorizerInfo.getJSONObject( "service_type_info" ); // 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
        String serviceType = serviceTypeInfo.getString( "id" );

        JSONObject verifyTypeInfo = authorizerInfo.getJSONObject( "verify_type_info" ); // 授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
        String verifyType = verifyTypeInfo.getString( "id" );

        String userName = authorizerInfo.getString( "user_name" ); // 公众号原始ID ghID
        String alias = authorizerInfo.getString( "alias" ); // 授权方公众号所设置的微信号，可能为空
        String qrcodeUrl = authorizerInfo.getString( "qrcode_url" ); // 二维码图片的URL

        JSONObject authorizationInfo = resultJson.getJSONObject( "authorization_info" ); // 授权信息
        String appID = authorizationInfo.getString( "authorizer_appid" ); // 授权方appid
        String funcInfo = authorizationInfo.getString( "func_info" ); // 公众号授权给开发者的权限集列表
        Integer isActiveUse = 1;
        Integer oauth = 1;
        Integer authorize = 1;
        //查看数据库有没有，有就更新，没有就插入
        MpInfoModelQuery mpInfoModelQuery = new MpInfoModelQuery();
        mpInfoModelQuery.createCriteria().andMpIDEqualTo( authorizerAppID );
        List <MpInfoModel> mpInfoModels = mpInfoModelMapper.selectByExample( mpInfoModelQuery );
        if (mpInfoModels.size() > 1){
            throw new WechatInnerException( "存在重复的公众号" );
        }
        MpInfoModel mpInfoModel = null;
        if (mpInfoModels.size() == 1) {
            mpInfoModel = mpInfoModels.get( 0 );
            mpInfoModel.setAppID( appID );
            mpInfoModel.setMpName( nickName );
            mpInfoModel.setHeadImg( headImg );
            mpInfoModel.setGhID( userName );
            mpInfoModel.setWeixinURL( qrcodeUrl );
            mpInfoModel.setFuncInfo( funcInfo );
            mpInfoModel.setMpType( getWechatType( serviceType,verifyType ) );
            mpInfoModel.setIsActiveUse( isActiveUse );
            mpInfoModel.setOauth( oauth );
            mpInfoModel.setAlias( alias );
            mpInfoModel.setAuthorize( authorize );
            mpInfoModel.setAuthorizerRefreshToken( authorizerRefreshToken );
            int i = mpInfoModelMapper.updateByPrimaryKeySelective( mpInfoModel );

        } else if (mpInfoModels.size() == 0){
            String mpID = MpIDProducer.initMpID();
            mpInfoModel = new MpInfoModel();
            mpInfoModel.setMpID( mpID );
            mpInfoModel.setAppID( appID );
            mpInfoModel.setMpName( nickName );
            mpInfoModel.setHeadImg( headImg );
            mpInfoModel.setGhID( userName );
            mpInfoModel.setWeixinURL( qrcodeUrl );
            mpInfoModel.setFuncInfo( funcInfo );
            mpInfoModel.setMpType( getWechatType( serviceType,verifyType ) );
            mpInfoModel.setIsActiveUse( isActiveUse );
            mpInfoModel.setOauth( oauth );
            mpInfoModel.setAlias( alias );
            mpInfoModel.setAuthorize( authorize );
            mpInfoModel.setAuthorizerRefreshToken( authorizerRefreshToken );
            int i = mpInfoModelMapper.insertSelective( mpInfoModel );
        }
    }

    /**
     * 返回公众号类型
     */
    private Integer getWechatType(String serviceType, String verifyType) {
        logger.debug( "mpType -- serviceType:" + serviceType + ";verifyType:" + verifyType );
        // 0 订阅号;1代表由历史老帐号升级后的订阅号; 2 服务号;
        if ("0".equals( serviceType.trim() ) || "1".equals( serviceType.trim() )) {
            if ("-1".equals( verifyType.trim() )) {
                return 10;
            } else {
                return 11;
            }
        } else if ("2".equals( serviceType.trim() )) {
            if ("-1".equals( verifyType.trim() )) {
                return 20;
            } else {
                return 21;
            }
        } else {
            return 0;
        }
    }
    /**
     * 获取公共平台token
     *
     * @return
     */
    public String initComponentToken() throws WechatInnerException {
        String params = getComponentModel( componentAppID, componentAppSecret );
        String componentAcToken = WechatCacheUtil.getData( componentAppID, "componentAcToken" );
        JSONObject resultJson = null;
        if (StringUtils.isBlank( componentAcToken )) {
            resultJson = HttpApiUtil.httpPost( WechatBaseApi.API_COMPONENT_TOKEN, params );
            if (resultJson == null || resultJson.getString( "component_access_token" ) == null) {
                String errcode = resultJson.getString( "errcode" );
                String errmsg = WechatErrorCode.wechatError.get( errcode );
                if (errmsg == null) {
                    errmsg = resultJson.getString( "errmsg" );
                }
                throw new WechatInnerException( errcode + "[" + errmsg + "]" );
//                return ResultUtil.toResultJson(resultJson, WechatMessageType.FALSE, errcode, errmsg);
            }
            componentAcToken = resultJson.getString( "component_access_token" );
            //缓存
            WechatCacheUtil.setData( componentAppID, "componentAcToken",
                    componentAcToken, Long.parseLong( resultJson.getString( "expires_in" ) ) );
        }
        return componentAcToken;
    }


    /**
     * 获取公众号调用凭证(accessToken)授权方式
     *
     * @return
     */
    public JSONObject apiAuthorizerToken(String authorizerAppID) throws WechatInnerException {

        String authorizerRefreshToken = WechatCacheUtil.getData( authorizerAppID, "authorizerReToken" );

        if (authorizerRefreshToken == null) {
            Map <String, Object> param = new HashMap <>();
            param.put( "appID", authorizerAppID );
            Map <String, Object> mpInfo = mpInfoService.queryMpInfo( param );
            if (mpInfo == null) {
                return ResultUtil.toResultJson( null, WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_NULL, "未找到对应的公众号" );
            }
            Integer authorize = (Integer) mpInfo.get( "authorize" );
            Integer mpType = (Integer) mpInfo.get( "mpType" );
            String authorizerReToken = String.valueOf( mpInfo.get( "authorizerRefreshToken" ) );

            if (authorize != 1) {
                return ResultUtil.toResultJson( null, WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ACCESSTOKEN_AUTH_ERROR, "公众号未授权" );
            }
            if (mpType != 11 && mpType != 21) {
                return ResultUtil.toResultJson( null, WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ACCESSTOKEN_AUTH_STATUS_ERROR, "公众号未认证" );
            }
            if (!StringUtils.isEmpty( authorizerReToken )) {
                authorizerRefreshToken = authorizerReToken;
            }
        }

        if (authorizerRefreshToken == null) {
            return ResultUtil.toResultJson( null, WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_REFRESHTOKEN_ERROR, "刷新令牌丢失，请重新授权公众号" );
        }
        String componentAccessToken = initComponentToken();

        JSONObject resultJson = componentPost( WechatBaseApi.API_AUTHORIZER_TOKEN, componentAccessToken, getComponentModel( componentAppID, authorizerAppID, authorizerRefreshToken ), componentAppID );

        if (WechatMessageType.FALSE == resultJson.getBoolean( WechatMessageType.IS_SUCCESS )) {
            return ResultUtil.toResultJson( null, WechatMessageType.FALSE, null, resultJson.getString( "errmsg" ) );
        }

        String value = resultJson.getString( "authorizer_access_token" );
        // 缓存
        WechatCacheUtil.setData( authorizerAppID, "authorizerAcToken", value, Long.parseLong( resultJson.getString( "expires_in" ) ) );
        // 刷新令牌
        authorizerRefreshToken = resultJson.getString( "authorizer_refresh_token" );

        WechatCacheUtil.setData( authorizerAppID, "authorizerReToken", authorizerRefreshToken );
        resultJson.put( "authorizerAccessToken", value );
        resultJson.put( "accessToken", value );
        return ResultUtil.toResultJson( resultJson, WechatMessageType.TRUE, ErrorCodes.WECHAT_SUCCESS_CODE, "" );
    }

    /**
     * 开放平台post请求
     *
     * @param url
     * @param componentAccessToken
     * @param parm
     * @param appID
     * @return
     */
    public JSONObject componentPost(String url, String componentAccessToken, String parm, String appID) {
        JSONObject resultJson = null;
        String errmsg = null;
        if (org.apache.commons.lang3.StringUtils.isEmpty( url ) || org.apache.commons.lang3.StringUtils.isEmpty( componentAccessToken ) || org.apache.commons.lang3.StringUtils.isEmpty( appID )) {
            return ResultUtil.toResultJson( resultJson, WechatMessageType.FALSE, ErrorCodes.WECHAT_PARPAS_EMPTY, "缺少参数" );
        }
        resultJson = HttpApiUtil.httpPost( url + "?component_access_token=" + componentAccessToken, parm );

        if (resultJson == null) {
            return ResultUtil.toResultJson( resultJson, WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ERROR, "httpClient请求出错" );
        }
        String errcode = resultJson.getString( "errcode" );
        if (errcode != null) {
            if (WechatMessageType.WECHAT_INVALID.equals( errcode ) || "42001".equals( errcode )) {
                try {
                    componentAccessToken = initComponentToken();
                } catch (WechatInnerException e) {
                    return ResultUtil.toResultJson( resultJson, WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ACCESSTOKEN_AUTH_ERROR, "获取accessToken服务失败" );
                }
                if (componentAccessToken == null) {
                    return ResultUtil.toResultJson( null, WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ERROR, "缓存中获取componentAccessToken is null" );
                }
                resultJson = HttpApiUtil.httpPost( url + "?component_access_token=" + componentAccessToken, parm );
                if (resultJson == null) {
                    return ResultUtil.toResultJson( resultJson, WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ERROR, "httpClient请求出错" );
                }
                errcode = resultJson.getString( "errcode" );
                if (errcode != null && !"0".equals( errcode )) {
                    errmsg = WechatErrorCode.wechatError.get( errcode );
                    return ResultUtil.toResultJson( resultJson, WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ERROR, errmsg );
                }
            } else if (!"0".equals( errcode )) {
                errmsg = WechatErrorCode.wechatError.get( errcode );
                return ResultUtil.toResultJson( resultJson, WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ERROR, errmsg );
            }
        }
        resultJson.put( WechatMessageType.IS_SUCCESS, WechatMessageType.TRUE );
        return resultJson;
    }

    /**
     * 获取授权令牌
     *
     * @param appID
     * @return
     */
    public JSONObject getAuthorizerAcToken(String appID,boolean isForceRefresh) throws WechatInnerException {
        JSONObject result = new JSONObject();
        String authorizerAcToken = null;
        if (!isForceRefresh){
            authorizerAcToken = WechatCacheUtil.getData( appID, "authorizerAcToken" );
        }
        if (authorizerAcToken == null) {
            JSONObject jsonObject = this.apiAuthorizerToken( appID );
            if (!jsonObject.getBoolean( WechatMessageType.IS_SUCCESS )) {
                return ResultUtil.toResultJson( result, WechatMessageType.FALSE, ErrorCodes.WECHAT_MP_ERROR, "获取authorizerAcToken失败：" + result.getString( WechatMessageType.MESSAGE ));
            }
            authorizerAcToken = jsonObject.getString( "accessToken" );
        }
        result.put( "authorizerAcToken", authorizerAcToken );
        result.put( "accessToken", authorizerAcToken );
        return ResultUtil.toResultJson(result,WechatMessageType.TRUE,ErrorCodes.WECHAT_SUCCESS_CODE,"执行成功");
    }

    /**
     * 拼接获取componentToken请求json
     * * @param component_appid
     *
     * @param component_appsecret
     * @return
     */
    private String getComponentModel(String component_appid, String component_appsecret) {

        String ticket = WechatCacheUtil.getData( component_appid, "ticket" );
        if (ticket == null) {
            logger.error( "component_verify_ticket is null " );
            return null;
        }
        StringBuffer sb = new StringBuffer( "{" );
        sb.append( "\"component_appid\":\"" ).append( component_appid ).append( "\"," )
          .append( "\"component_appsecret\":\"" ).append( component_appsecret ).append( "\"," )
          .append( "\"component_verify_ticket\":\"" ).append( ticket ).append( "\"" )
          .append( "}" );

        return sb.toString();
    }

    /**
     * 拼接获取componentToken请求json
     *
     * @param component_appid
     * @param authorizer_appid
     * @param authorizer_refresh_token
     * @return
     */
    private String getComponentModel(String component_appid, String authorizer_appid, String authorizer_refresh_token) {
        StringBuffer sb = new StringBuffer( "{" );
        sb.append( "\"component_appid\":\"" ).append( component_appid ).append( "\"," )
          .append( "\"authorizer_appid\":\"" ).append( authorizer_appid ).append( "\"," )
          .append( "\"authorizer_refresh_token\":\"" ).append( authorizer_refresh_token ).append( "\"" )
          .append( "}" );

        return sb.toString();
    }
}
