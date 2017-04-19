package com.hualala.app.wechat.impl;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CreateCardCouponRpcService;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.enumtype.*;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by renjianfei on 2017/4/18.
 */
@Service
public class CreateCardCouponRpcServiceImpl implements CreateCardCouponRpcService {


    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private MpInfoService mpInfoService;

    /**
     * 创建卡券
     * @param couponReqData
     * @return
     */
    @Override
    public CardCouponResData createCoupon(CouponReqData couponReqData) {

        //判断mpID,没有则调方法获取
        HeadData headData = couponReqData.getHeadData();
        String mpID = headData.getMpID();
        if (StringUtils.isBlank(mpID)) {
            String brandID = headData.getBrandID();
            String groupID = headData.getGroupID();
            String shopID = headData.getShopID();
            if (StringUtils.isBlank(brandID) || StringUtils.isBlank(groupID)) {
                return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID为空并且没有提供brandID、groupID、shopID！");
            }
            //通过上面三个属性获取mpID，调用方法待定；
            mpID = mpInfoService.queryMpIDAuth(Long.parseLong(groupID), Long.parseLong(brandID), Long.parseLong(shopID));
        }
        if (StringUtils.isBlank(mpID)) {
            //返回响应对象，设置错误信息和错误码；
            return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "获取mpID失败！");
        }
        CouponTypeEnum couponType = couponReqData.getCouponType();
        if(couponType == null){
            return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_TYPE_NULL, "需要指定卡券类型！");
        }
        //根据类型设置卡券特有信息
        Map<String, Object> card = new HashMap<>();
        card.put("card_type",couponType.getName());
        Integer leastCost = null;
        Integer reduceCost = null;
        String defaultDetail = null;
        String dealDetail = null;
        String gift = null;
        Integer discount = null;
        switch (couponType){
            case CASH:
                 leastCost = couponReqData.getLeastCost();//启用门槛 -1为无门槛
                 reduceCost = couponReqData.getReduceCost();//减免金额
                if(leastCost == 0 ){
                    return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_LEAST_COST_NULL, "如需要设置无门槛代金券，门槛请设置为-1");
                }
                if(leastCost == WechatMessageType.ZERO){
                    leastCost = 0;
                }
                card.put("least_cost",leastCost);
                card.put("reduce_cost",reduceCost);
                break;
            case GROUPON:
                 dealDetail = couponReqData.getDealDetail();

                card.put("deal_detail",dealDetail);
                break;
            case GIFT:
                 gift = couponReqData.getGift();

                card.put("gift",gift);
                break;
            case DISCOUNT:
                 discount = couponReqData.getDiscount();
                card.put("discount",discount);
                break;
            case GENERAL_COUPON:
                 defaultDetail = couponReqData.getDefaultDetail();
                card.put("default_detail",defaultDetail);
        }
        // BaseInfo设置卡券基本信息
        BaseInfo baseInfo = couponReqData.getBaseInfo();
         card = setBaseInfo(card, baseInfo);
