package com.hualala.app.wechat.impl;

import com.hualala.app.wechat.TemplateItemTypeEnum;
import com.hualala.app.wechat.WechatTemplateRpcService;
import com.hualala.app.wechat.WechatTemplateTypeEnum;
import com.hualala.core.client.BaseRpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

        WechatTemplateRpcService.WechatTemplateRpcReqData reqData = new WechatTemplateRpcService.WechatTemplateRpcReqData();
        reqData.setBrandID( 5 );
        reqData.setGroupID( 5 );
        reqData.setFirst( "first-Tital" );
        reqData.setKeynote1( "关键字1" );
        reqData.setKeynote2( "关键字2" );
        reqData.setKeynote3( "关键字3" );
        reqData.setKeynote4( "关键字4" );
        reqData.setKeynote5( "关键字5" );
        reqData.setKeynote6( "关键字6" );
        reqData.setRemark( "" );
        reqData.setMpID( "doulaofangceshi" );
        reqData.setOrderKey( "00000000000000000000000000000000" );

//        reqData.setModelType("queue");
//        reqData.setModelSubType("alarm");
        reqData.setTemplateType( WechatTemplateTypeEnum.TEMPLATE_ENUM_QUEUE );
        reqData.setUserID( 3573647 );
        reqData.setOpenID( "o7FjEuGrxL0V1qnfz2mxNCUhNxJY" );

        WechatTemplateRpcService wechatService = rpcClient.getRpcClient( WechatTemplateRpcService.class );
        wechatService.sentWechatTemplate( reqData );
    }

    @Test
    public void sentMQWechatTemplate() throws Exception {
//        String openID = "oAkHb1IuY78GZyAo9uxzna7MPvV8";
        String userID = "3574377";
        String mpID = "doulaofang1ceshi";
//        String mpID = "dohko1155";
        List <WechatTemplateRpcService.WechatTemplateItem> items = new ArrayList <>();
        WechatTemplateRpcService.WechatTemplateItem first = new WechatTemplateRpcService.WechatTemplateItem();
        first.setType( TemplateItemTypeEnum.TEMPLATE_ITEM_TYPE_FIRST );
        first.setValue( "测试模板消息:first" );
        items.add( first );

        WechatTemplateRpcService.WechatTemplateItem remark = new WechatTemplateRpcService.WechatTemplateItem();
        remark.setType( TemplateItemTypeEnum.TEMPLATE_ITEM_TYPE_REMARK );
        remark.setValue( "测试模板消息:remark" );
        items.add( remark );
        WechatTemplateRpcService.WechatTemplateItem keynote1 = new WechatTemplateRpcService.WechatTemplateItem();
        keynote1.setType( TemplateItemTypeEnum.TEMPLATE_ITEM_TYPE_KEYNOTE_1 );
        keynote1.setValue( "1" );
        items.add( keynote1 );
        WechatTemplateRpcService.WechatTemplateItem keynote2 = new WechatTemplateRpcService.WechatTemplateItem();
        keynote2.setType( TemplateItemTypeEnum.TEMPLATE_ITEM_TYPE_KEYNOTE_2 );
        keynote2.setValue( "2" );
        items.add( keynote2 );
        WechatTemplateRpcService.WechatTemplateItem keynote3 = new WechatTemplateRpcService.WechatTemplateItem();
        keynote3.setType( TemplateItemTypeEnum.TEMPLATE_ITEM_TYPE_KEYNOTE_3 );
        keynote3.setValue( "3" );
        items.add( keynote3 );
        WechatTemplateRpcService.WechatTemplateItem keynote4 = new WechatTemplateRpcService.WechatTemplateItem();
        keynote4.setType( TemplateItemTypeEnum.TEMPLATE_ITEM_TYPE_KEYNOTE_4 );
        keynote4.setValue( "4" );
        items.add( keynote4 );
        WechatTemplateRpcService.WechatTemplateItem keynote5 = new WechatTemplateRpcService.WechatTemplateItem();
        keynote5.setType( TemplateItemTypeEnum.TEMPLATE_ITEM_TYPE_KEYNOTE_5 );
        keynote5.setValue( "5" );
        keynote5.setColor( "#173177" );
        items.add( keynote5 );

        WechatTemplateRpcService.WechatSendTemplateReq reqData = new WechatTemplateRpcService.WechatSendTemplateReq();
        reqData.setTemplateType( WechatTemplateTypeEnum.TEMPLATE_ENUM_ASSESSMENT);
//        reqData.setOpenID( openID );
        reqData.setUserID( Long.parseLong( userID ));
        reqData.setMpID( mpID );
        reqData.setTemplateItem( items );
        WechatTemplateRpcService wechatService = rpcClient.getRpcClient( WechatTemplateRpcService.class );
        for (int i = 0; i < 10; i++) {
            WechatTemplateRpcService.WechatSendTemplateRes wechatSendTemplateRes = wechatService.sentWechatTemplateByMQ( reqData );
            System.out.println( wechatSendTemplateRes.getMessageParams() );
        }
    }

}