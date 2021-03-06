package com.hualala.app.wechat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.LangTypeEnum;
import com.hualala.app.wechat.common.*;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.impl.WechatTemplateRpcServiceImpl;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.core.app.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by renjianfei on 2017/4/6.
 * 微信卡券通用接口
 */
@Service
public class BaseHttpService implements WechatBaseApi {


    private Logger logger = Logger.of(WechatTemplateRpcServiceImpl.class);

    @Autowired
    private HttpApiService httpApiService;

    /**
     * 通用的httppost请求方法返回标准的result
     * 适合errcode =“0”代表成功的请求使用
     */
    public JSONObject commonHttpPost(String url, Map<String, Object> map, String mpID) {
        String jsonString = JSONObject.toJSONString(map);
        return commonHttpPost(url,jsonString,mpID);
    }

    /**
     * 通用的httppost请求方法返回标准的result
     * 适合errcode =“0”代表成功的请求使用
     */

    public JSONObject commonHttpPost(String url, String jsonString, String mpID) {
        logger.debug(() -> "微信请求参数 ：" + jsonString);
        JSONObject responseJson = httpApiService.httpPost(url, jsonString, mpID);
        //首先判断 null ：200    然后判断创建是否成功
        if (null == responseJson) {
            return ResultUtil.toResultJson(responseJson, false, ErrorCodes.WECHAT_HTTP_FAILED, "http请求失败！");
        } else if(responseJson.containsKey( WechatMessageType.IS_SUCCESS) && !responseJson.getBoolean(WechatMessageType.IS_SUCCESS)){
            return responseJson;
        }
        logger.debug(() -> "微信响应参数 ：" + responseJson.toJSONString());
        String errcode = responseJson.getString(WechatMessageType.WECHAT_ERR_CODE);
        if (StringUtils.isNotBlank(errcode) && !"0".equals(errcode)) {
            String errmassage = WechatErrorCode.wechatError.get(errcode);
            String errorcode = errmassage == null ? "[ errcode:"+errcode+" ]" : errmassage;
            return ResultUtil.toResultJson(responseJson, false, null, errorcode+":[ "+responseJson.getString(WechatMessageType.WECHAT_ERR_MESSAGE)+" ]");
        }
        return ResultUtil.toResultJson(responseJson, true, ErrorCodes.WECHAT_SUCCESS_CODE, "执行成功！");
    }
    /**
     * 通用的httppost请求方法返回标准的result
     * 适合errcode =“0”代表成功的请求使用
     */

    public JSONObject commonHttpGet(String url, String mpID) {
        logger.debug(() -> "微信请求参数 ：" + url);
        JSONObject responseJson = httpApiService.httpGet(url, mpID);
        //首先判断 null ：200    然后判断创建是否成功
        if (null == responseJson) {
            return ResultUtil.toResultJson(responseJson, false, ErrorCodes.WECHAT_HTTP_FAILED, "http请求失败！");
        } else if(responseJson.containsKey(WechatMessageType.IS_SUCCESS) && !responseJson.getBoolean(WechatMessageType.IS_SUCCESS)){
            return responseJson;
        }
        logger.debug(() -> "微信响应参数 ：" + responseJson.toJSONString());
        String errcode = responseJson.getString(WechatMessageType.WECHAT_ERR_CODE);
        if (StringUtils.isNotBlank(errcode) && !"0".equals(errcode)) {
            String errmassage = WechatErrorCode.wechatError.get(errcode);
            String errorcode = errmassage == null ? "[ errcode:"+errcode+" ]" : errmassage;
            return ResultUtil.toResultJson(responseJson, false, null, errorcode+":[ "+responseJson.getString(WechatMessageType.WECHAT_ERR_MESSAGE)+" ]");
        }
        return ResultUtil.toResultJson(responseJson, true, ErrorCodes.WECHAT_SUCCESS_CODE, "执行成功！");
    }

    /**
     * Created by renjianfei on 2017/4/6.
     * 创建卡券接口
     * 返回的结果只包含错误和成功，没有null值。
     * 返回示例：
     * responseJson：
     * {
     * "errcode":0,
     * "errmsg":"ok",
     * "card_id":"p1Pj9jr90_SQRaVqYI239Ka1erkI"
     * }
     */
    public JSONObject createCardAndCoupon(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(CREATE_CARD_URL, map, mpId);
    }


    /**
     * Created by renjianfei on 2017/4/7.
     * 查询code接口可以查询当前code是否可以被核销并检查code状态。当前可以被定位的状态为正常、已核销、转赠中、已删除、已失效和无效code。
     */
    public JSONObject getCodeStatus(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(GET_CODE_STATUS, map, mpId);
    }

    public JSONObject getCodeStatus(String json, String mpId) {
        return this.commonHttpPost(GET_CODE_STATUS, json, mpId);
    }

