package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renjianfei on 2017/4/19.
 */
public class CreateCardCouponRpcServiceTest extends BaseRpcTest {

//    @Autowired
//    private CreateCardCouponRpcServiceImpl rpcClient;

    @Override
    public void test() {
        CreateCardCouponRpcService rpcClient = super.baseRpcClient.getRpcClient(CreateCardCouponRpcService.class);

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
        Integer startTime =  (int)(currentDateTimeLong/1000L);
        Integer endTime = startTime + 3600 * 24 * 7;
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
        baseInfo.setColor("Color010");
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
        baseInfo.setUseLimit(0);
        baseInfo.setUseAllLocations(false);
        ArrayList<Integer> locationIdList = new ArrayList<>();
        locationIdList.add(218384742);
        baseInfo.setLocationIdList(locationIdList);

        //设置高级信息
        CreateCardCouponRpcService.AdvancedInfo advancedInfo = couponReqData.getAdvancedInfo();

        CreateCardCouponRpcService.Abstract abstractObj = advancedInfo.getAbstractObj();
        abstractObj.setCoverAbstract("微信餐厅推出多种新季菜品，期待您的光临");
        ArrayList<String> iconUrl = new ArrayList<>();
        iconUrl.add("http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sjpiby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0");
        abstractObj.setIconUrlList(iconUrl);

        ArrayList<String> businessServiceList = new ArrayList<>();
        businessServiceList.add("BIZ_SERVICE_FREE_WIFI");
        businessServiceList.add("BIZ_SERVICE_WITH_PET");
        businessServiceList.add("BIZ_SERVICE_FREE_PARK");
        businessServiceList.add("BIZ_SERVICE_DELIVER");
        advancedInfo.setBusinessServiceList(businessServiceList);

        List<CreateCardCouponRpcService.TextImage> textImageList = new ArrayList<>();
        CreateCardCouponRpcService.TextImage textImage = new CreateCardCouponRpcService.TextImage();
        textImage.setImageUrl("http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sjpiby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0");
        textImage.setText("此菜品精选食材，以独特的烹饪方法，最大程度地刺激食 客的味蕾");
        CreateCardCouponRpcService.TextImage textImage1 = new CreateCardCouponRpcService.TextImage();
        textImage1.setImageUrl("http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sjpiby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0");
        textImage1.setText("此菜品迎合大众口味，老少皆宜，营养均衡");
        textImageList.add(textImage);
        textImageList.add(textImage1);
        advancedInfo.setTextImageList(textImageList);

        List<CreateCardCouponRpcService.TimeLimit> timeLimitList = new ArrayList<>();
        CreateCardCouponRpcService.TimeLimit monday = new CreateCardCouponRpcService.TimeLimit();
        monday.setType("MONDAY");
        monday.setBeginHour(0);
        monday.setBeginMinute(59);
        monday.setEndHour(10);
        monday.setEndMinute(59);
        CreateCardCouponRpcService.TimeLimit holiday = new CreateCardCouponRpcService.TimeLimit();
        holiday.setType("HOLIDAY");
        timeLimitList.add(monday);
        timeLimitList.add(holiday);
        advancedInfo.setTimeLimitList(timeLimitList);

        CreateCardCouponRpcService.UseCodition useCodition = advancedInfo.getUseCodition();
        useCodition.setAcceptCategory("鞋类");
        useCodition.setCanUseWithOtherDiscount(true);
        useCodition.setRejectCategory("阿迪达斯");

        CreateCardCouponRpcService.CardCouponResData coupon = rpcClient.createCoupon(couponReqData);
        String s = JSONObject.toJSONString(coupon);
        System.out.println(s);
    }
}
