package com.hualala.app.wechat;


import com.hualala.core.rpc.RpcProtoGenerator;

/**
 * Created by xiangbin on 2016/10/20.
 */
public class ProtoGenerator {

    @org.junit.Test
    public void generatorProto() {
        Class[] generatorClass = new Class[]{
//                CardGiveOutRpcService.class,
//                JsApiSignRpcService.class,
//                CardSignRpcService.class,
//                MsgPublishRpcService.class,
//                CardEventProcessRpcService.class,
//                CardCodeRpcService.class,
//                CardUpdateRpcService.class,
//                CardDeleteRpcService.class,
//                CardEventRpcService.class,
//                CardSyncRpcService.class,
//                CardCodeRpcService.class,
//                CardPrePareQueryRpcService.class,
//                CardPrePareCreateRpcService.class,
//                CreateCardCouponRpcService.class,
//                WechatQRCodeRpcSerivce.class,
//                WechatMessageRpcService.class,
//                WebAuthorizationRpcService.class,
//                UserGetUserInfoRpcService.class,
//                ComponentTokenRpcService.class,
                UserAuthorizationRpcService.class
//                CardStatusEnum.class,
//                ColorEnum.class,
//                CouponTypeEnum.class,
//                DateInfoTypeEnum.class,
//                CustomCodeModeEnum.class,
//                CodeTypeEnum.class,
//                TimelimitTypeEnum.class,
//                MpTypeEnum.class,
//                WechatQRTypeEnum.class,
//                WechatTemplateTypeEnum.class,
//                WechatMessageEnum.class,
//                LangTypeEnum.class

        };
        RpcProtoGenerator.generate(generatorClass);
    }
}
