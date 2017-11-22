package com.hualala.app.wechat;

import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * Created by renjianfei on 2017/10/13.
 */
public class AuthorizationCheckRpcServiceImplTest extends BaseRpcTest {
    @Resource(name = "skuRedisTemplate")
    private StringRedisTemplate skuRedisTemplate;
    @Override
    public void test() throws InterruptedException {
        String mpID = "AfTiEFjiRU0897bd";
//        skuRedisTemplate.delete( RedisKeys.WEHCHAT_MPINFO_KEY + mpID );
        AuthorizationCheckRpcService rpcClient = baseRpcClient.getRpcClient( AuthorizationCheckRpcService.class );
        AuthorizationCheckRpcService.AuthorizationCheckReq authorizationCheckReq = new AuthorizationCheckRpcService.AuthorizationCheckReq();
        authorizationCheckReq.setMpID( mpID );
        authorizationCheckReq.setInterfaceType( WechatFuctionEnum.TEMPORARY_QR_CODE );
        AuthorizationCheckRpcService.AuthorizationCheckRes check = rpcClient.check( authorizationCheckReq );
        System.out.println(check);
    }
}
