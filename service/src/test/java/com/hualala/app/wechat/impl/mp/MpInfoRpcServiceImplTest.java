package com.hualala.app.wechat.impl.mp;

import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.MpInfoRpcService;

/**
 * Created by renjianfei on 2017/10/16.
 */
public class MpInfoRpcServiceImplTest extends BaseRpcTest {
    @Override
    public void test() throws InterruptedException {
        MpInfoRpcService rpcClient = super.baseRpcClient.getRpcClient( MpInfoRpcService.class );
        MpInfoRpcService.MpInfoSelectReqData mpInfoSelectReqData = new MpInfoRpcService.MpInfoSelectReqData();
        mpInfoSelectReqData.setMpID( "" );
        MpInfoRpcService.MpInfoQueryResData mpInfoQueryResData = rpcClient.selectMp( mpInfoSelectReqData );
        System.out.println(mpInfoQueryResData.getMpInfoResDataList());

    }
}
