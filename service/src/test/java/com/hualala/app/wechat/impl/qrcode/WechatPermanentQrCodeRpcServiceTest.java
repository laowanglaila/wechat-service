package com.hualala.app.wechat.impl.qrcode;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.WechatPermanentQrCodeRpcService;
import com.hualala.core.base.ResultInfo;
import org.junit.Test;

/**
 * Created by renjianfei on 2017/10/16.
 */
public class WechatPermanentQrCodeRpcServiceTest extends BaseRpcTest {
    @Override
    public void test() throws InterruptedException {
        String mpID = "renjianfei";
        String shopID = "76022769";
        WechatPermanentQrCodeRpcService rpcClient = super.baseRpcClient.getRpcClient( WechatPermanentQrCodeRpcService.class );
        WechatPermanentQrCodeRpcService.UpdateQrCodeReq updateQrCodeReq = new WechatPermanentQrCodeRpcService.UpdateQrCodeReq();
        updateQrCodeReq.setMpID( mpID );
        updateQrCodeReq.setShopID( shopID );
        updateQrCodeReq.setActionType( "1" );
        updateQrCodeReq.setParam3( "37213" );
        updateQrCodeReq.setTableName( "7778" );
        updateQrCodeReq.setIsActive( 1 );
        WechatPermanentQrCodeRpcService.UpdateQrCodeRes updateQrCodeRes = rpcClient.updateQrcode( updateQrCodeReq );
        System.out.println(updateQrCodeRes.getMessageParams());
    }
    @Test
    public void del(){
        String mpID = "renjianfei";
        String shopID = "76022769";
        WechatPermanentQrCodeRpcService rpcClient = super.baseRpcClient.getRpcClient( WechatPermanentQrCodeRpcService.class );
        WechatPermanentQrCodeRpcService.DelQrCodeReq delQrCodeReq = new WechatPermanentQrCodeRpcService.DelQrCodeReq();
        delQrCodeReq.setMpID( mpID );
        delQrCodeReq.setShopID( shopID );
        delQrCodeReq.setQrcodeType( "1" );
        ResultInfo resultInfo = rpcClient.delWechatQrcodeByParams( delQrCodeReq );
        System.out.println(resultInfo.getMessageParams());
    }
}