    /**
     * Created by renjianfei on 2017/4/7.
     * 用于获取用户卡包里的，属于该appid下所有可用卡券，包括正常状态和未生效状态。
     */
    public JSONObject getCardList(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(GET_CARD_LIST, map, mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 查询某个card_id的创建信息、审核状态以及库存数量。
     */
    public JSONObject getCardInfo(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(GET_CARD_INFO, map, mpId);
    }

    public JSONObject getCardInfo(String jsonString, String mpId) {
        return this.commonHttpPost(GET_CARD_INFO, jsonString, mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 批量查看卡券详情
     */
    public JSONObject getBatchCardID(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(BATCHGET_CARD_ID, map, mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 增减某张券的库存
     */
    public JSONObject setCardSku(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(MODIFY_CARD_SKU, map, mpId);
    }

    public JSONObject setCardSku(String jsonString, String mpId) {
        return this.commonHttpPost(MODIFY_CARD_SKU, jsonString, mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 删除一个优惠券或者会员卡
     */
    public JSONObject deleteCard(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(DELETE_CARD, map, mpId);
    }

    public JSONObject deleteCard(String jsonString, String mpId) {
        return this.commonHttpPost(DELETE_CARD, jsonString, mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 设置优惠券或者会员卡为失效状态
     */
    public JSONObject setCardUnavailable(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(UNAVAILABLE_CARD, map, mpId);
    }
    public JSONObject setCardUnavailable(String json, String mpId) {
        return this.commonHttpPost(UNAVAILABLE_CARD, json, mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 获取测试白名单
     */
    public JSONObject setWhiteList(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(SET_WHITE_LIST, map, mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 导入微信code码
     * 单次导入微信卡券后台的自定义code，上限为100个
     */
    public JSONObject importCardCode(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(IMPORT_CARD_CODE, map, mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 查询code导入微信后台成功的数目
     */
    public JSONObject getDepositCount(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(GET_DEPOSIT_COUNT, map, mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 查询code导入微信后台的情况
     */
    public JSONObject checkCardCode(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(CHECK_CARD_CODE, map, mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 获取息群发卡券图文消
     */
    public JSONObject getHtmlMessage(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(GET_HTML_MESSAGE, map, mpId);
    }

    /**
     * reated by renjianfei on 2017/4/7.
     * 核销code接口
     */
    public JSONObject destoryCardCode(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(CONSUME_CARD_CODE, map, mpId);
    }

    public JSONObject destoryCardCode(String cardID,String code, String mpId) {
        StringBuilder stringBuilder = new StringBuilder( "{\"code\": \"" );
        stringBuilder.append( code ).append( "\"" );
        if (StringUtils.isNotBlank( cardID )){
            stringBuilder.append( ",\"card_id\": \"" ).append( cardID ).append( "\"" );
        }
        stringBuilder.append( "}" );
        String json = stringBuilder.toString();
        return this.commonHttpPost(CONSUME_CARD_CODE, json, mpId);
    }

    /**
     * code解码接口
     * :https://api.weixin.qq.com/card/code/decrypt
     */
    public JSONObject decodingCardCode(String json, String mpId) {
        return this.commonHttpPost(DECRYPT_CARD_CODE, json, mpId);
    }

    /**
     * 修改卡面信息
     * 支持更新所有卡券类型的部分通用字段及特殊卡券（会员卡、飞机票、电影票、会议门票）中特定字段的信息。
     */
    public JSONObject updateCardInfo(Map<String, Object> map, String mpID) {
        String url = UPDATE_CARD_INFO;
        return this.commonHttpPost(url, map, mpID);
    }


//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    /**
     * Created by renjianfei on 2017/4/7.
     * 优惠券微信接口
     */

    /**
     * Created by renjianfei on 2017/4/7.
     * 微信自助核销卡券接口
     */
    public JSONObject setSelfConsume(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(SET_SELF_CONSUME, map, mpId);
    }

    /**
     * Created by renjianfei on 2017/4/7.
     * 更改code接口（在转赠优惠券时使用）
     */
    public JSONObject updateCardCode(Map<String, Object> map, String mpId) {
        return this.commonHttpPost(UPDATE_CARD_CODE, map, mpId);
    }

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 激活会员卡接口
     */
    public JSONObject activate(Map<String, Object> map, String mpID) {
        String url = ACTIVATE_CARD;
        return this.commonHttpPost(url, map, mpID);
    }
    public JSONObject activate(String json, String mpID) {
        String url = ACTIVATE_CARD;
        return this.commonHttpPost(url, json, mpID);
    }

    /**
     * 更新会员信息
     */
    public JSONObject updateMemberInfo(Map<String, Object> map, String mpID) {
        String url = UPDATE_MEMBER_INFO;
        return this.commonHttpPost(url, map, mpID);
    }
    public JSONObject updateMemberInfo(String params, String mpID) {
        String url = UPDATE_MEMBER_INFO;
        return this.commonHttpPost(url, params, mpID);
    }

    /**
     * 拉取会员信息（积分）
     */
    public JSONObject getMemberInfo(Map<String, Object> map, String mpID) {
        String url = GET_MEMBER_INFO;
        return this.commonHttpPost(url, map, mpID);
    }

    /**
     * 获取一键激活时用户填入的信息
     */
    public JSONObject getActivateUserForm(Map<String, Object> map, String mpID) {
        String url = GET_ACTIVATE_USERFORM;
        return this.commonHttpPost(url, map, mpID);
    }

    /**
     * 获取一键激活时用户填入的信息
     */
    public JSONObject setActivateUserForm(Map<String, Object> map, String mpID) {
        String url = SET_ACTIVATE_USERFORM;
        return this.commonHttpPost(url, map, mpID);
    }
    /**
     * 获取一键激活时用户填入的信息
     */
    public JSONObject setActivateUserForm(String json, String mpID) {
        JSONObject jsonObject = JSONObject.parseObject( json );
        String url = SET_ACTIVATE_USERFORM;
        return this.commonHttpPost(url, jsonObject, mpID);
    }

    /**
     * 获取微信二维码
     */
    public JSONObject createQrCode(Map<String, Object> map, String mpID) {
        String url = CREATE_QR_CODE;
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
        JSONObject responseJson = httpApiService.httpPost(url, jsonObject.toJSONString(), mpID);
        //首先判断 null ：200    然后判断创建是否成功
        if (null == responseJson) {
            return ResultUtil.toResultJson(responseJson, false, ErrorCodes.WECHAT_HTTP_FAILED, "http请求失败！");
        }
        String ticket = responseJson.getString("ticket");
        String expireSeconds = responseJson.getString("expire_seconds");
        String qrurl = responseJson.getString("url");
        if (StringUtils.isBlank(ticket) && StringUtils.isBlank(qrurl)) {
            return ResultUtil.toResultJson(responseJson, false, ErrorCodes.WECHAT_HTTP_FAILED, WechatErrorCode.wechatError.get(responseJson.getString("errcode")));
        }
        return ResultUtil.toResultJson(responseJson, true, ErrorCodes.WECHAT_SUCCESS_CODE, "");
    }

    public JSONObject createQrCode(String param, String mpID) {
        String url = CREATE_QR_CODE;
        JSONObject responseJson = httpApiService.httpPost(url, param, mpID);
        //首先判断 null ：200    然后判断创建是否成功
        if (null == responseJson) {
            return ResultUtil.toResultJson(responseJson, false, ErrorCodes.WECHAT_HTTP_FAILED, "http请求失败！");
        } else if(responseJson.containsKey(WechatMessageType.IS_SUCCESS) && !responseJson.getBoolean(WechatMessageType.IS_SUCCESS)){
            return responseJson;
        }
        String ticket = responseJson.getString("ticket");
        String expireSeconds = responseJson.getString("expire_seconds");
        String qrurl = responseJson.getString("url");
        if (StringUtils.isBlank(ticket) && StringUtils.isBlank(qrurl)) {
            if (responseJson.containsKey(WechatMessageType.WECHAT_ERR_CODE)
                    && "48001".equals(responseJson.getString(WechatMessageType.WECHAT_ERR_CODE))){
                throw new WechatException( WechatExceptionTypeEnum.WECHAT_MP_PERMISSION_DENIED);
            }
            return ResultUtil.toResultJson(responseJson, false, ErrorCodes.WECHAT_HTTP_FAILED, WechatErrorCode.wechatError.get(responseJson.getString("errcode")));
        }
        return ResultUtil.toResultJson(responseJson, true, ErrorCodes.WECHAT_SUCCESS_CODE, "");
    }

    /**
     * 获得模板ID
     *
     * @param jsonObject
     * @param mpID
     * @return
     */
    public JSONObject apiAddTemplate(JSONObject jsonObject, String mpID) {
        return this.commonHttpPost(API_ADD_TEMPLATE, jsonObject, mpID);
    }

    /**
     * 支付即会员、支付赠券
     * @param jsonObject
     * @param mpID
     * @return
     */
    public JSONObject payGiftCard(JSONObject jsonObject , String mpID){
        return this.commonHttpPost(PAY_GIFT_CARD, jsonObject, mpID);
    }
    public JSONObject payGiftCard(String json , String mpID){
        return this.commonHttpPost(PAY_GIFT_CARD, json, mpID);
    }

    /**
     * 客服消息接口
     * @param json
     * @param mpID
     * @return
     */
    public JSONObject messageCustomSend(String json, String mpID){
        return this.commonHttpPost(MESSAGE_CUSTOM_SEND, json, mpID);
    }
    /**
     * 获取用户信息
     * @param
     * @return
     */
    public JSONObject getWechatUserInfo(String accessToken, String openid, LangTypeEnum langTypeEnum){
        String params = "access_token=" + accessToken + "&openid=" + openid + "&lang=" + langTypeEnum.name();
        String url = GET_WECHAT_USERINFO + "?" + params;
        return this.commonHttpGet(url, null);
    }
    /**
     * 获取用户信息
     * @param
     * @return
     */
    public JSONObject getWechatAPIUserInfo(String openID, LangTypeEnum langType, String mpID){
        String param = "openid=" + openID + "&lang=" + langType.name();
        String url = GET_WECHAT_API_USERINFO + "?" + param;
        return this.commonHttpGet(url, mpID);
    }

}
