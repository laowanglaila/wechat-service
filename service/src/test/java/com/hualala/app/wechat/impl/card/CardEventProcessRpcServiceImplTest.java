package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.crm.bean.cardChannel.CardChannelReq;
import com.hualala.app.crm.bean.cardChannel.CardChannelRes;
import com.hualala.app.crm.bean.giftDetailChannel.GiftDetailChannelReq;
import com.hualala.app.crm.bean.giftDetailChannel.GiftDetailChannelRes;
import com.hualala.app.crm.service.CardChannelService;
import com.hualala.app.crm.service.GiftDetailChannelService;
import com.hualala.app.wechat.BaseRpcTest;
import org.junit.Test;

/**
 * Created by renjianfei on 2017/7/20.
 */
public class CardEventProcessRpcServiceImplTest extends BaseRpcTest {

    @Override
    public void test() {

    }

    /**
     * 绑定会员渠道信息
     */
    @Test
    public void testaddCardChannel(){
        CardChannelService rpcClient = super.baseRpcClient.getRpcClient(CardChannelService.class);
        CardChannelReq cardChannelReq = new CardChannelReq();
        cardChannelReq.setCardID(730353551578210304L);
        cardChannelReq.setGroupID(8L);
        cardChannelReq.setWechatCardCode("312160172886");
        cardChannelReq.setWechatCardKey(643958577561181608L);
        CardChannelRes cardChannelRes = rpcClient.addCardChannel(cardChannelReq);
        Object[] messageParams = cardChannelRes.getMessageParams();
        String s = JSONObject.toJSONString(messageParams);
        System.out.println(s);
    }
    /**
     * 绑定优惠券渠道信息
     */
    @Test
    public void testaddCouponChannel(){
        GiftDetailChannelService rpcClient = baseRpcClient.getRpcClient(GiftDetailChannelService.class);
        GiftDetailChannelReq giftDetailChannelReq = new GiftDetailChannelReq();
        giftDetailChannelReq.setCustomerGiftDetailID(2313L);
        giftDetailChannelReq.setGroupID(1155L);
        giftDetailChannelReq.setCustomerID(5L);
        giftDetailChannelReq.setWechatCardCode("12364");
        giftDetailChannelReq.setWechatCardKey(4L+"");
        GiftDetailChannelRes giftDetailChannelRes = rpcClient.addGiftDetailChannel(giftDetailChannelReq);
        System.out.println(giftDetailChannelRes);
    }
}