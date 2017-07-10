package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONArray;
import com.hualala.app.wechat.CardPrePareCreateRpcService;
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
public class CardPrePareCreateRpcServiceImplTest {

    @Autowired
    private CardPrePareCreateRpcServiceImpl prePareCreateCardRpcService;
    @Test
    public void test(){
        CardPrePareCreateRpcService.PreCouponReqData couponReqData = new CardPrePareCreateRpcService.PreCouponReqData();
//        couponReqData.setMpID("doulaofangceshi");

        couponReqData.setDealDetail("dealDetail");
        couponReqData.setDefaultDetail("defaultDetail");
        couponReqData.setDiscount(10);
        couponReqData.setGift("gift");
        couponReqData.setLeastCost(100);
        couponReqData.setReduceCost(10);
        CardPrePareCreateRpcService.PreCardResData coupon = prePareCreateCardRpcService.createCoupon(couponReqData);

        System.out.println("--------------------------------"+coupon.toString());
    }
    @Test
    public void testBaseInfo(){

        CardPrePareCreateRpcService.PreCardBaseInfoData preCardBaseInfoData = new CardPrePareCreateRpcService.PreCardBaseInfoData();
//        preCardBaseInfoData.setCardKey(6424712067642565637L);
        preCardBaseInfoData.setMpID("doulaofangceshi");
        preCardBaseInfoData.setTitle("玛莎2拉蒂");
        preCardBaseInfoData.setCardType("MEMBER_CARD");
        preCardBaseInfoData.setBrandName("海底捞");
        preCardBaseInfoData.setBindOpenid(true);
        preCardBaseInfoData.setCanGiveFriend(true);
        preCardBaseInfoData.setCanShare(true);
        preCardBaseInfoData.setCenterSubTitle("CenterSubTitle");
        preCardBaseInfoData.setCenterTitle("CenterTitle");
        preCardBaseInfoData.setCenterUrl("CenterUrl");
        preCardBaseInfoData.setCodeType("CODE_TYPE_QRCODE");
        preCardBaseInfoData.setColor("Color10");
        preCardBaseInfoData.setSku(100);
        preCardBaseInfoData.setNotice("Notice");
        CardPrePareCreateRpcService.PreCardResData baseInfo = prePareCreateCardRpcService.createBaseInfo(preCardBaseInfoData);
        System.out.println("-------------------------------"+baseInfo.getMessage());
    }
    @Test
    public void testAdvancedInfo(){

        CardPrePareCreateRpcService.PreAdvancedInfoData preAdvancedInfoData = new CardPrePareCreateRpcService.PreAdvancedInfoData();
        preAdvancedInfoData.setAbstractInfo("Json");
        CardPrePareCreateRpcService.PreCardResData advancedInfo = prePareCreateCardRpcService.createAdvancedInfo(preAdvancedInfoData);
        System.out.println("-------------------------------"+advancedInfo);
    }

    /**
     * 插入一条完整的卡券信息
     */
    @Test
    public void testInsertCoupon(){

    CardPrePareCreateRpcService.PreCouponReqData couponData = new CardPrePareCreateRpcService.PreCouponReqData();
    //头信息：商家信息

    //设置baseInfo
    CardPrePareCreateRpcService.PreCardBaseInfoData baseInfo = new CardPrePareCreateRpcService.PreCardBaseInfoData();
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
    baseInfo.setGetLimit(1);
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
    CardPrePareCreateRpcService.PreAdvancedInfoData advancedInfo = new CardPrePareCreateRpcService.PreAdvancedInfoData();

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


    CardPrePareCreateRpcService.PreCardResData resInfo = prePareCreateCardRpcService.createCoupon(couponData);
    Long cardKey = resInfo.getCardKey();
    baseInfo.setCardKey(cardKey);
    advancedInfo.setCardKey(cardKey);
    prePareCreateCardRpcService.createBaseInfo(baseInfo);
    prePareCreateCardRpcService.createAdvancedInfo(advancedInfo);

}

//    @Test
//    public void testSubmitCoupon(){
//        CardPrePareCreateRpcService.CardPrimaryKey cardPrimaryKey = new CardPrePareCreateRpcService.CardPrimaryKey();
//        cardPrimaryKey.setCardKey(2342343242L);
//        CardPrePareCreateRpcService.PreCardResData preCardResData = prePareCreateCardRpcService.submitCouponInfo(cardPrimaryKey);
//        System.out.println(preCardResData.getMessage());
//    }

    @Test
    public void testMemberInfo(){
        CardPrePareCreateRpcService.PreMemberReqData preMemberReqData = new CardPrePareCreateRpcService.PreMemberReqData();
//        couponReqData.setMpID("doulaofangceshi");
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
        CardPrePareCreateRpcService.PreCardResData coupon = prePareCreateCardRpcService.createMemberCard(preMemberReqData);

        System.out.println("--------------------------------"+coupon.toString());
    }

