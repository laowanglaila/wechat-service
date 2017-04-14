package com.hualala.app.wechat.config.client;


import com.hualala.core.grpc.GrpcClient;

/**
 * Created by Corren on 2017/2/7.
 */
public class SemServiceClient extends GrpcClient {

    public SemServiceClient(String target) {
        super(target);
    }
}
