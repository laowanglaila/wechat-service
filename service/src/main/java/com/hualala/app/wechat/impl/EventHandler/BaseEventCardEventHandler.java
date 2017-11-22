package com.hualala.app.wechat.impl.EventHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.sdk.mp.common.WechatMessageType;
import com.hualala.app.wechat.sdk.mp.util.WechatBeanFactory;
import com.hualala.core.base.ResultInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by renjianfei on 2017/9/8.
 */
@Slf4j
public abstract class BaseEventCardEventHandler  {



    public abstract Object handler(JSONObject jsonObject);
    public void handler(JSONObject object,String event){
        try {
            Object resultInfo = this.handler( object );
            this.onComplete( resultInfo,object,event );
        }catch (Throwable throwable){
            this.onError( throwable,object );
        }

    }

    void onComplete(Object resultInfo, Object requestInfo,String event) {
        if (requestInfo != null && resultInfo instanceof ResultInfo) {
            ResultInfo result = (ResultInfo) resultInfo;
            if (log.isDebugEnabled()) {
                log.debug( JSONObject.toJSONString( result.getMessageParams() ) );
            }
            if (!"000".equals( result.getCode() )) {
                Object[] messageParams = result.getMessageParams();
                String s = JSONObject.toJSONString( messageParams );
                if (log.isErrorEnabled()) {
                    log.error( "卡券事件处理器:"+event+"-服务端执行失败:" + JSON.toJSONString( resultInfo ) + ";\n" + JSON.toJSONString( requestInfo ) );
                }
            }
        }else {
            log.info( "卡券事件处理器:"+event+"-事件:" + JSON.toJSONString( resultInfo ) + ";\n" + JSON.toJSONString( requestInfo )  );
        }
    }

     void onError(Throwable e, Object requestInfo) {
        if (log.isErrorEnabled()) {
            log.error( "卡券绑定-GRPC通信异常:" + JSON.toJSONString( requestInfo ), e );
        }
    }
    public static BaseEventCardEventHandler create(String event){

        if (WechatMessageType.EVENT_CARD_MEMBER_ACTIVE.equals( event )) {
            //激活会员卡

        } else if (WechatMessageType.EVENT_CARD_NOT_PASS_CHECK.equals( event )) {
            //未通过审核

        } else if (WechatMessageType.EVENT_CARD_PASS_CHECK.equals( event )) {
            //通过审核

        } else if (WechatMessageType.EVENT_CARD_PAY_ORDER.equals( event )) {
            //券点流水详情事件

        } else if (WechatMessageType.EVENT_CARD_SKU_REMIND.equals( event )) {
            //库存报警事件

        } else if (WechatMessageType.EVENT_CARD_UPDATE_MEMBER.equals( event )) {
            //会员卡内容更新事件

        } else if (WechatMessageType.EVENT_CARD_USER_CONSUME.equals( event )) {
            //用户核销事件

        } else if (WechatMessageType.EVENT_CARD_USER_DEL.equals( event )) {
            //用户删除事件

        } else if (WechatMessageType.EVENT_CARD_USER_ENTER.equals( event )) {
            //用户从卡券进入公众号会话事件

        } else if (WechatMessageType.EVENT_CARD_USER_GET.equals( event )) {
            //用户领取事件
            return WechatBeanFactory.getBean( CardUserGetHandler.class );
        } else if (WechatMessageType.EVENT_CARD_USER_GIFTING.equals( event )) {
            //用户转赠事件

        } else if (WechatMessageType.EVENT_CARD_USER_PAY_FROM_PAY_CELL.equals( event )) {
            //用户买单事件

        } else if (WechatMessageType.EVENT_CARD_USER_VIEW.equals( event )) {
            //用户进入会员卡事件（暂不接受压力大）

        }
        return null;
    }


}