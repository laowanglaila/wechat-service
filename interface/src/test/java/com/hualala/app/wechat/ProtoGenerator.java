package com.hualala.app.wechat;


import com.hualala.core.rpc.RpcProtoGenerator;

/**
 * Created by xiangbin on 2016/10/20.
 */
public class ProtoGenerator {

    @org.junit.Test
    public void generatorProto() {
        Class[] generatorClass = {MpTypeEnum.class, MpInfoService.class
            };
        RpcProtoGenerator.generate(generatorClass);
    }
}
