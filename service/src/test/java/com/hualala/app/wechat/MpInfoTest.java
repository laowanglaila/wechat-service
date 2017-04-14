package com.hualala.app.wechat;

import com.alibaba.fastjson.JSON;
import com.hualala.app.wechat.util.template.WechatTemplateFatory;
import com.hualala.core.client.BaseRpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * Created by xkia on 2017/3/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MpInfoTest {
    @Autowired
    BaseRpcClient rpcClient;

    @Autowired
    WechatTemplateFatory wechatTemplateFatory;

    @Test
    public void myInfoQueryTest(){

        MpInfoRpcService mpInfoRpcService = rpcClient.getRpcClient(MpInfoRpcService.class);
        MpInfoRpcService.MpInfoQueryReqData mpInfoQueryReqData = new MpInfoRpcService.MpInfoQueryReqData();
        mpInfoQueryReqData.setMpID("doulaofang_caicai");
        MpInfoRpcService.MpInfoQueryResData mpInfoQueryResData = mpInfoRpcService.queryMpInfo(mpInfoQueryReqData);
        System.out.println(JSON.toJSONString(mpInfoQueryResData));
    }

    @Test
    public void SpringBeanTest(){
         System.out.println("---------" +wechatTemplateFatory.newWechatTemplate("","queue","alarm",null).getModelType());
    }




//    @Test
//    public void test(){
//        String getTemplateJson = "{\"first\":\"尊敬的顾客,您好!您的排号请求已被确认! \",\"keyword1\":\"[keynote1]\",\"keyword2\":\"[keynote2]\",\"keyword3\":\"[keynote3]\",\"keyword4\":\"[keynote4]位\",\"modelID\":\"[modelID]\",\"remark\":\"[remark]\",\"templateID\":\"[templateID]\"}";
//        Map<String,String> map = new HashMap<>();
//        map.put("keynote1","关键字1");
//
//        final Properties properties = new Properties();
//        properties.putAll(map);
//
//        PropertyPlaceholderHelper strictHelper = new PropertyPlaceholderHelper(
//                "["
//                    ,"]",
//               ":", true);
//        String templateStr = strictHelper.replacePlaceholders(getTemplateJson,
//                properties);
//
//        System.out.println(templateStr);
//    }
}