// TODO AdvancedInfo设置高级信息

        //请求微信
        Map<String, Object> params = new HashMap<>();
        params.put("card",card);
        JSONObject.toJSONString(params);
        JSONObject jsonObject = baseHttpService.createCardAndCoupon(params, mpID);
        CardCouponResData resultInfoBean = ResultUtil.getResultInfoBean(jsonObject, CardCouponResData.class);
        return resultInfoBean;
    }


    /**
     * 将接受到的baseInfo设置到card中
     * @param card
     * @param baseInfo
     * @return card
     */
    private Map<String, Object> setBaseInfo(Map<String, Object> card, BaseInfo baseInfo) {
        Map<Object, Object> baseInfoMap = new HashMap<>();
        //sku
        Sku sku = baseInfo.getSku();
        Integer quantity = sku.getQuantity();
        if (quantity == 0 ){
            return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_QUANTITY_NULL, "库存不能为0");
        }
        Map<String, Object> skuMap = new HashMap<>();
        skuMap.put("quantity",quantity);
        baseInfoMap.put("sku",skuMap);
        //dateInfo
        DateInfo dateInfo = baseInfo.getDateInfo();
        DateInfoTypeEnum type = dateInfo.getType();
        if(type == null){
            return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_DATE_TYPE_NULL, "有效时间类型不能为空！");
        }
        Map<String, Object> dateInfoMap = new HashMap<>();
        dateInfoMap.put("type",type.getName());
        switch (type){
            case DATE_TYPE_PERMANENT:
                break;
            case DATE_TYPE_FIX_TERM:
                dateInfoMap.put("fixed_begin_term",dateInfo.getFixedBeginTerm());
                dateInfoMap.put("fixed_term",dateInfo.getEndTimestamp());
                break;
            case DATE_TYPE_FIX_TIME_RANGE:
                dateInfoMap.put("begin_timestamp",dateInfo.getBeginTimestamp());
                dateInfoMap.put("end_timestamp",dateInfo.getEndTimestamp());
        }
        baseInfoMap.put("dateInfo",dateInfoMap);

        //baseInfo其他信息（必填）
        //        logo_url 	             是 	string(128) 	http://mmbiz.qpic.cn/ 	卡券的商户logo，建议像素为300*300。
        String logoUrl = baseInfo.getLogoUrl();
        if (StringUtils.isBlank(logoUrl)){
            return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_LOGO_NULL, "LogoUrl不能为空！");
        }
        baseInfoMap.put("logo_url",logoUrl);
        //        code_type 	         是 	string(16) 	    CODE_TYPE_TEXT 	Code展示类型，"CODE_TYPE_TEXT"，文本；"CODE_TYPE_BARCODE"，一维码 ；"CODE_TYPE_QRCODE"，二维码；"CODE_TYPE_ONLY_QRCODE",二维码无code显示；"CODE_TYPE_ONLY_BARCODE",一维码无code显示；
        CodeTypeEnum codeType = baseInfo.getCodeType();
        if (null == codeType){
            return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "codeType不能为空！");
        }
        baseInfoMap.put("code_type",codeType.getName());
        //        brand_name             是 	string（36） 	海底捞 	商户名字,字数上限为12个汉字。
        String brandName = baseInfo.getBrandName();
        if (StringUtils.isBlank(brandName)){
            return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "brandName不能为空！");
        }
        baseInfoMap.put("brand_name",brandName);
        //        title 	             是 	string（27） 	双人套餐100元兑换券 	卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。
        String title = baseInfo.getTitle();
        if (StringUtils.isBlank(title)){
            return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "title不能为空！");
        }
        baseInfoMap.put("title",title);
        //        color 	             是 	string（16） 	Color010 	券颜色。按色彩规范标注填写Color010-Color100。详情见获取颜色列表接口
        ColorEnum color = baseInfo.getColor();
        if (color == null){
            return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_COLOR_NULL, "color不能为空！");
        }
        baseInfoMap.put("color",color.getName());
        //        notice 	             是 	string（48） 	请出示二维码核销卡券 	卡券使用提醒，字数上限为16个汉字。
        String notice = baseInfo.getNotice();
        if (StringUtils.isBlank(notice)){
            return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_NOTICE_NULL, "notice不能为空！");
        }
        baseInfoMap.put("notice",notice);
        //        description            是 	string（3072） 	不可与其他优惠同享/n如需团购券发票，请向店员提出要求。 	卡券使用说明，字数上限为1024个汉字。
        String description = baseInfo.getDescription();
        if (StringUtils.isBlank(description)){
            return new CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_DESCRIPTION_NULL, "description不能为空！");
        }
        baseInfoMap.put("description",description);

        //baseInfo其他信息（选填）
        //        use_custom_code        否 	bool 	        true 	是否自定义Code码。填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，在卡券投放时带入。
        baseInfoMap.put("use_custom_code",baseInfo.getUseCustomCode());
        //        bind_openid 	         否 	bool 	        true 	是否指定用户领取，填写true或false。默认为false。
        baseInfoMap.put("bind_openid",baseInfo.getBindOpenid());
        //        service_phone 	     否 	string（24） 	40012234 	客服电话。
        String servicePhone = baseInfo.getServicePhone();
        if (StringUtils.isNotBlank(servicePhone)){
            baseInfoMap.put("service_phone",servicePhone);
        }
        //-----------------------------------------------------------------------
        //        location_id_list 	     否 	array 	        1234，2312 	门店位置ID。调用POI门店管理接口获取门店位置ID。
        String locationIdList = baseInfo.getLocationIdList();
        if (StringUtils.isNotBlank(locationIdList)){
            String[] split = locationIdList.split(", ");
            baseInfoMap.put("location_id_list",split);
        }
        //-----------------------------------------------------------------------
        //        source 	             否 	string（36） 	大众点评 	第三方来源名，例如同程旅游、大众点评。
        String source = baseInfo.getSource();
        if (StringUtils.isNotBlank(source)){
            baseInfoMap.put("source",source);
        }
        //        customUrl_name 	     否 	string（15） 	立即使用 	自定义跳转外链的入口名字。
        String customUrlName = baseInfo.getCustomUrlName();
        if (StringUtils.isNotBlank(customUrlName)){
            baseInfoMap.put("custom_url_name",customUrlName);
        }
        //        custom_url 	         否 	string（128） 	"xxxx.com" 	自定义跳转的URL。
        String customUrl = baseInfo.getCustomUrl();
        if (StringUtils.isNotBlank(customUrl)){
            baseInfoMap.put("custom_url",customUrl);
        }
        //        custom_url_sub_title 	 否 	string（18） 	更多惊喜 	显示在入口右侧的提示语。
        String customUrlSubTitle = baseInfo.getCustomUrlSubTitle();
        if (StringUtils.isNotBlank(customUrlSubTitle)){
            baseInfoMap.put("custom_url_sub_title",customUrlSubTitle);
        }
        //        promotion_url_name 	 否 	string（15） 	产品介绍 	营销场景的自定义入口名称。
        String promotionUrlName = baseInfo.getPromotionUrlName();
        if (StringUtils.isNotBlank(promotionUrlName)){
            baseInfoMap.put("promotion_url_name",promotionUrlName);
        }
        //        promotion_url 	     否 	string（128） 	XXXX.com 	入口跳转外链的地址链接。
        String promotionUrl = baseInfo.getPromotionUrl();
        if (StringUtils.isNotBlank(promotionUrl)){
            baseInfoMap.put("promotion_url",promotionUrl);
        }
        //        promotion_url_sub_title否 	string（18） 	卖场大优惠。 	显示在营销入口右侧的提示语。
        String promotionUrlSubTitle = baseInfo.getPromotionUrlSubTitle();
        if (StringUtils.isNotBlank(promotionUrlSubTitle)){
            baseInfoMap.put("romotion_url_sub_title",promotionUrlSubTitle);
        }
        //        get_limit 	         否 	int 	        1 	每人可领券的数量限制。默认值为50。
        Integer getLimit = baseInfo.getGetLimit();
        if(null != getLimit && getLimit != 0){
            if (getLimit == WechatMessageType.ZERO){
                getLimit = 0;
            }
            baseInfoMap.put("get_limit",getLimit);
        }
        //        can_share 	         否 	bool 	        false 	卡券领取页面是否可分享。
        baseInfoMap.put("can_share",baseInfo.getCanShare());
        //        can_give_friend 	     否 	bool 	        false 	卡券是否可转赠。
        baseInfoMap.put("can_give_friend",baseInfo.getCanGiveFriend());
        //        need_push_on_view 	 否 	bool 	        false 	填写true为用户点击进入会员卡时推送事件，默认为false。详情见进入会员卡事件推送
        baseInfoMap.put("need_push_on_view",baseInfo.getNeedPushOnView());

        //        get_custom_code_mode	否	string(32)      GET_CUSTOM_COD E_MODE_DEPOSIT 填入 GET_CUSTOM_CODE_MODE_DEPOSIT
        //                                                  表示该卡券为预存code模式卡券，须导入超过库存数目的自定义code后方可投放，填入该字段后，
        //                                                  quantity字段须为0,须导入code后再增加库存
        CustomCodeModeEnum getCustomCodeMode = baseInfo.getGetCustomCodeMode();
        if (getCustomCodeMode != null) {
            baseInfoMap.put("get_custom_code_mode", getCustomCodeMode.getName());
        }
        //        use_all_locations     否	bool	        true	设置本卡券支持全部门店，与location_id_list互斥
        baseInfoMap.put("use_all_locations",baseInfo.getUseAllLocations());
        //        center_title	        否	string（18）	立即使用卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示
        baseInfoMap.put("center_title",baseInfo.getCenterTitle());
        //        center_sub_title	    否	string（24）	立即享受优惠显示在入口下方的提示，仅在卡券状态正常(可以核销)时显示。
        baseInfoMap.put("center_sub_title",baseInfo.getCenterSubTitle());
        //        center_url	        否	string（128）	www.qq.com顶部居中的url，仅在卡券状态正常(可以核销)时显示。
        baseInfoMap.put("center_url",baseInfo.getCenterUrl());
        //        use_limit	            否	int	            100	每人可核销的数量限制,不填写默认为50。
        baseInfoMap.put("use_limit",baseInfo.getUseLimit());

        card.put("base_info",baseInfoMap);
        return card;
    }
}
