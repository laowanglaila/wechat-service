package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.WechatTemplateRpcService;
import com.hualala.app.wechat.WechatTemplateTypeEnum;
import com.hualala.core.client.BaseRpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by xkia on 2017/4/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatTemplateRpcServiceImplTest {

    @Autowired
    BaseRpcClient rpcClient;
    @Test
    public void sentWechatTemplate() throws Exception {

        WechatTemplateRpcService.WechatTemplateRpcReqData  reqData= new WechatTemplateRpcService.WechatTemplateRpcReqData();
        reqData.setBrandID(5);
        reqData.setGroupID(5);
        reqData.setFirst("first-Tital");
        reqData.setKeynote1("关键字1");
        reqData.setKeynote2("关键字2");
        reqData.setKeynote3("关键字3");
        reqData.setKeynote4("关键字4");
        reqData.setKeynote5("关键字5");
        reqData.setKeynote6("关键字6");
        reqData.setRemark("备注说明");
        reqData.setMpID("doulaofangceshi");

//        reqData.setModelType("queue");
//        reqData.setModelSubType("alarm");
        //reqData.setTemplateType(WechatTemplateTypeEnum.TEMPLATE_ENUM_QUEUE);
        reqData.setUserID(3573647);
        reqData.setOpenID("o7FjEuGrxL0V1qnfz2mxNCUhNxJY");

        WechatTemplateRpcService wechatService = rpcClient.getRpcClient(WechatTemplateRpcService.class);
        wechatService.sentWechatTemplate(reqData);
    }

}