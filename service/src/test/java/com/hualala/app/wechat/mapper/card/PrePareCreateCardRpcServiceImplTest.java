package com.hualala.app.wechat.mapper.card;

import com.hualala.app.wechat.PrePareCreateCardRpcService;
import com.hualala.app.wechat.impl.card.PrePareCreateCardRpcServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by renjianfei on 2017/4/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PrePareCreateCardRpcServiceImplTest {

    @Autowired
    private PrePareCreateCardRpcServiceImpl prePareCreateCardRpcService;
    @Test
    public void test(){
        PrePareCreateCardRpcService.CouponReqData couponReqData = new PrePareCreateCardRpcService.CouponReqData();
        couponReqData.setMpID("doulaofangceshi");
        couponReqData.setCardType("GROUPON");
        couponReqData.setTitle("豆捞坊金牌团购");
        couponReqData.setCardKey("asdfasdfasdfasdfasdf");
        PrePareCreateCardRpcService.CardCouponResData coupon = prePareCreateCardRpcService.createCoupon(couponReqData);

        System.out.println("--------------------------------"+coupon.toString());
    }

}