    /**
     * 插入一条完整的会员卡信息
     */
    @Test
    public void testInsertMember(){

        CardPrePareCreateRpcService.PreMemberReqData memberInfo = new CardPrePareCreateRpcService.PreMemberReqData();
        //头信息：商家信息
        memberInfo.setPrerogative("桂圆铺会员卡01");
        memberInfo.setAutoActivate(true);
        memberInfo.setSupplyBonus(true);
        memberInfo.setSupplyBalance(true);
        memberInfo.setCustomCell1("{\n" +
                "   \"name\" : \"点击有惊喜！\",\n" +
                "   \"tips\" : \"豪礼等着你！\",\n" +
                "   \"url\" : \"http://www.hualala.com\"\n" +
                "}\n");
        memberInfo.setCustomField1("{\n" +
                "   \"name_type\" : \"FIELD_NAME_TYPE_LEVEL\",\n" +
                "   \"url\" : \"http://www.hualala.com\"\n" +
                "}\n");
        //设置baseInfo
        CardPrePareCreateRpcService.PreCardBaseInfoData baseInfo = new CardPrePareCreateRpcService.PreCardBaseInfoData();
        baseInfo.setSku(100);
        //Json
        Long currentDateTimeLong = System.currentTimeMillis();
        Integer startTime =  (int)(currentDateTimeLong/1000L);
        Integer endTime = startTime + 3600 * 24 * 365;
        baseInfo.setBrandID(5L);
        baseInfo.setGroupID(1155L);
        baseInfo.setShopID(5L);
        baseInfo.setCardType("MEMBER_CARD");
        baseInfo.setTitle("桂圆铺会员卡01");
        baseInfo.setMpID("dohko1155");
        baseInfo.setDateInfo("{\"type\" : \"DATE_TYPE_PERMANENT\"}");
        baseInfo.setBindOpenid(false);
        baseInfo.setBrandName("桂源铺");
        baseInfo.setCanShare(false);
        baseInfo.setCanGiveFriend(false);
        baseInfo.setCenterTitle("CenterTitle");
        baseInfo.setCenterUrl("www.hualala.com");
        baseInfo.setCodeType("CODE_TYPE_QRCODE");
        baseInfo.setColor("Color010");
        baseInfo.setCustomUrl("http://www.hualala.com");
        baseInfo.setCustomUrlName("自定义入口");
        baseInfo.setCenterSubTitle("6个汉字tips");
        baseInfo.setDescription("会员卡描述");
        baseInfo.setGetLimit(1);
        baseInfo.setLogoUrl("http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0");
        baseInfo.setNotice("公告内容");
        baseInfo.setPromotionUrl("http://www.hualala.com");
        baseInfo.setPromotionUrlName("年中大促");
        baseInfo.setServicePhone("17614430096");
        baseInfo.setSource("lingqudaokabao");
        baseInfo.setUseCustomCode(false);
//        baseInfo.setCustomCodeMode("GET_CUSTOM_CODE_MODE_DEPOSIT");
        baseInfo.setUseLimit(0);
        baseInfo.setUseAllLocations(false);
        ArrayList<Integer> locationIdList = new ArrayList<>();
        locationIdList.add(218384742);
        String s = JSONArray.toJSONString(locationIdList);
        //Json
        baseInfo.setLocationIdList(s);

        //高级
        CardPrePareCreateRpcService.PreAdvancedInfoData advancedInfo = new CardPrePareCreateRpcService.PreAdvancedInfoData();

        advancedInfo.setAbstractInfo("{\"abstract\" : \"abstract\",\"icon_url_list\" : [\"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj  piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\"]}");
        advancedInfo.setBusinessService("BIZ_SERVICE_FREE_WIFI, BIZ_SERVICE_WITH_PET, BIZ_SERVICE_FREE_PARK, BIZ_SERVICE_DELIVER");
//        advancedInfo.setTextImage("[{\"image_url\" : \"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sjpiby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\",\"text\" : \"text此菜品精选食材，以独特的烹饪方法，最大程度地刺激食 客的味蕾\"},{\"image_url\" : \"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\",\"text\" : \"此菜品迎合大众口味，老少皆宜，营养均衡\"}]");
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
                "   \"accept_category\" : \"accept_category\"," +
                "   \"can_use_with_other_discount\" : true," +
                "   \"reject_category\" : \"reject_category\"" +
                "}");


        CardPrePareCreateRpcService.PreCardResData resInfo = prePareCreateCardRpcService.createBaseInfo(baseInfo);
        Long cardKey = resInfo.getCardKey();
        memberInfo.setCardKey(cardKey);
        prePareCreateCardRpcService.createMemberCard(memberInfo);
        advancedInfo.setCardKey(cardKey);
        prePareCreateCardRpcService.createAdvancedInfo(advancedInfo);
        System.out.println("---------------------------------------------------"+cardKey);
    }

    @Test
    public void testSubmitMember(){
        CardPrePareCreateRpcService.CardPrimaryKey cardPrimaryKey = new CardPrePareCreateRpcService.CardPrimaryKey();
        cardPrimaryKey.setCardKey(6439585775611816067L);
        CardPrePareCreateRpcService.PreCardResData preCardResData = prePareCreateCardRpcService.submitCardInfo(cardPrimaryKey);
        System.out.println(preCardResData.getMessage());
    }

}
