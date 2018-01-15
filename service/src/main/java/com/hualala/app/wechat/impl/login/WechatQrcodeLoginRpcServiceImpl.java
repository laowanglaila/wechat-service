package com.hualala.app.wechat.impl.login;

import com.hualala.app.wechat.LangTypeEnum;
import com.hualala.app.wechat.WebAuthorizationRpcService;
import com.hualala.app.wechat.WechatQrcodeLoginRpcService;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.exception.WechatInnerException;
import com.hualala.app.wechat.impl.user.WebAuthorizationRpcServiceImpl;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.UserTokenService;
import com.hualala.app.wechat.vo.UserAuthVO;
import com.hualala.core.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by renjianfei on 2017/12/18.
 */
@Slf4j
@Service
public class WechatQrcodeLoginRpcServiceImpl implements WechatQrcodeLoginRpcService  {
    private static final String url= "https://open.weixin.qq.com/connect/qrconnect";
    private static final String SHOP_DOHKO_APPID= "wx707511f565f186b9";
    private static final String SHOP_DOHKO_SECRET= "00bca46012bceea791d47f5f55f3f6dd";
    private static final String SHOP_PRE_APPID= "wx5b5d9bc30db9d1fd";
    private static final String SHOP_PRE_SECRET= "dad44b640db98aa9624101daedffa6a6";
    private static final String SHOP_APPID= "wx499ddc30e0416fc9";
    private static final String SHOP_SECRET= "8be8197b496ebdd53bcc18f38f8c9e84";
    private static Map<String,String> appRepository;
    static {
        appRepository = new HashMap <>(  );
        appRepository.put( SHOP_DOHKO_APPID, SHOP_DOHKO_SECRET );
        appRepository.put( SHOP_PRE_APPID, SHOP_PRE_SECRET );
        appRepository.put( SHOP_APPID, SHOP_SECRET );
    }
    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private WebAuthorizationRpcServiceImpl webAuthorizationRpcService;
    @Autowired
    private UserTokenService userTokenService;
    @Override
    public OauthUrlRes getOauthUrl(OauthUrlReq reqData) {
        String appID = reqData.getAppID();
        String callBackUrl = reqData.getCallBackUrl();
        String encodeUtl = Base64.getUrlEncoder().encodeToString( callBackUrl.getBytes() );
        String state = "open";
        String scope = "snsapi_login";
        String oantUrl = this.url + "?appid=" + appID + "&redirect_uri=" + encodeUtl +
                "&response_type=code&scope=" + scope + "&state=" + state + "#wechat_redirect";
        OauthUrlRes oauthUrlRes = new OauthUrlRes();
        oauthUrlRes.setOauthUrl( oantUrl );
        return oauthUrlRes;
    }

    @Override
    public QrcodeLoginRes qrcodeLogin(QrcodeLoginReq userAuthorizationReq) {
        String appID = userAuthorizationReq.getAppID();
        if (!appRepository.containsKey( appID )){
            throw new WechatException( "","appID" );
        }
        String code = userAuthorizationReq.getCode();
        UserAuthVO userAuthVO = null;
        try {
            userAuthVO = userTokenService.userAuthorization( appID, appRepository.get( appID ), code );
        } catch (WechatInnerException e) {
            log.error( "微信二维码登录异常:" + e.getMessage(),e );
            throw new WechatException( "","微信二维码登录异常" );
        }
        String openID = userAuthVO.getOpenID();
        String accessToken = userAuthVO.getAccessToken();
        WebAuthorizationRpcService.WechatUserInfoReq wechatUserInfoReq = new WebAuthorizationRpcService.WechatUserInfoReq();
        wechatUserInfoReq.setAccessToken( accessToken );
        wechatUserInfoReq.setOpenid( openID );
        wechatUserInfoReq.setLang( LangTypeEnum.zh_CN );

        WebAuthorizationRpcService.WechatUserInfoRes wechatUserInfo = webAuthorizationRpcService.getWechatUserInfo( wechatUserInfoReq );
        QrcodeLoginRes qrcodeLoginRes = DataUtils.copyProperties( wechatUserInfo, QrcodeLoginRes.class );
        return qrcodeLoginRes;
    }


}
