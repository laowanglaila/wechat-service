package com.hualala.app.wechat.config.client;

import com.hualala.core.grpc.GrpcClient;

/**
 * Created by renjianfei on 2017/10/21.
 */
public class UserClient extends GrpcClient {
    public UserClient(String target) {
        super( target );
    }
}
