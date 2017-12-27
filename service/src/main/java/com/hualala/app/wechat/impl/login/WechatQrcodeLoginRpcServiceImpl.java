package com.hualala.app.wechat.impl.login;

import com.hualala.app.wechat.WechatQrcodeLoginRpcService;
import com.hualala.app.wechat.service.BaseHttpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by renjianfei on 2017/12/18.
 */
@Service
public class WechatQrcodeLoginRpcServiceImpl implements WechatQrcodeLoginRpcService {
    @Autowired
    private BaseHttpService baseHttpService;

    private static final String url= "https://open.weixin.qq.com/connect/qrconnect";
    private static final String appID= "wx707511f565f186b9";
    private static final String appSecret= "00bca46012bceea791d47f5f55f3f6dd";
    private static Map<String,String> appRepository;
    static {
        appRepository = new HashMap <>(  );
        appRepository.put( appID, appSecret );
    }

    @Override
    public OauthUrlRes getOauthUrl(OauthUrlReq reqData) {
        String appID = reqData.getAppID();
        String appSecret = appRepository.get( appID );
        if (StringUtils.isBlank(appSecret)){
            appID = this.appID;
            appSecret = this.appSecret;
        }
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



        return null;
    }
}
