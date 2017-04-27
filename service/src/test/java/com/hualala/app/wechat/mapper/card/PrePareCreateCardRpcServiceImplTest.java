package com.hualala.app.wechat.mapper.card;

import com.hualala.app.wechat.PrePareCreateCardRpcService;
import com.hualala.app.wechat.impl.card.PrePareCreateCardRpcServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

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
        PrePareCreateCardRpcService.PreCouponReqData couponReqData = new PrePareCreateCardRpcService.PreCouponReqData();
//        couponReqData.setMpID("doulaofangceshi");
        couponReqData.setBrandID(5L);
        couponReqData.setGroupID(5L);
        couponReqData.setShopID(5L);
        couponReqData.setCardType("GROUPON");
        couponReqData.setTitle("豆捞坊金牌团购");
        couponReqData.setDealDetail("dealDetail");
        couponReqData.setDefaultDetail("defaultDetail");
        couponReqData.setDiscount(10);
        couponReqData.setGift("gift");
        couponReqData.setLeastCost(100);
        couponReqData.setReduceCost(10);
        couponReqData.setCardKey("123sdvfxgazfg");
        PrePareCreateCardRpcService.PreCardResData coupon = prePareCreateCardRpcService.createCoupon(couponReqData);

        System.out.println("--------------------------------"+coupon.toString());
    }
    @Test
    public void testBaseInfo(){

        PrePareCreateCardRpcService.PreCardBaseInfoData preCardBaseInfoData = new PrePareCreateCardRpcService.PreCardBaseInfoData();
        preCardBaseInfoData.setBrandName("海底捞");
        preCardBaseInfoData.setBindOpenid(true);
        preCardBaseInfoData.setCanGiveFriend(true);
        preCardBaseInfoData.setCanShare(true);
        preCardBaseInfoData.setCardKey("123sdvfxgazfg");
        preCardBaseInfoData.setCenterSubTitle("CenterSubTitle");
        preCardBaseInfoData.setCenterTitle("CenterTitle");
        preCardBaseInfoData.setCenterUrl("CenterUrl");
        preCardBaseInfoData.setCodeType("CODE_TYPE_QRCODE");
        preCardBaseInfoData.setColor("Color10");
        preCardBaseInfoData.setSku(100);
        preCardBaseInfoData.setNotice("Notice");
        PrePareCreateCardRpcService.PreCardResData baseInfo = prePareCreateCardRpcService.createBaseInfo(preCardBaseInfoData);
        System.out.println("-------------------------------"+baseInfo);
    }
    @Test
    public void testAdvancedInfo(){

        PrePareCreateCardRpcService.PreAdvancedInfoData preAdvancedInfoData = new PrePareCreateCardRpcService.PreAdvancedInfoData();
        preAdvancedInfoData.setCardKey("123sdvwfxgazfg");
        preAdvancedInfoData.setAbstractInfo("Json");
        PrePareCreateCardRpcService.PreCardResData advancedInfo = prePareCreateCardRpcService.createAdvancedInfo(preAdvancedInfoData);
        System.out.println("-------------------------------"+advancedInfo);
    }

    /**
     * 插入一条完整的卡券信息
     */
    @Test
    public void testInsertCoupon(){

    PrePareCreateCardRpcService.PreCouponReqData couponData = new PrePareCreateCardRpcService.PreCouponReqData();
    //头信息：商家信息
    couponData.setTitle("哗啦啦尊享火锅套餐");
    couponData.setMpID("doulaofangceshi");
    couponData.setDealDetail("以下锅底2选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补 凉锅、酸菜鱼锅可选）：大锅1份 12元小锅2份 16元 ");
    couponData.setCardKey("sffg7896aerzcvgadsg123");
    couponData.setCardType("GROUPON");
    //设置baseInfo
    PrePareCreateCardRpcService.PreCardBaseInfoData baseInfo = new PrePareCreateCardRpcService.PreCardBaseInfoData();
    baseInfo.setSku(100);
    //Json
    Long currentDateTimeLong = System.currentTimeMillis();
    Integer startTime =  (int)(currentDateTimeLong/1000L);
    Integer endTime = startTime + 3600 * 24 * 7;
    baseInfo.setDateInfo("{\"begin_timestamp\" : "+startTime+",\"end_timestamp\" : "+endTime+",\n\"type\" : \"DATE_TYPE_FIX_TIME_RANGE\"}");
    baseInfo.setBindOpenid(false);
    baseInfo.setBrandName("微信餐厅，豆捞坊测试");
    baseInfo.setCanShare(true);
    baseInfo.setCanGiveFriend(true);
    baseInfo.setCenterSubTitle("按钮下方的wording");
    baseInfo.setCenterTitle("顶部居中按钮");
    baseInfo.setCenterUrl("www.hualala.com");
    baseInfo.setCodeType("CODE_TYPE_QRCODE");
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

    baseInfo.setUseCustomCode(false);
    baseInfo.setUseLimit(0);
    baseInfo.setUseAllLocations(false);
    ArrayList<Integer> locationIdList = new ArrayList<>();
    locationIdList.add(218384742);
    //Json
    baseInfo.setLocationIdList("123, 12321, 345345");

    //高级
    PrePareCreateCardRpcService.PreAdvancedInfoData advancedInfo = new PrePareCreateCardRpcService.PreAdvancedInfoData();

    advancedInfo.setAbstractInfo("{\"abstract\" : \"微信餐厅推出多种新季菜品，期待您的光临\",\"icon_url_list\" : [\"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj  piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\"]}");
    advancedInfo.setBusinessService("BIZ_SERVICE_FREE_WIFI, BIZ_SERVICE_WITH_PET, BIZ_SERVICE_FREE_PARK, BIZ_SERVICE_DELIVER");
    advancedInfo.setTextImage("[{\"image_url\" : \"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sjpiby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\",\"text\" : \"此菜品精选食材，以独特的烹饪方法，最大程度地刺激食 客的味蕾\"},{\"image_url\" : \"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\",\"text\" : \"此菜品迎合大众口味，老少皆宜，营养均衡\"}]");
    advancedInfo.setTimeLimit("[" +
            "   {" +
            "      \"begin_hour\" : 0," +
            "      \"begin_minute\" : 10," +
            "      \"end_hour\" : 10," +
            "      \"end_minute\" : 59," +
            "      \"type\" : \"MONDAY\"" +
            "   }," +
            "   {" +
            "      \"type\" : \"HOLIDAY\"" +
            "   }" +
            "]");
    advancedInfo.setUseCodition("{" +
            "   \"accept_category\" : \"鞋类\"," +
            "   \"can_use_with_other_discount\" : true," +
            "   \"reject_category\" : \"阿迪达斯\"" +
            "}");


    PrePareCreateCardRpcService.PreCardResData resInfo = prePareCreateCardRpcService.createCoupon(couponData);
    String cardKey = resInfo.getCardKey();
    baseInfo.setCardKey(cardKey);
    advancedInfo.setCardKey(cardKey);
    prePareCreateCardRpcService.createBaseInfo(baseInfo);
    prePareCreateCardRpcService.createAdvancedInfo(advancedInfo);

}

    @Test
    public void testSubmitCoupon(){
        PrePareCreateCardRpcService.CardPrimaryKey cardPrimaryKey = new PrePareCreateCardRpcService.CardPrimaryKey();
        cardPrimaryKey.setCardKey("sffg7896aerzcvgadsg123");
        PrePareCreateCardRpcService.PreCardResData preCardResData = prePareCreateCardRpcService.submitCouponInfo(cardPrimaryKey);
        System.out.println(preCardResData);
    }

    @Test
    public void testMemberInfo(){
        PrePareCreateCardRpcService.PreMemberReqData preMemberReqData = new PrePareCreateCardRpcService.PreMemberReqData();
//        couponReqData.setMpID("doulaofangceshi");
        preMemberReqData.setBrandID(5L);
        preMemberReqData.setGroupID(5L);
        preMemberReqData.setShopID(5L);
        preMemberReqData.setCardType("GROUPON");
        preMemberReqData.setTitle("豆捞坊金牌团购");
        preMemberReqData.setCardKey("asasdfdfa1asd23123dasdfgasd");
        preMemberReqData.setAutoActivate(true);
        preMemberReqData.setBackgroundPicUrl("url");
        preMemberReqData.setBalanceUrl("url");
        preMemberReqData.setSupplyBonus(true);
        preMemberReqData.setBonusRule("json");
        preMemberReqData.setCustomCell1("Json");
        preMemberReqData.setCustomField1("json");
        preMemberReqData.setCustomField2("json");
        preMemberReqData.setCustomField3("json");
        preMemberReqData.setPrerogative("Prerogative");
        preMemberReqData.setWxActivate(true);
        preMemberReqData.setBonusUrl("url");
        preMemberReqData.setSupplyBalance(true);
        preMemberReqData.setDiscount(10);
        PrePareCreateCardRpcService.PreCardResData coupon = prePareCreateCardRpcService.createMemberCard(preMemberReqData);

        System.out.println("--------------------------------"+coupon.toString());
    }

    /**
     * 插入一条完整的会员卡信息
     */
    @Test
    public void testInsertMember(){

        PrePareCreateCardRpcService.PreMemberReqData couponData = new PrePareCreateCardRpcService.PreMemberReqData();
        //头信息：商家信息
        couponData.setTitle("哗啦啦会员");
        couponData.setMpID("doulaofangceshi");
        couponData.setCardKey("123aerzc12adssg123");
        couponData.setCardType("MEMBER_CARD");
        couponData.setPrerogative("asdfasdf");

        couponData.setCustomCell1("{\n" +
                "   \"name\" : \"使用入口2\",\n" +
                "   \"tips\" : \"激活后显示\",\n" +
                "   \"url\" : \"http://www.xxx.com\"\n" +
                "}\n");
        couponData.setCustomField1("{\n" +
                "   \"name_type\" : \"FIELD_NAME_TYPE_LEVEL\",\n" +
                "   \"url\" : \"http://www.qq.com\"\n" +
                "}\n");
        //设置baseInfo
        PrePareCreateCardRpcService.PreCardBaseInfoData baseInfo = new PrePareCreateCardRpcService.PreCardBaseInfoData();
        baseInfo.setSku(100);
        //Json
        Long currentDateTimeLong = System.currentTimeMillis();
        Integer startTime =  (int)(currentDateTimeLong/1000L);
        Integer endTime = startTime + 3600 * 24 * 7;
        baseInfo.setDateInfo("{\"begin_timestamp\" : "+startTime+",\"end_timestamp\" : "+endTime+",\n\"type\" : \"DATE_TYPE_FIX_TIME_RANGE\"}");
        baseInfo.setBindOpenid(false);
        baseInfo.setBrandName("微信餐厅，豆捞坊测试");
        baseInfo.setCanShare(true);
        baseInfo.setCanGiveFriend(true);
        baseInfo.setCenterSubTitle("按钮下方的wording");
        baseInfo.setCenterTitle("顶部居中按钮");
        baseInfo.setCenterUrl("www.hualala.com");
        baseInfo.setCodeType("CODE_TYPE_QRCODE");
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

        baseInfo.setUseCustomCode(false);
        baseInfo.setUseLimit(0);
        baseInfo.setUseAllLocations(false);
        ArrayList<Integer> locationIdList = new ArrayList<>();
        locationIdList.add(218384742);
        //Json
        baseInfo.setLocationIdList("123, 12321, 345345");

        //高级
        PrePareCreateCardRpcService.PreAdvancedInfoData advancedInfo = new PrePareCreateCardRpcService.PreAdvancedInfoData();

        advancedInfo.setAbstractInfo("{\"abstract\" : \"微信餐厅推出多种新季菜品，期待您的光临\",\"icon_url_list\" : [\"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj  piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\"]}");
        advancedInfo.setBusinessService("BIZ_SERVICE_FREE_WIFI, BIZ_SERVICE_WITH_PET, BIZ_SERVICE_FREE_PARK, BIZ_SERVICE_DELIVER");
        advancedInfo.setTextImage("[{\"image_url\" : \"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sjpiby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\",\"text\" : \"此菜品精选食材，以独特的烹饪方法，最大程度地刺激食 客的味蕾\"},{\"image_url\" : \"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\",\"text\" : \"此菜品迎合大众口味，老少皆宜，营养均衡\"}]");
        advancedInfo.setTimeLimit("[" +
                "   {" +
                "      \"begin_hour\" : 0," +
                "      \"begin_minute\" : 10," +
                "      \"end_hour\" : 10," +
                "      \"end_minute\" : 59," +
                "      \"type\" : \"MONDAY\"" +
                "   }," +
                "   {" +
                "      \"type\" : \"HOLIDAY\"" +
                "   }" +
                "]");
        advancedInfo.setUseCodition("{" +
                "   \"accept_category\" : \"鞋类\"," +
                "   \"can_use_with_other_discount\" : true," +
                "   \"reject_category\" : \"阿迪达斯\"" +
                "}");


        PrePareCreateCardRpcService.PreCardResData resInfo = prePareCreateCardRpcService.createMemberCard(couponData);
        String cardKey = resInfo.getCardKey();
        baseInfo.setCardKey(cardKey);
        advancedInfo.setCardKey(cardKey);
        prePareCreateCardRpcService.createBaseInfo(baseInfo);
        prePareCreateCardRpcService.createAdvancedInfo(advancedInfo);

    }

    @Test
    public void testSubmitMember(){
        PrePareCreateCardRpcService.CardPrimaryKey cardPrimaryKey = new PrePareCreateCardRpcService.CardPrimaryKey();
        cardPrimaryKey.setCardKey("123aerzc12adsg123");
        PrePareCreateCardRpcService.PreCardResData preCardResData = prePareCreateCardRpcService.submitMemberInfo(cardPrimaryKey);
        System.out.println(preCardResData);
    }

}
