package com.hualala.app.wechat.impl.mp;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.MpShopsRpcService;
import com.hualala.app.wechat.common.ErrorCodes;
import org.springframework.util.Assert;

import java.util.ArrayList;

/**
 * Created by renjianfei on 2017/9/20.
 */

public class MpShopsRpcServiceImplTest extends BaseRpcTest {
    @Override
    public void test() throws InterruptedException {
        MpShopsRpcService rpcClient = super.baseRpcClient.getRpcClient( MpShopsRpcService.class );
        MpShopsRpcService.BindShopsReqData bindShopsReqData = new MpShopsRpcService.BindShopsReqData();
        bindShopsReqData.setGroupID( 1123L );
        bindShopsReqData.setMpID( "123" );
        ArrayList <Long> objects = new ArrayList <>();
        objects.add( 1213L );
        bindShopsReqData.setShopIDs( objects );
        MpShopsRpcService.BindShopsResData bindShopsResData = rpcClient.bindShops( bindShopsReqData );
        Assert.isTrue( bindShopsResData.getCode().equals( ErrorCodes.WECHAT_SUCCESS_CODE ) );
    }
}
