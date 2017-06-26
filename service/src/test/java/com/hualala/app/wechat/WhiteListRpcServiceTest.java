package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by renjianfei on 2017/4/14.
 */
public class WhiteListRpcServiceTest extends BaseRpcTest {
    @Override
    public void test() {

        WhiteListRpcService rpcClient = baseRpcClient.getRpcClient(WhiteListRpcService.class);
        WhiteListRpcService.ReqData reqData = new WhiteListRpcService.ReqData();
        reqData.setMpID("hualala_com");
        reqData.setUserName("renfeifei_1314");
        WhiteListRpcService.ResData resData = rpcClient.addToWhiteList(reqData);

        System.out.println(JSONObject.toJSONString(resData));

    }
}
