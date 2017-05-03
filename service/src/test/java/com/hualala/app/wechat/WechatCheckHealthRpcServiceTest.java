package com.hualala.app.wechat;

/**
 * Created by renjianfei on 2017/5/2.
 */
public class WechatCheckHealthRpcServiceTest extends BaseRpcTest {
    @Override
    public void test() {
        long requsetTime = System.currentTimeMillis();
        CheckHealthRpcService rpcClient = super.baseRpcClient.getRpcClient(CheckHealthRpcService.class);
        CheckHealthRpcService.CheckHealthReqData checkHealthReqData = new CheckHealthRpcService.CheckHealthReqData();
        CheckHealthRpcService.CheckHealthResData check = rpcClient.check(checkHealthReqData);
        Long responseTime = check.getResponseTime();
        System.out.println(responseTime-requsetTime+"ms");
    }
}
