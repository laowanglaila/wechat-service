package com.hualala.app.wechat.event;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.crm.bean.card.VoucherCardReq;
import com.hualala.app.crm.bean.card.VoucherCardRes;
import com.hualala.app.crm.service.CardInfoService;
import com.hualala.app.wechat.BaseRpcTest;

/**
 * Created by renjianfei on 2017/12/20.
 */
public class VoucherChardTest extends BaseRpcTest {
    @Override
    public void test() throws InterruptedException {
        CardInfoService rpcClient = baseRpcClient.getRpcClient( CardInfoService.class );
        VoucherCardReq voucherCardReq = new VoucherCardReq();
        String mpID = "dohko1155";
        Long groupID = 1155L;
        Long cardKey = 6501468355902317699L;
        String code = "592766754503";
        Long cardType = 788298068629155840L;
        String fromUserName = "oFx2ov0gR-6v8jYAZdGPllWpI0IY";
        String openid = "oACwGs4QPjQ_JoAVBOCQgwC12yFk";
        voucherCardReq.setMpID( mpID );
        voucherCardReq.setGroupID( groupID );
        voucherCardReq.setWechatCardKey( cardKey );
        voucherCardReq.setCardTypeID( cardType );
        voucherCardReq.setWechatCardCode( code );
        voucherCardReq.setSourceWay( true );
        voucherCardReq.setSourceType( 30 );
        voucherCardReq.setShopWeixinID( fromUserName );
//        voucherCardReq.setWeixinID( openid );
        VoucherCardRes voucherCardRes = rpcClient.reverseVoucherCardAssociation( voucherCardReq );
        System.out.println( JSONObject.toJSONString( voucherCardRes ));
    }
}
