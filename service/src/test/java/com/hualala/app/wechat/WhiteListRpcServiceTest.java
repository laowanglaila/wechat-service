package com.hualala.app.wechat;

/**
 * Created by renjianfei on 2017/4/14.
 */
public class WhiteListRpcServiceTest extends BaseRpcTest {
    @Override
    public void test() {

        WhiteListRpcService rpcClient = baseRpcClient.getRpcClient(WhiteListRpcService.class);
        WhiteListRpcService.ReqData reqData = new WhiteListRpcService.ReqData();
        reqData.setMpID("doulaofang");
        reqData.setUserName(new String[]{"renfeifei_1314"});
        rpcClient.addToWhiteList(reqData);

    }
}
