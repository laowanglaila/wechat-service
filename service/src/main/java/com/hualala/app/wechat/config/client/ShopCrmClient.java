package com.hualala.app.wechat.config.client;

import com.hualala.core.grpc.GrpcClient;

/**
 * Created by renjianfei on 2017/6/26.
 */
public class ShopCrmClient extends GrpcClient {

    public ShopCrmClient(String target) {
        super(target);
    }
}
