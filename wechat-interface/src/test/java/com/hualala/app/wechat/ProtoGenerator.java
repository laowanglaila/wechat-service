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
                CardSignRpcService.class,
//                MsgPublishRpcService.class,
//                CardEventProcessRpcService.class,
//                CardCodeRpcService.class,
//                CardUpdateRpcService.class,
//                CardDeleteRpcService.class,
//                CardEventRpcService.class,
//                CardSyncRpcService.class,
//                CardPrePareQueryRpcService.class,
//                CardPrePareCreateRpcService.class,
//                CreateCardCouponRpcService.class,
//                WechatQRCodeRpcSerivce.class,
//                WechatMessageRpcService.class,
//                WebAuthorizationRpcService.class,
//                UserGetUserInfoRpcService.class,
//                ComponentTokenRpcService.class,
//                MpShopsRpcService.class,
//                UserAuthorizationRpcService.class,
//                WechatAccessTokenRpcService.class,
//                WechatOAuthRpcService.class,
//                AuthorizationCheckRpcService.class,
//                WechatPermanentQrCodeRpcService.class,
//                MpInfoRpcService.class,
//                WechatTemplateRpcService.class,
//                WechatQrcodeLoginRpcService.class,
//                InvoiceTemplateRpcService.class,
//                InvoiceAuthorizationRpcService.class,
                InvoiceInsertCardRpcService.class,
                ColorEnum.class,
                CardStatusEnum.class,
                CouponTypeEnum.class,
                DateInfoTypeEnum.class,
                CustomCodeModeEnum.class,
                CodeTypeEnum.class,
                TimelimitTypeEnum.class,
                MpTypeEnum.class,
                WechatQRTypeEnum.class,
                WechatTemplateTypeEnum.class,
                WechatMessageEnum.class,
                LangTypeEnum.class,
                WechatFuctionEnum.class,
                TemplateItemTypeEnum.class
        };
        RpcProtoGenerator.generate(generatorClass);
    }
}
