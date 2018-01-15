package com.hualala.app.wechat.util;

import com.hualala.app.wechat.WechatRequestInfo;
import com.hualala.app.wechat.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.exception.WechatException;
import com.hualala.app.wechat.mapper.WechatMpMapper;
import com.hualala.app.wechat.service.MpInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by renjianfei on 2017/8/17.
 */
@Slf4j
@Component
public class RequestUtil {
    @Autowired
    private MpInfoService mpInfoServiceInjector;
    @Autowired
    private WechatMpMapper wechatMpMapperInjector;
    private static MpInfoService mpInfoService;
    private static WechatMpMapper wechatMpMapper;
    @PostConstruct
    public void init(){
        mpInfoService = this.mpInfoServiceInjector;
        wechatMpMapper = this.wechatMpMapperInjector;
    }
    public static String getMpID(WechatRequestInfo requestInfo){
        String mpID = requestInfo.getMpID();
        if (StringUtils.isBlank(mpID)) {
            Long brandID = requestInfo.getBrandID();
            Long groupID = requestInfo.getGroupID();
            Long shopID = requestInfo.getShopID();
            if ( groupID == null || shopID == null) {
                log.error("mpID为空并且没有提供brandID[{}]、groupID[{}]、shopID[{}]！",brandID,groupID,shopID);
                throw new WechatException(WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS,"mpID为空并且没有提供brandID、groupID、shopID！");
            }
            //通过上面三个属性获取mpID，调用方法待定；
            mpID = mpInfoService.queryMpIDAuth(groupID, brandID, shopID);
        }
        if (StringUtils.isBlank(mpID)) {
            //返回响应对象，设置错误信息和错误码；
            throw new WechatException(WechatExceptionTypeEnum.WECHAT_ILLEGAL_ARGUMENTS,"获取mpID失败，或者公众号不存在！");
        }
        return mpID;
    }
}
