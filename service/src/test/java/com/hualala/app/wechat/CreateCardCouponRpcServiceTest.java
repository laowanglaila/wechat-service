package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.impl.CreateCardCouponRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by renjianfei on 2017/4/19.
 */
public class CreateCardCouponRpcServiceTest extends BaseRpcTest {

    @Autowired
    private CreateCardCouponRpcServiceImpl rpcClient;

    @Override
    public void test() {
//        CreateCardCouponRpcService rpcClient = super.baseRpcClient.getRpcClient(CreateCardCouponRpcService.class);

        CreateCardCouponRpcService.CouponReqData couponReqData = new CreateCardCouponRpcService.CouponReqData();
        couponReqData.setCouponType(CouponTypeEnum.GROUPON);
        couponReqData.setDealDetail("以下锅底2选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补 凉锅、酸菜鱼锅可选）：大锅1份 12元小锅2份 16元 ");
        //头信息：商家信息
        CreateCardCouponRpcService.HeadData headData = couponReqData.getHeadData();
        headData.setMpID("doulaofangceshi");
        //设置baseInfo
        CreateCardCouponRpcService.BaseInfo baseInfo = couponReqData.getBaseInfo();
        baseInfo.getSku().setQuantity(100);
        CreateCardCouponRpcService.DateInfo dateInfo = baseInfo.getDateInfo();
        dateInfo.setType(DateInfoTypeEnum.DATE_TYPE_FIX_TIME_RANGE);
        Long currentDateTimeLong = System.currentTimeMillis();
        System.out.println("------------------------------------------------------"+currentDateTimeLong);
        int startTime = (int) (currentDateTimeLong/1000L);
        int endTime = startTime + 3600 * 24 * 7;
        System.out.println("------------------------------------------------------"+startTime);
        System.out.println("------------------------------------------------------"+endTime);
        dateInfo.setBeginTimestamp(startTime);
        dateInfo.setEndTimestamp(endTime);

        baseInfo.setBindOpenid(false);
        baseInfo.setBrandName("微信餐厅，豆捞坊测试");
        baseInfo.setCanShare(true);
        baseInfo.setCanGiveFriend(true);
        baseInfo.setCenterSubTitle("按钮下方的wording");
        baseInfo.setCenterTitle("顶部居中按钮");
        baseInfo.setCenterUrl("www.hualala.com");
        baseInfo.setCodeType(CodeTypeEnum.CODE_TYPE_QRCODE);
        baseInfo.setColor(ColorEnum.Color010);
        baseInfo.setCustomUrl("http://www.hualala.com");
        baseInfo.setCustomUrlName("立即使用");
        baseInfo.setCenterSubTitle("6个汉字tips");
        baseInfo.setDescription("不可与其他优惠同享如需团购券发票，请在消费时向商户提出店内均可使用，仅限堂食");
        baseInfo.setGetLimit(3);
        baseInfo.setLogoUrl("http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0");
        baseInfo.setNotice("使用时向服务员出示此券");
        baseInfo.setPromotionUrl("http://www.hualala.com");
        baseInfo.setPromotionUrlName("更多优惠");
        baseInfo.setServicePhone("17614430096");
        baseInfo.setSource("哗啦啦微信门店");
        baseInfo.setTitle("哗啦啦尊享火锅套餐");
        baseInfo.setUseCustomCode(false);
        baseInfo.setUseLimit(99);
        baseInfo.setUseAllLocations(true);
        CreateCardCouponRpcService.CardCouponResData coupon = rpcClient.createCoupon(couponReqData);
        String s = JSONObject.toJSONString(coupon);
        System.out.println(s);
    }
}
