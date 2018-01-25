package com.hualala.app.wechat.impl.invoice;

import com.hualala.app.wechat.InvoiceTemplateRpcService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InvoiceTemplateRpcServiceImplTest {

    @Autowired
    InvoiceTemplateRpcServiceImpl invoiceTemplateRpcService;

    /**
     * 测试创建电子发票模板，并将模板信息写入数据库
     */
    @Test
    public void test(){
        InvoiceTemplateRpcService.InvoiceInfoReq infoReq = new InvoiceTemplateRpcService.InvoiceInfoReq();
        //40081 api功能未授权
        infoReq.setMpID("hualala_com");
        infoReq.setPayee("测试-开票人");
        infoReq.setType("个人");
        infoReq.setLogoUrl("http://res.hualala.com/group2/M00/8E/C0/wKgVT1pdqg2YMK5NAAACkQelpg0888.png");
        infoReq.setTitle("豆捞坊");

//        infoReq.setMpID("doulaofang1ceshi");

        invoiceTemplateRpcService.create(infoReq);
    }
}
