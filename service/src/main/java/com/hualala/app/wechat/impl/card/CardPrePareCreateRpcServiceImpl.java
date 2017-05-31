package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardPrePareCreateRpcService;
import com.hualala.app.wechat.CreateCardCouponRpcService;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.mapper.WechatMpMapper;
import com.hualala.app.wechat.mapper.card.AdvancedModelMapper;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.card.CouponModelMapper;
import com.hualala.app.wechat.mapper.card.MemberModelMapper;
import com.hualala.app.wechat.model.card.AdvancedModel;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.CouponModel;
import com.hualala.app.wechat.model.card.MemberModel;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.service.card.CreateCardKeyService;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WechatNameConverterUtil;
import com.hualala.core.app.Logger;
import com.hualala.core.utils.DataUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by renjianfei on 2017/4/25.
 */
@Service
public class CardPrePareCreateRpcServiceImpl implements CardPrePareCreateRpcService {

    private Logger logger = Logger.of(CardPrePareCreateRpcServiceImpl.class);

    @Autowired
    private MpInfoService mpInfoService;
    @Autowired
    private WechatMpMapper wechatMpMapper;
    @Autowired
    private CouponModelMapper couponModelMapper;

    @Autowired
    private CreateCardKeyService createCardKeyService;

    @Override
    public PreCardResData createCoupon(PreCouponReqData preCouponReqData) {
        if (preCouponReqData.getCardKey() == null) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "参数cardKey为空！");
        }

        CouponModel couponModel = DataUtils.copyProperties(preCouponReqData, CouponModel.class);
        couponModelMapper.insertSelective(couponModel);
        PreCardResData cardCouponResData = new PreCardResData();
        cardCouponResData.setCardKey(couponModel.getCardKey());
        cardCouponResData.setResultInfo(ErrorCodes.WECHAT_SUCCESS_CODE, "请求成功");
        return cardCouponResData;
    }

    @Autowired
    private MemberModelMapper memberModelMapper;

    /**
     * 创建会员的必要信息
     *
     * @param preCardReqData
     * @return
     */
    @Override
    public PreCardResData createMemberCard(PreMemberReqData preCardReqData) {
        if (preCardReqData.getCardKey() == null) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "参数cardKey为空！");
        }
        MemberModel memberModel = DataUtils.copyProperties(preCardReqData, MemberModel.class);
        memberModelMapper.insertSelective(memberModel);

        PreCardResData cardCouponResData = new PreCardResData();
        cardCouponResData.setCardKey(memberModel.getCardKey());
        cardCouponResData.setResultInfo(ErrorCodes.WECHAT_SUCCESS_CODE, "请求成功");
        return cardCouponResData;
    }

    /**
     * 预创建基本信息
     */
    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;

    @Override
    public PreCardResData createBaseInfo(PreCardBaseInfoData preCardBaseInfoData) {

        //判断mpID,没有则调方法获取
        String mpID = preCardBaseInfoData.getMpID();
        if (StringUtils.isBlank(mpID)) {
            Long brandID = preCardBaseInfoData.getBrandID();
            Long groupID = preCardBaseInfoData.getGroupID();
            Long shopID = preCardBaseInfoData.getShopID();
            if (null == brandID || null == groupID) {
                return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID为空并且没有提供brandID、groupID、shopID！");
            }
            //通过上面三个属性获取mpID，调用方法待定；
            mpID = mpInfoService.queryMpIDAuth(groupID, brandID, shopID);
        }
        if (StringUtils.isBlank(mpID)) {
            //返回响应对象，设置错误信息和错误码；
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "获取mpID失败！");
        }
        //groupID
        Long groupID = null;
        groupID = preCardBaseInfoData.getGroupID();
        if (groupID == null) {
            Map<String, Object> params = new HashMap<>();
            params.put("mpID", mpID);
            List<Map<String, Object>> maps = wechatMpMapper.queryByParams(params);
            if (maps.size() > 0) {
                Integer groupID1 = (Integer) maps.get(0).get("groupID");
                groupID = groupID1.longValue();
            } else {
                return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_GROUP_ID_NULL, "获取GroupID失败！");
            }
        }
        preCardBaseInfoData.setGroupID(groupID);
        preCardBaseInfoData.setMpID(mpID);
        //cardKey
        Long cardKey = preCardBaseInfoData.getCardKey();


        //title
        if (StringUtils.isBlank(preCardBaseInfoData.getTitle())) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_TITLE_NULL, "参数title为空！");
        }
        //cardType
        if (StringUtils.isBlank(preCardBaseInfoData.getCardType())) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_TYPE_NULL, "参数cardType为空！");
        }
        BaseInfoModel baseInfoModel = DataUtils.copyProperties(preCardBaseInfoData, BaseInfoModel.class);

        //cardKey
        if (cardKey == null) {
            try {
                cardKey = createCardKeyService.createCardKey(groupID);
            } catch (ExecutionException e) {
                e.printStackTrace();
                return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "获取CardKey失败:\r\n" + e.getMessage());
            }
            baseInfoModel.setCardKey(cardKey);
            baseInfoModelMapper.insertSelective(baseInfoModel);
        }
        baseInfoModel.setCardStatus(1);
        baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
        PreCardResData cardCouponResData = new PreCardResData();
        cardCouponResData.setCardKey(cardKey);
        cardCouponResData.setResultInfo(ErrorCodes.WECHAT_SUCCESS_CODE, "请求成功");
        return cardCouponResData;
    }

    /**
     * 预创建高级信息
     */
    @Autowired
    private AdvancedModelMapper advancedModelMapper;

    @Override
    public PreCardResData createAdvancedInfo(PreAdvancedInfoData preAdvancedInfoData) {
        //cardKey
        if (preAdvancedInfoData.getCardKey() == null) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "参数cardKey为空！");
        }
        AdvancedModel advancedModel = DataUtils.copyProperties(preAdvancedInfoData, AdvancedModel.class);
        advancedModelMapper.insertSelective(advancedModel);
        PreCardResData cardCouponResData = new PreCardResData();
        cardCouponResData.setCardKey(preAdvancedInfoData.getCardKey());
        cardCouponResData.setResultInfo(ErrorCodes.WECHAT_SUCCESS_CODE, "请求成功");
        return cardCouponResData;
    }

    @Autowired
    private BaseHttpService baseHttpService;

    /**
     * 提交优惠券
     *
     * @param cardPrimaryKey
     * @return
     */
    @Override
    public PreCardResData submitCouponInfo(CardPrimaryKey cardPrimaryKey) {
        Long cardKey = cardPrimaryKey.getCardKey();
        if (cardKey == null) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "参数cardKey为空！");
        }
        Map<String, Object> card = new HashMap<>();

        CouponModel couponModel = couponModelMapper.selectByPrimaryKey(cardKey);
        //首先判断该cardKey没有对应得cardID
        String cardID = couponModel.getCardID();
        if (StringUtils.isNotBlank(cardID)) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_SUBMIT_REPEATED, "已经提交成功，不能重复提交！");
        }
        Map<String, Object> cardInfo = new HashMap<>();
        BaseInfoModel baseInfo = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        AdvancedModel advancedInfo = advancedModelMapper.selectByPrimaryKey(cardKey);
        String cardType = couponModel.getCardType();
        String mpID = couponModel.getMpID();
        card.put("card_type", cardType);
        Integer leastCost = null;
        Integer reduceCost = null;
        String defaultDetail = null;
        String dealDetail = null;
        String gift = null;
        Integer discount = null;
        switch (cardType) {
            case WechatMessageType.CARD_TYPE_CASH:
                leastCost = couponModel.getLeastCost();//启用门槛 -1为无门槛
                reduceCost = couponModel.getReduceCost();//减免金额
                if (null == leastCost || null == reduceCost) {
                    return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_LEAST_COST_NULL, "代金券的启用门槛，或者减免金额不能为空！");
                }
                cardInfo.put("least_cost", leastCost);
                cardInfo.put("reduce_cost", reduceCost);
                break;
            case WechatMessageType.CARD_TYPE_DISCOUNT:
                discount = couponModel.getDiscount();
                cardInfo.put("discount", discount);
                break;
            case WechatMessageType.CARD_TYPE_GENERAL_COUPON:
                defaultDetail = couponModel.getDefaultDetail();
                cardInfo.put("default_detail", defaultDetail);

                break;
            case WechatMessageType.CARD_TYPE_GIFT:
                gift = couponModel.getGift();
                cardInfo.put("gift", gift);

                break;
            case WechatMessageType.CARD_TYPE_GROUPON:
                dealDetail = couponModel.getDealDetail();
                cardInfo.put("deal_detail", dealDetail);

                break;
        }

        // BaseInfo设置卡券基本信息
        Map<String, Object> baseInfoMap = new HashMap<>();
        Integer quantity = baseInfo.getSku();
        Map<String, Object> sku = new HashMap<String, Object>();
        sku.put("quantity", quantity);
        baseInfoMap.put("sku", sku);

        String dateInfo = baseInfo.getDateInfo();
        JSONObject dateInfoMap = JSONObject.parseObject(dateInfo);
        baseInfoMap.put("date_info", dateInfoMap);

        //baseInfo其他信息（必填）
        //        logo_url 	             是 	string(128) 	http://mmbiz.qpic.cn/ 	卡券的商户logo，建议像素为300*300。
        String logoUrl = baseInfo.getLogoUrl();
        if (StringUtils.isBlank(logoUrl)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_LOGO_NULL, "LogoUrl不能为空！");
        }
        baseInfoMap.put("logo_url", logoUrl);
        //        code_type 	         是 	string(16) 	    CODE_TYPE_TEXT 	Code展示类型，"CODE_TYPE_TEXT"，文本；"CODE_TYPE_BARCODE"，一维码 ；"CODE_TYPE_QRCODE"，二维码；"CODE_TYPE_ONLY_QRCODE",二维码无code显示；"CODE_TYPE_ONLY_BARCODE",一维码无code显示；
        String codeType = baseInfo.getCodeType();
        if (StringUtils.isBlank(codeType)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "codeType不能为空！");
        }
        baseInfoMap.put("code_type", codeType);
        //        brand_name             是 	string（36） 	海底捞 	商户名字,字数上限为12个汉字。
        String brandName = baseInfo.getBrandName();
        if (StringUtils.isBlank(brandName)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "brandName不能为空！");
        }
        baseInfoMap.put("brand_name", brandName);
        //        title 	             是 	string（27） 	双人套餐100元兑换券 	卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。
        String title = couponModel.getTitle();
        if (StringUtils.isBlank(title)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "title不能为空！");
        }
        baseInfoMap.put("title", title);
        //        color 	             是 	string（16） 	Color010 	券颜色。按色彩规范标注填写Color010-Color100。详情见获取颜色列表接口
        String color = baseInfo.getColor();
        if (StringUtils.isBlank(color)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_COLOR_NULL, "背景色color不能为空！");
        }
        baseInfoMap.put("color", color);
        //        notice 	             是 	string（48） 	请出示二维码核销卡券 	卡券使用提醒，字数上限为16个汉字。
        String notice = baseInfo.getNotice();
        if (StringUtils.isBlank(notice)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_NOTICE_NULL, "notice不能为空！");
        }
        baseInfoMap.put("notice", notice);
        //        description            是 	string（3072） 	不可与其他优惠同享/n如需团购券发票，请向店员提出要求。 	卡券使用说明，字数上限为1024个汉字。
        String description = baseInfo.getDescription();
        if (StringUtils.isBlank(description)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_DESCRIPTION_NULL, "description不能为空！");
        }
        baseInfoMap.put("description", description);

        //baseInfo其他信息（选填）
        //        use_custom_code        否 	bool 	        true 	是否自定义Code码。填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，在卡券投放时带入。
        baseInfoMap.put("use_custom_code", baseInfo.getUseCustomCode());
        //        bind_openid 	         否 	bool 	        true 	是否指定用户领取，填写true或false。默认为false。
        baseInfoMap.put("bind_openid", true);
        //        service_phone 	     否 	string（24） 	40012234 	客服电话。
        String servicePhone = baseInfo.getServicePhone();
        if (StringUtils.isNotBlank(servicePhone)) {
            baseInfoMap.put("service_phone", servicePhone);
        }
        //        location_id_list 	     否 	array 	        1234，2312 	门店位置ID。调用POI门店管理接口获取门店位置ID。
        String locationIdList = baseInfo.getLocationIdList();
        if (StringUtils.isNotBlank(locationIdList)) {
            String[] split = locationIdList.split(", ");
            baseInfoMap.put("location_id_list", split);
        }

        //        source 	             否 	string（36） 	大众点评 	第三方来源名，例如同程旅游、大众点评。
        String source = baseInfo.getSource();
        if (StringUtils.isNotBlank(source)) {
            baseInfoMap.put("source", source);
        }
        //        customUrl_name 	     否 	string（15） 	立即使用 	自定义跳转外链的入口名字。
        String customUrlName = baseInfo.getCustomUrlName();
        if (StringUtils.isNotBlank(customUrlName)) {
            baseInfoMap.put("custom_url_name", customUrlName);
        }
        //        custom_url 	         否 	string（128） 	"xxxx.com" 	自定义跳转的URL。
        String customUrl = baseInfo.getCustomUrl();
        if (StringUtils.isNotBlank(customUrl)) {
            baseInfoMap.put("custom_url", customUrl);
        }
        //        custom_url_sub_title 	 否 	string（18） 	更多惊喜 	显示在入口右侧的提示语。
        String customUrlSubTitle = baseInfo.getCustomUrlSubTitle();
        if (StringUtils.isNotBlank(customUrlSubTitle)) {
            baseInfoMap.put("custom_url_sub_title", customUrlSubTitle);
        }
        //        promotion_url_name 	 否 	string（15） 	产品介绍 	营销场景的自定义入口名称。
        String promotionUrlName = baseInfo.getPromotionUrlName();
        if (StringUtils.isNotBlank(promotionUrlName)) {
            baseInfoMap.put("promotion_url_name", promotionUrlName);
        }
        //        promotion_url 	     否 	string（128） 	XXXX.com 	入口跳转外链的地址链接。
        String promotionUrl = baseInfo.getPromotionUrl();
        if (StringUtils.isNotBlank(promotionUrl)) {
            baseInfoMap.put("promotion_url", promotionUrl);
        }
        //        promotion_url_sub_title否 	string（18） 	卖场大优惠。 	显示在营销入口右侧的提示语。
        String promotionUrlSubTitle = baseInfo.getPromotionUrlSubTitle();
        if (StringUtils.isNotBlank(promotionUrlSubTitle)) {
            baseInfoMap.put("romotion_url_sub_title", promotionUrlSubTitle);
        }
        //        get_limit 	         否 	int 	        1 	每人可领券的数量限制。默认值为50。
        Integer getLimit = baseInfo.getGetLimit();
        baseInfoMap.put("get_limit", getLimit);
        //        can_share 	         否 	bool 	        false 	卡券领取页面是否可分享。
        baseInfoMap.put("can_share", baseInfo.getCanShare());
        //        can_give_friend 	     否 	bool 	        false 	卡券是否可转赠。
        baseInfoMap.put("can_give_friend", baseInfo.getCanGiveFriend());
        //        need_push_on_view 	 否 	bool 	        false 	填写true为用户点击进入会员卡时推送事件，默认为false。详情见进入会员卡事件推送
        baseInfoMap.put("need_push_on_view", baseInfo.getNeedPushOnView());

        //        get_custom_code_mode	否	string(32)      GET_CUSTOM_COD E_MODE_DEPOSIT 填入 GET_CUSTOM_CODE_MODE_DEPOSIT
        //                                                  表示该卡券为预存code模式卡券，须导入超过库存数目的自定义code后方可投放，填入该字段后，
        //                                                  quantity字段须为0,须导入code后再增加库存
        String customCodeMode = baseInfo.getCustomCodeMode();
        if (StringUtils.isNotBlank(customCodeMode)) {
            baseInfoMap.put("get_custom_code_mode", customCodeMode);
        }
        //        use_all_locations     否	bool	        true	设置本卡券支持全部门店，与location_id_list互斥
        baseInfoMap.put("use_all_locations", baseInfo.getUseAllLocations());
        //        center_title	        否	string（18）	立即使用卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示
        baseInfoMap.put("center_title", baseInfo.getCenterTitle());
        //        center_sub_title	    否	string（24）	立即享受优惠显示在入口下方的提示，仅在卡券状态正常(可以核销)时显示。
        baseInfoMap.put("center_sub_title", baseInfo.getCenterSubTitle());
        //        center_url	        否	string（128）	www.qq.com顶部居中的url，仅在卡券状态正常(可以核销)时显示。
        baseInfoMap.put("center_url", baseInfo.getCenterUrl());
        //        use_limit	            否	int	            100	每人可核销的数量限制,不填写默认为50。
        Integer useLimit = baseInfo.getUseLimit();
        baseInfoMap.put("use_limit", useLimit);

        cardInfo.put("base_info", baseInfoMap);

        //  AdvancedInfo设置高级信息
        Map<String, Object> advancedInfoMap = new HashMap<>();
        //        abstract	                    否	JSON结构	        封面摘要结构体名称
        String abstractInfo = advancedInfo.getAbstractInfo();
        JSONObject abstractMap = JSONObject.parseObject(abstractInfo);
        advancedInfoMap.put("abstract", abstractMap);
        //          business_service	        否	arry	            商家服务类型：BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位；BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi，可多选
        String businessService = advancedInfo.getBusinessService();
        String[] businessServiceList = businessService.split(", ");
        advancedInfoMap.put("business_service", businessServiceList);
        //          text_image_list	            否	JSON结构            图文列表，显示在详情内页，优惠券券开发者须至少传入一组图文列表
        String textImage = advancedInfo.getTextImage();
        JSONArray textImages = JSONArray.parseArray(textImage);
        advancedInfoMap.put("text_image_list", textImages);
        //          time_limit	                否	JSON结构	        使用时段限制，包含以下字段
        String timeLimit = advancedInfo.getTimeLimit();
        JSONArray timeLimits = JSONArray.parseArray(timeLimit);
        advancedInfoMap.put("time_limit", timeLimits);

        //          use_condition	            否	JSON结构            使用门槛（条件）字段，若不填写使用条件则在券面拼写：无最低消费限制，全场通用，不限品类；并在使用说明显示：可与其他优惠共享
        String useCodition = advancedInfo.getUseCodition();
        JSONObject useCoditionMap = JSONObject.parseObject(useCodition);
        advancedInfoMap.put("use_condition", useCoditionMap);

        cardInfo.put("advanced_info", advancedInfoMap);

        Map<String, Object> map = WechatNameConverterUtil.convertToDBStyle(cardInfo);
        //请求微信
        card.put(cardType.toLowerCase(), map);

        Map<String, Object> params = new HashMap<>();
        params.put("card", card);
        JSONObject jsonObject = baseHttpService.createCardAndCoupon(params, mpID);
        //判断是否在微信端创建成功,如果成功存入数据库
        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            //获取cardID
            String cardId = jsonObject.getString("card_id");
            couponModel.setCardKey(cardKey);
            couponModel.setCardID(cardId);
            couponModel.setCardStatus(2);
            couponModelMapper.updateByPrimaryKeySelective(couponModel);
        }
        PreCardResData resultInfoBean = ResultUtil.getResultInfoBean(jsonObject, PreCardResData.class);

        return resultInfoBean;
    }

    /**
     * 提交会员卡
     *
     * @param cardPrimaryKey
     * @return
     */
    @Override
    public PreCardResData submitMemberInfo(CardPrimaryKey cardPrimaryKey) {
        Long cardKey = cardPrimaryKey.getCardKey();
        if (cardKey == null) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "参数cardKey为空！");
        }
        Map<String, Object> card = new HashMap<>();

        MemberModel memberModel = memberModelMapper.selectByPrimaryKey(cardKey);
        //首先判断该cardKey没有对应得cardID
        String cardID = memberModel.getCardID();
        if (StringUtils.isNotBlank(cardID)) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_SUBMIT_REPEATED, "已经提交成功，不能重复提交！");
        }
        Map<String, Object> cardInfo = new HashMap<>();
        BaseInfoModel baseInfo = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        AdvancedModel advancedInfo = advancedModelMapper.selectByPrimaryKey(cardKey);
        String cardType = memberModel.getCardType();
        String mpID = memberModel.getMpID();
        //必填
        card.put("card_type", cardType);

        String prerogative = memberModel.getPrerogative();
        if (StringUtils.isBlank(prerogative)) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_PREROGATIVE_EMPTY, "会员卡特权说明为空！");
        }
        cardInfo.put("prerogative", prerogative);
        //选填     自动激活
        Boolean autoActivate = memberModel.getAutoActivate();

        String activateUrl = memberModel.getActivateUrl();
        if (StringUtils.isNotBlank(activateUrl)) {
            cardInfo.put("activate_url", activateUrl);
        }
        //        background_pic_url     否            	string(128)  商家自定义会员卡背景图，须先调用上传图片接口将背景图上传至CDN，否则报错，卡面设计请遵循微信会员卡自定义背景设计规范  ,像素大小控制在1000像素*600像素以下
        String backgroundPicUrl = memberModel.getBackgroundPicUrl();
        if (StringUtils.isNotBlank(backgroundPicUrl)) {
            cardInfo.put("background_pic_url", backgroundPicUrl);
        }
        Integer discount = memberModel.getDiscount();
        cardInfo.put("discount", discount);

        //wx_activate            	否            	bool            	设置为true时会员卡支持一键开卡，不允许同时传入activate_url字段，否则设置wx_activate失效。填入该字段后仍需调用接口设置开卡项方可生效，详情见一键开卡。
        Boolean wxActivate = memberModel.getWxActivate();
        cardInfo.put("wx_activate", wxActivate);


        //supply_balance            	是            	bool            	是否支持储值，填写true或false。如填写true，储值相关字段均为必填。
        Boolean supplyBalance = memberModel.getSupplyBalance();
        cardInfo.put("supply_balance", supplyBalance);
        if (supplyBalance) {
            String balanceRules = memberModel.getBalanceRules();
            String balanceUrl = memberModel.getBalanceUrl();
            cardInfo.put("balance_rules", balanceRules);
            cardInfo.put("balance_url", balanceUrl);
        }

        //supply_bonus            	是            	bool            	显示积分，填写true或false，如填写true，积分相关字段均为必填。
        Boolean supplyBonus = memberModel.getSupplyBonus();
        cardInfo.put("supply_bonus", supplyBonus);
        if (supplyBonus) {
            String bonusUrl = memberModel.getBonusUrl();
            String bonusCleared = memberModel.getBonusCleared();
            if (StringUtils.isNotBlank(bonusUrl)) {
                cardInfo.put("bonus_url", bonusUrl);
            }
            if (StringUtils.isNotBlank(bonusCleared)) {
                cardInfo.put("bonus_cleared", bonusCleared);
            }
            //json
            String bonusRule = memberModel.getBonusRule();
            if (StringUtils.isNotBlank(bonusRule)) {
                JSONObject jsonObject = JSONObject.parseObject(bonusRule);
                cardInfo.put("bonus_rule", jsonObject);
            }
            String bonusRules = memberModel.getBonusRules();
            if (StringUtils.isNotBlank(bonusRules)) {
                cardInfo.put("bonus_rules", bonusRules);
            }
        }


        //会员Json
        String customCell1 = memberModel.getCustomCell1();
        if (StringUtils.isNotBlank(customCell1)) {
            JSONObject jsonObject = JSONObject.parseObject(customCell1);
            cardInfo.put("custom_cell1", jsonObject);
        }
        String customField1 = memberModel.getCustomField1();
        if (StringUtils.isNotBlank(customField1)) {
            JSONObject jsonObject = JSONObject.parseObject(customField1);
            cardInfo.put("custom_field1", jsonObject);
        }
        String customField2 = memberModel.getCustomField2();
        if (StringUtils.isNotBlank(customField2)) {
            JSONObject jsonObject = JSONObject.parseObject(customField2);
            cardInfo.put("custom_field2", jsonObject);
        }
        String customField3 = memberModel.getCustomField3();
        if (StringUtils.isNotBlank(customField3)) {
            JSONObject jsonObject = JSONObject.parseObject(customField3);
            cardInfo.put("custom_field3", jsonObject);
        }
        // BaseInfo设置卡券基本信息
        Map<String, Object> baseInfoMap = new HashMap<>();
        Integer quantity = baseInfo.getSku();
        Map<String, Object> sku = new HashMap<String, Object>();
        sku.put("quantity", quantity);
        baseInfoMap.put("sku", sku);

        String dateInfo = baseInfo.getDateInfo();
        JSONObject dateInfoMap = JSONObject.parseObject(dateInfo);
        baseInfoMap.put("date_info", dateInfoMap);

        //baseInfo其他信息（必填）
        //        logo_url 	             是 	string(128) 	http://mmbiz.qpic.cn/ 	卡券的商户logo，建议像素为300*300。
        String logoUrl = baseInfo.getLogoUrl();
        if (StringUtils.isBlank(logoUrl)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_LOGO_NULL, "LogoUrl不能为空！");
        }
        baseInfoMap.put("logo_url", logoUrl);
        //        code_type 	         是 	string(16) 	    CODE_TYPE_TEXT 	Code展示类型，"CODE_TYPE_TEXT"，文本；"CODE_TYPE_BARCODE"，一维码 ；"CODE_TYPE_QRCODE"，二维码；"CODE_TYPE_ONLY_QRCODE",二维码无code显示；"CODE_TYPE_ONLY_BARCODE",一维码无code显示；
        String codeType = baseInfo.getCodeType();
        if (StringUtils.isBlank(codeType)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "codeType不能为空！");
        }
        baseInfoMap.put("code_type", codeType);
        //        brand_name             是 	string（36） 	海底捞 	商户名字,字数上限为12个汉字。
        String brandName = baseInfo.getBrandName();
        if (StringUtils.isBlank(brandName)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "brandName不能为空！");
        }
        baseInfoMap.put("brand_name", brandName);
        //        title 	             是 	string（27） 	双人套餐100元兑换券 	卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。
        String title = memberModel.getTitle();
        if (StringUtils.isBlank(title)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "title不能为空！");
        }
        baseInfoMap.put("title", title);
        //        color 	             是 	string（16） 	Color010 	券颜色。按色彩规范标注填写Color010-Color100。详情见获取颜色列表接口
        String color = baseInfo.getColor();
        if (StringUtils.isBlank(color)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_COLOR_NULL, "背景色color不能为空！");
        }
        baseInfoMap.put("color", color);
        //        notice 	             是 	string（48） 	请出示二维码核销卡券 	卡券使用提醒，字数上限为16个汉字。
        String notice = baseInfo.getNotice();
        if (StringUtils.isBlank(notice)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_NOTICE_NULL, "notice不能为空！");
        }
        baseInfoMap.put("notice", notice);
        //        description            是 	string（3072） 	不可与其他优惠同享/n如需团购券发票，请向店员提出要求。 	卡券使用说明，字数上限为1024个汉字。
        String description = baseInfo.getDescription();
        if (StringUtils.isBlank(description)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_DESCRIPTION_NULL, "description不能为空！");
        }
        baseInfoMap.put("description", description);

        //baseInfo其他信息（选填）
        //        use_custom_code        否 	bool 	        true 	是否自定义Code码。填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，在卡券投放时带入。
        baseInfoMap.put("use_custom_code", baseInfo.getUseCustomCode());
        //        bind_openid 	         否 	bool 	        true 	是否指定用户领取，填写true或false。默认为false。
        baseInfoMap.put("bind_openid", true);
        //        service_phone 	     否 	string（24） 	40012234 	客服电话。
        String servicePhone = baseInfo.getServicePhone();
        if (StringUtils.isNotBlank(servicePhone)) {
            baseInfoMap.put("service_phone", servicePhone);
        }
        //        location_id_list 	     否 	array 	        1234，2312 	门店位置ID。调用POI门店管理接口获取门店位置ID。
        String locationIdList = baseInfo.getLocationIdList();
        if (StringUtils.isNotBlank(locationIdList)) {
            String[] split = locationIdList.split(", ");
            baseInfoMap.put("location_id_list", split);
        }

        //        source 	             否 	string（36） 	大众点评 	第三方来源名，例如同程旅游、大众点评。
        String source = baseInfo.getSource();
        if (StringUtils.isNotBlank(source)) {
            baseInfoMap.put("source", source);
        }
        //        customUrl_name 	     否 	string（15） 	立即使用 	自定义跳转外链的入口名字。
        String customUrlName = baseInfo.getCustomUrlName();
        if (StringUtils.isNotBlank(customUrlName)) {
            baseInfoMap.put("custom_url_name", customUrlName);
        }
        //        custom_url 	         否 	string（128） 	"xxxx.com" 	自定义跳转的URL。
        String customUrl = baseInfo.getCustomUrl();
        if (StringUtils.isNotBlank(customUrl)) {
            baseInfoMap.put("custom_url", customUrl);
        }
        //        custom_url_sub_title 	 否 	string（18） 	更多惊喜 	显示在入口右侧的提示语。
        String customUrlSubTitle = baseInfo.getCustomUrlSubTitle();
        if (StringUtils.isNotBlank(customUrlSubTitle)) {
            baseInfoMap.put("custom_url_sub_title", customUrlSubTitle);
        }
        //        promotion_url_name 	 否 	string（15） 	产品介绍 	营销场景的自定义入口名称。
        String promotionUrlName = baseInfo.getPromotionUrlName();
        if (StringUtils.isNotBlank(promotionUrlName)) {
            baseInfoMap.put("promotion_url_name", promotionUrlName);
        }
        //        promotion_url 	     否 	string（128） 	XXXX.com 	入口跳转外链的地址链接。
        String promotionUrl = baseInfo.getPromotionUrl();
        if (StringUtils.isNotBlank(promotionUrl)) {
            baseInfoMap.put("promotion_url", promotionUrl);
        }
        //        promotion_url_sub_title否 	string（18） 	卖场大优惠。 	显示在营销入口右侧的提示语。
        String promotionUrlSubTitle = baseInfo.getPromotionUrlSubTitle();
        if (StringUtils.isNotBlank(promotionUrlSubTitle)) {
            baseInfoMap.put("romotion_url_sub_title", promotionUrlSubTitle);
        }
        //        get_limit 	         否 	int 	        1 	每人可领券的数量限制。默认值为50。
        Integer getLimit = baseInfo.getGetLimit();
        baseInfoMap.put("get_limit", getLimit);
        //        can_share 	         否 	bool 	        false 	卡券领取页面是否可分享。
        baseInfoMap.put("can_share", baseInfo.getCanShare());
        //        can_give_friend 	     否 	bool 	        false 	卡券是否可转赠。
        baseInfoMap.put("can_give_friend", baseInfo.getCanGiveFriend());
        //        need_push_on_view 	 否 	bool 	        false 	填写true为用户点击进入会员卡时推送事件，默认为false。详情见进入会员卡事件推送
        baseInfoMap.put("need_push_on_view", baseInfo.getNeedPushOnView());

        //        get_custom_code_mode	否	string(32)      GET_CUSTOM_COD E_MODE_DEPOSIT 填入 GET_CUSTOM_CODE_MODE_DEPOSIT
        //                                                  表示该卡券为预存code模式卡券，须导入超过库存数目的自定义code后方可投放，填入该字段后，
        //                                                  quantity字段须为0,须导入code后再增加库存
        String customCodeMode = baseInfo.getCustomCodeMode();
        if (StringUtils.isNotBlank(customCodeMode)) {
            baseInfoMap.put("get_custom_code_mode", customCodeMode);
        }
        //        use_all_locations     否	bool	        true	设置本卡券支持全部门店，与location_id_list互斥
        baseInfoMap.put("use_all_locations", baseInfo.getUseAllLocations());
        //        center_title	        否	string（18）	立即使用卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示
        baseInfoMap.put("center_title", baseInfo.getCenterTitle());
        //        center_sub_title	    否	string（24）	立即享受优惠显示在入口下方的提示，仅在卡券状态正常(可以核销)时显示。
        baseInfoMap.put("center_sub_title", baseInfo.getCenterSubTitle());
        //        center_url	        否	string（128）	www.qq.com顶部居中的url，仅在卡券状态正常(可以核销)时显示。
        baseInfoMap.put("center_url", baseInfo.getCenterUrl());
        //        use_limit	            否	int	            100	每人可核销的数量限制,不填写默认为50。
        Integer useLimit = baseInfo.getUseLimit();
        baseInfoMap.put("use_limit", useLimit);

        if (baseInfo.getIsSwipeCard()) {
            String payInfo = "{\"swipe_card\" : {\"is_swipe_card\" : true}}";
            JSONObject payJson = JSONObject.parseObject(payInfo);
            baseInfoMap.put("pay_info", payJson);
        }
        if (baseInfo.getIsPayAndQrcode()) {
            baseInfoMap.put("is_pay_and_qrcode", true);
        }

        cardInfo.put("base_info", baseInfoMap);

        //  AdvancedInfo设置高级信息
        Map<String, Object> advancedInfoMap = new HashMap<>();
        //        abstract	                    否	JSON结构	        封面摘要结构体名称
        String abstractInfo = advancedInfo.getAbstractInfo();
        JSONObject abstractMap = JSONObject.parseObject(abstractInfo);
        advancedInfoMap.put("abstract", abstractMap);
        //          business_service	        否	arry	            商家服务类型：BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位；BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi，可多选
        String businessService = advancedInfo.getBusinessService();
        String[] businessServiceList = businessService.split(", ");
        advancedInfoMap.put("business_service", businessServiceList);
        //          text_image_list	            否	JSON结构            图文列表，显示在详情内页，优惠券券开发者须至少传入一组图文列表
        String textImage = advancedInfo.getTextImage();
        JSONArray textImages = JSONArray.parseArray(textImage);
        advancedInfoMap.put("text_image_list", textImages);
        //          time_limit	                否	JSON结构	        使用时段限制，包含以下字段
        String timeLimit = advancedInfo.getTimeLimit();
        JSONArray timeLimits = JSONArray.parseArray(timeLimit);
        advancedInfoMap.put("time_limit", timeLimits);

        //          use_condition	            否	JSON结构            使用门槛（条件）字段，若不填写使用条件则在券面拼写：无最低消费限制，全场通用，不限品类；并在使用说明显示：可与其他优惠共享
        String useCodition = advancedInfo.getUseCodition();
        JSONObject useCoditionMap = JSONObject.parseObject(useCodition);
        advancedInfoMap.put("use_condition", useCoditionMap);

        cardInfo.put("advanced_info", advancedInfoMap);

        Map<String, Object> map = WechatNameConverterUtil.convertToDBStyle(cardInfo);
        //请求微信
        card.put(cardType.toLowerCase(), map);

        Map<String, Object> params = new HashMap<>();
        params.put("card", card);
        String s = JSONObject.toJSONString(params);
        JSONObject jsonObject = baseHttpService.createCardAndCoupon(params, mpID);
        //判断是否在微信端创建成功,如果成功存入数据库
        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            //获取cardID
            String cardId = jsonObject.getString("card_id");
            memberModel.setCardKey(cardKey);
            memberModel.setCardID(cardId);
            memberModel.setCardStatus(2);
            memberModelMapper.updateByPrimaryKeySelective(memberModel);
        }
        PreCardResData resultInfoBean = ResultUtil.getResultInfoBean(jsonObject, PreCardResData.class);

        return resultInfoBean;
    }

    @Override
    public PreCardResData submitCardInfo(CardPrimaryKey cardPrimaryKey) {
        Long cardKey = cardPrimaryKey.getCardKey();
        if (cardKey == null) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "参数cardKey为空！");
        }
        Map<String, Object> card = new HashMap<>();

        BaseInfoModel baseInfo = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        //首先判断该cardKey没有对应得cardID
        String cardID = baseInfo.getCardID();
        if (StringUtils.isNotBlank(cardID)) {
            return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_SUBMIT_REPEATED, "已经提交成功，不能重复提交！");
        }
        Map<String, Object> cardInfo = new HashMap<>();
        AdvancedModel advancedInfo = advancedModelMapper.selectByPrimaryKey(cardKey);
        String cardType = baseInfo.getCardType();
        String mpID = baseInfo.getMpID();
        //必填
        card.put("card_type", cardType);

        if (WechatMessageType.CARD_TYPE_MEMBER_CARD.equals(cardType)) {
            MemberModel memberModel = memberModelMapper.selectByPrimaryKey(cardKey);
            String prerogative = memberModel.getPrerogative();
            if (StringUtils.isBlank(prerogative)) {
                return new PreCardResData().setResultInfo(ErrorCodes.WECHAT_CARD_PREROGATIVE_EMPTY, "会员卡特权说明为空！");
            }
            cardInfo.put("prerogative", prerogative);
            //选填     自动激活
            Boolean autoActivate = memberModel.getAutoActivate();

            String activateUrl = memberModel.getActivateUrl();
            if (StringUtils.isNotBlank(activateUrl)) {
                cardInfo.put("activate_url", activateUrl);
            }
            //        background_pic_url     否            	string(128)  商家自定义会员卡背景图，须先调用上传图片接口将背景图上传至CDN，否则报错，卡面设计请遵循微信会员卡自定义背景设计规范  ,像素大小控制在1000像素*600像素以下
            String backgroundPicUrl = memberModel.getBackgroundPicUrl();
            if (StringUtils.isNotBlank(backgroundPicUrl)) {
                cardInfo.put("background_pic_url", backgroundPicUrl);
            }
            Integer discount = memberModel.getDiscount();
            cardInfo.put("discount", discount);

            //wx_activate            	否            	bool            	设置为true时会员卡支持一键开卡，不允许同时传入activate_url字段，否则设置wx_activate失效。填入该字段后仍需调用接口设置开卡项方可生效，详情见一键开卡。
            Boolean wxActivate = memberModel.getWxActivate();
            cardInfo.put("wx_activate", wxActivate);


            //supply_balance            	是            	bool            	是否支持储值，填写true或false。如填写true，储值相关字段均为必填。
            Boolean supplyBalance = memberModel.getSupplyBalance();
            cardInfo.put("supply_balance", supplyBalance);
            if (supplyBalance) {
                String balanceRules = memberModel.getBalanceRules();
                String balanceUrl = memberModel.getBalanceUrl();
                cardInfo.put("balance_rules", balanceRules);
                cardInfo.put("balance_url", balanceUrl);
            }
            //supply_bonus            	是            	bool            	显示积分，填写true或false，如填写true，积分相关字段均为必填。
            Boolean supplyBonus = memberModel.getSupplyBonus();
            cardInfo.put("supply_bonus", supplyBonus);
            if (supplyBonus) {
                String bonusUrl = memberModel.getBonusUrl();
                String bonusCleared = memberModel.getBonusCleared();
                if (StringUtils.isNotBlank(bonusUrl)) {
                    cardInfo.put("bonus_url", bonusUrl);
                }
                if (StringUtils.isNotBlank(bonusCleared)) {
                    cardInfo.put("bonus_cleared", bonusCleared);
                }
                //json
                String bonusRule = memberModel.getBonusRule();
                if (StringUtils.isNotBlank(bonusRule)) {
                    JSONObject jsonObject = JSONObject.parseObject(bonusRule);
                    cardInfo.put("bonus_rule", jsonObject);
                }
                String bonusRules = memberModel.getBonusRules();
                if (StringUtils.isNotBlank(bonusRules)) {
                    cardInfo.put("bonus_rules", bonusRules);
                }
            }
            //会员Json
            String customCell1 = memberModel.getCustomCell1();
            if (StringUtils.isNotBlank(customCell1)) {
                JSONObject jsonObject = JSONObject.parseObject(customCell1);
                cardInfo.put("custom_cell1", jsonObject);
            }
            String customField1 = memberModel.getCustomField1();
            if (StringUtils.isNotBlank(customField1)) {
                JSONObject jsonObject = JSONObject.parseObject(customField1);
                cardInfo.put("custom_field1", jsonObject);
            }
            String customField2 = memberModel.getCustomField2();
            if (StringUtils.isNotBlank(customField2)) {
                JSONObject jsonObject = JSONObject.parseObject(customField2);
                cardInfo.put("custom_field2", jsonObject);
            }
            String customField3 = memberModel.getCustomField3();
            if (StringUtils.isNotBlank(customField3)) {
                JSONObject jsonObject = JSONObject.parseObject(customField3);
                cardInfo.put("custom_field3", jsonObject);
            }
        } else {
            CouponModel couponModel = couponModelMapper.selectByPrimaryKey(cardKey);
            card.put("card_type", cardType);
            Integer leastCost = null;
            Integer reduceCost = null;
            String defaultDetail = null;
            String dealDetail = null;
            String gift = null;
            Integer discount = null;
            switch (cardType) {
                case WechatMessageType.CARD_TYPE_CASH:
                    leastCost = couponModel.getLeastCost();//启用门槛 -1为无门槛
                    reduceCost = couponModel.getReduceCost();//减免金额
                    if (null == leastCost || null == reduceCost) {
                        return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_LEAST_COST_NULL, "代金券的启用门槛，或者减免金额不能为空！");
                    }
                    cardInfo.put("least_cost", leastCost);
                    cardInfo.put("reduce_cost", reduceCost);
                    break;
                case WechatMessageType.CARD_TYPE_DISCOUNT:
                    discount = couponModel.getDiscount();
                    cardInfo.put("discount", discount);
                    break;
                case WechatMessageType.CARD_TYPE_GENERAL_COUPON:
                    defaultDetail = couponModel.getDefaultDetail();
                    cardInfo.put("default_detail", defaultDetail);
                    break;
                case WechatMessageType.CARD_TYPE_GIFT:
                    gift = couponModel.getGift();
                    cardInfo.put("gift", gift);

                    break;
                case WechatMessageType.CARD_TYPE_GROUPON:
                    dealDetail = couponModel.getDealDetail();
                    cardInfo.put("deal_detail", dealDetail);

                    break;
            }
        }
        // BaseInfo设置卡券基本信息
        Map<String, Object> baseInfoMap = new HashMap<>();
        Integer quantity = baseInfo.getSku();
        Map<String, Object> sku = new HashMap<String, Object>();
        sku.put("quantity", quantity);
        baseInfoMap.put("sku", sku);

        String dateInfo = baseInfo.getDateInfo();
        JSONObject dateInfoMap = JSONObject.parseObject(dateInfo);
        baseInfoMap.put("date_info", dateInfoMap);

        //baseInfo其他信息（必填）
        //        logo_url 	             是 	string(128) 	http://mmbiz.qpic.cn/ 	卡券的商户logo，建议像素为300*300。
        String logoUrl = baseInfo.getLogoUrl();
        if (StringUtils.isBlank(logoUrl)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_LOGO_NULL, "LogoUrl不能为空！");
        }
        baseInfoMap.put("logo_url", logoUrl);
        //        code_type 	         是 	string(16) 	    CODE_TYPE_TEXT 	Code展示类型，"CODE_TYPE_TEXT"，文本；"CODE_TYPE_BARCODE"，一维码 ；"CODE_TYPE_QRCODE"，二维码；"CODE_TYPE_ONLY_QRCODE",二维码无code显示；"CODE_TYPE_ONLY_BARCODE",一维码无code显示；
        String codeType = baseInfo.getCodeType();
        if (StringUtils.isBlank(codeType)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "codeType不能为空！");
        }
        baseInfoMap.put("code_type", codeType);
        //        brand_name             是 	string（36） 	海底捞 	商户名字,字数上限为12个汉字。
        String brandName = baseInfo.getBrandName();
        if (StringUtils.isBlank(brandName)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "brandName不能为空！");
        }
        baseInfoMap.put("brand_name", brandName);
        //        title 	             是 	string（27） 	双人套餐100元兑换券 	卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。
        String title = baseInfo.getTitle();
        if (StringUtils.isBlank(title)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_TYPE_NULL, "title不能为空！");
        }
        baseInfoMap.put("title", title);
        //        color 	             是 	string（16） 	Color010 	券颜色。按色彩规范标注填写Color010-Color100。详情见获取颜色列表接口
        String color = baseInfo.getColor();
        if (StringUtils.isBlank(color)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_COLOR_NULL, "背景色color不能为空！");
        }
        baseInfoMap.put("color", color);
        //        notice 	             是 	string（48） 	请出示二维码核销卡券 	卡券使用提醒，字数上限为16个汉字。
        String notice = baseInfo.getNotice();
        if (StringUtils.isBlank(notice)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_NOTICE_NULL, "notice不能为空！");
        }
        baseInfoMap.put("notice", notice);
        //        description            是 	string（3072） 	不可与其他优惠同享/n如需团购券发票，请向店员提出要求。 	卡券使用说明，字数上限为1024个汉字。
        String description = baseInfo.getDescription();
        if (StringUtils.isBlank(description)) {
            return new CreateCardCouponRpcService.CardCouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_DESCRIPTION_NULL, "description不能为空！");
        }
        baseInfoMap.put("description", description);

        //baseInfo其他信息（选填）
        //        use_custom_code        否 	bool 	        true 	是否自定义Code码。填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，在卡券投放时带入。
        baseInfoMap.put("use_custom_code", baseInfo.getUseCustomCode());
        //        bind_openid 	         否 	bool 	        true 	是否指定用户领取，填写true或false。默认为false。
        baseInfoMap.put("bind_openid", true);
        //        service_phone 	     否 	string（24） 	40012234 	客服电话。
        String servicePhone = baseInfo.getServicePhone();
        if (StringUtils.isNotBlank(servicePhone)) {
            baseInfoMap.put("service_phone", servicePhone);
        }
        //        location_id_list 	     否 	array 	        1234，2312 	门店位置ID。调用POI门店管理接口获取门店位置ID。
        String locationIdList = baseInfo.getLocationIdList();
        if (StringUtils.isNotBlank(locationIdList)) {
            JSONArray objects = JSONArray.parseArray(locationIdList);
            baseInfoMap.put("location_id_list", objects);
        }

        //        source 	             否 	string（36） 	大众点评 	第三方来源名，例如同程旅游、大众点评。
        String source = baseInfo.getSource();
        if (StringUtils.isNotBlank(source)) {
            baseInfoMap.put("source", source);
        }
        //        customUrl_name 	     否 	string（15） 	立即使用 	自定义跳转外链的入口名字。
        String customUrlName = baseInfo.getCustomUrlName();
        if (StringUtils.isNotBlank(customUrlName)) {
            baseInfoMap.put("custom_url_name", customUrlName);
        }
        //        custom_url 	         否 	string（128） 	"xxxx.com" 	自定义跳转的URL。
        String customUrl = baseInfo.getCustomUrl();
        if (StringUtils.isNotBlank(customUrl)) {
            baseInfoMap.put("custom_url", customUrl);
        }
        //        custom_url_sub_title 	 否 	string（18） 	更多惊喜 	显示在入口右侧的提示语。
        String customUrlSubTitle = baseInfo.getCustomUrlSubTitle();
        if (StringUtils.isNotBlank(customUrlSubTitle)) {
            baseInfoMap.put("custom_url_sub_title", customUrlSubTitle);
        }
        //        promotion_url_name 	 否 	string（15） 	产品介绍 	营销场景的自定义入口名称。
        String promotionUrlName = baseInfo.getPromotionUrlName();
        if (StringUtils.isNotBlank(promotionUrlName)) {
            baseInfoMap.put("promotion_url_name", promotionUrlName);
        }
        //        promotion_url 	     否 	string（128） 	XXXX.com 	入口跳转外链的地址链接。
        String promotionUrl = baseInfo.getPromotionUrl();
        if (StringUtils.isNotBlank(promotionUrl)) {
            baseInfoMap.put("promotion_url", promotionUrl);
        }
        //        promotion_url_sub_title否 	string（18） 	卖场大优惠。 	显示在营销入口右侧的提示语。
        String promotionUrlSubTitle = baseInfo.getPromotionUrlSubTitle();
        if (StringUtils.isNotBlank(promotionUrlSubTitle)) {
            baseInfoMap.put("romotion_url_sub_title", promotionUrlSubTitle);
        }
        //        get_limit 	         否 	int 	        1 	每人可领券的数量限制。默认值为50。
        baseInfoMap.put("get_limit", baseInfo.getGetLimit());
        //        can_share 	         否 	bool 	        false 	卡券领取页面是否可分享。
        baseInfoMap.put("can_share", baseInfo.getCanShare());
        //        can_give_friend 	     否 	bool 	        false 	卡券是否可转赠。
        baseInfoMap.put("can_give_friend", baseInfo.getCanGiveFriend());
        //        need_push_on_view 	 否 	bool 	        false 	填写true为用户点击进入会员卡时推送事件，默认为false。详情见进入会员卡事件推送
        baseInfoMap.put("need_push_on_view", baseInfo.getNeedPushOnView());

        //        get_custom_code_mode	否	string(32)      GET_CUSTOM_COD E_MODE_DEPOSIT 填入 GET_CUSTOM_CODE_MODE_DEPOSIT
        //                                                  表示该卡券为预存code模式卡券，须导入超过库存数目的自定义code后方可投放，填入该字段后，
        //                                                  quantity字段须为0,须导入code后再增加库存
        String customCodeMode = baseInfo.getCustomCodeMode();
        if (StringUtils.isNotBlank(customCodeMode)) {
            baseInfoMap.put("get_custom_code_mode", customCodeMode);
        }
        //        use_all_locations     否	bool	        true	设置本卡券支持全部门店，与location_id_list互斥
        baseInfoMap.put("use_all_locations", baseInfo.getUseAllLocations());
        //        center_title	        否	string（18）	立即使用卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示
        String centerTitle = baseInfo.getCenterTitle();
        if (StringUtils.isNotBlank(centerTitle)) {
            baseInfoMap.put("center_title", centerTitle);
        }
        //        center_sub_title	    否	string（24）	立即享受优惠显示在入口下方的提示，仅在卡券状态正常(可以核销)时显示。
        String centerSubTitle = baseInfo.getCenterSubTitle();
        if (StringUtils.isNotBlank(centerSubTitle)) {
            baseInfoMap.put("center_sub_title", centerSubTitle);
        }
        //        center_url	        否	string（128）	www.qq.com顶部居中的url，仅在卡券状态正常(可以核销)时显示。
        String centerUrl = baseInfo.getCenterUrl();
        if (StringUtils.isNotBlank(centerUrl)) {
            baseInfoMap.put("center_url", centerUrl);
        }
        //        use_limit	            否	int	            100	每人可核销的数量限制,不填写默认为50。
        baseInfoMap.put("use_limit", baseInfo.getUseLimit());
//todo baseInfo : is_swipe_card
        if (baseInfo.getIsSwipeCard()) {
            String payInfo = "{\"swipe_card\" : {\"is_swipe_card\" : true}}";
            JSONObject payJson = JSONObject.parseObject(payInfo);
            baseInfoMap.put("pay_info", payJson);
        }
        if (baseInfo.getIsPayAndQrcode()) {
            baseInfoMap.put("is_pay_and_qrcode", true);
        }

        cardInfo.put("base_info", baseInfoMap);

        //  AdvancedInfo设置高级信息
        Map<String, Object> advancedInfoMap = new HashMap<>();
        //        abstract	                    否	JSON结构	        封面摘要结构体名称
        String abstractInfo = advancedInfo.getAbstractInfo();
        JSONObject abstractMap = JSONObject.parseObject(abstractInfo);
        advancedInfoMap.put("abstract", abstractMap);
        //          business_service	        否	arry	            商家服务类型：BIZ_SERVICE_DELIVER 外卖服务； BIZ_SERVICE_FREE_PARK 停车位；BIZ_SERVICE_WITH_PET 可带宠物； BIZ_SERVICE_FREE_WIFI 免费wifi，可多选
        String businessService = advancedInfo.getBusinessService();
        String[] businessServiceList = businessService.split(", ");
        advancedInfoMap.put("business_service", businessServiceList);
        //          text_image_list	            否	JSON结构            图文列表，显示在详情内页，优惠券券开发者须至少传入一组图文列表
        String textImage = advancedInfo.getTextImage();
        JSONArray textImages = JSONArray.parseArray(textImage);
        advancedInfoMap.put("text_image_list", textImages);
        //          time_limit	                否	JSON结构	        使用时段限制，包含以下字段
        String timeLimit = advancedInfo.getTimeLimit();
        JSONArray timeLimits = JSONArray.parseArray(timeLimit);
        advancedInfoMap.put("time_limit", timeLimits);

        //          use_condition	            否	JSON结构            使用门槛（条件）字段，若不填写使用条件则在券面拼写：无最低消费限制，全场通用，不限品类；并在使用说明显示：可与其他优惠共享
        String useCodition = advancedInfo.getUseCodition();
        JSONObject useCoditionMap = JSONObject.parseObject(useCodition);
        advancedInfoMap.put("use_condition", useCoditionMap);

        cardInfo.put("advanced_info", advancedInfoMap);

        Map<String, Object> map = WechatNameConverterUtil.convertToDBStyle(cardInfo);
        //请求微信
        card.put(cardType.toLowerCase(), map);

        Map<String, Object> params = new HashMap<>();
        params.put("card", card);
        String s = JSONObject.toJSONString(params);
        JSONObject jsonObject = baseHttpService.createCardAndCoupon(params, mpID);
        //判断是否在微信端创建成功,如果成功存入数据库
        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            //获取cardID
            BaseInfoModel baseInfoModel = new BaseInfoModel();
            String cardId = jsonObject.getString("card_id");
            baseInfoModel.setCardKey(cardKey);
            baseInfoModel.setCardID(cardId);
            baseInfoModel.setCardStatus(2);
            baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
        }
        PreCardResData resultInfoBean = ResultUtil.getResultInfoBean(jsonObject, PreCardResData.class);

        return resultInfoBean;
    }

    private boolean setMemberInfo(Long cardKey, Map<String, Object> cardInfo) {
        MemberModel memberModel = memberModelMapper.selectByPrimaryKey(cardKey);
        String prerogative = memberModel.getPrerogative();
        if (StringUtils.isBlank(prerogative)) {
            return true;
        }
        cardInfo.put("prerogative", prerogative);
        //选填     自动激活
        Boolean autoActivate = memberModel.getAutoActivate();

        String activateUrl = memberModel.getActivateUrl();
        if (StringUtils.isNotBlank(activateUrl)) {
            cardInfo.put("activate_url", activateUrl);
        }
        //        background_pic_url     否            	string(128)  商家自定义会员卡背景图，须先调用上传图片接口将背景图上传至CDN，否则报错，卡面设计请遵循微信会员卡自定义背景设计规范  ,像素大小控制在1000像素*600像素以下
        String backgroundPicUrl = memberModel.getBackgroundPicUrl();
        if (StringUtils.isNotBlank(backgroundPicUrl)) {
            cardInfo.put("background_pic_url", backgroundPicUrl);
        }
        Integer discount = memberModel.getDiscount();
        cardInfo.put("discount", discount);

        //wx_activate            	否            	bool            	设置为true时会员卡支持一键开卡，不允许同时传入activate_url字段，否则设置wx_activate失效。填入该字段后仍需调用接口设置开卡项方可生效，详情见一键开卡。
        Boolean wxActivate = memberModel.getWxActivate();
        cardInfo.put("wx_activate", wxActivate);


        //supply_balance            	是            	bool            	是否支持储值，填写true或false。如填写true，储值相关字段均为必填。
        Boolean supplyBalance = memberModel.getSupplyBalance();
        cardInfo.put("supply_balance", supplyBalance);
        if (supplyBalance) {
            String balanceRules = memberModel.getBalanceRules();
            String balanceUrl = memberModel.getBalanceUrl();
            cardInfo.put("balance_rules", balanceRules);
            cardInfo.put("balance_url", balanceUrl);
        }

        //supply_bonus            	是            	bool            	显示积分，填写true或false，如填写true，积分相关字段均为必填。
        Boolean supplyBonus = memberModel.getSupplyBonus();
        cardInfo.put("supply_bonus", supplyBonus);
        if (supplyBonus) {
            String bonusUrl = memberModel.getBonusUrl();
            String bonusCleared = memberModel.getBonusCleared();
            if (StringUtils.isNotBlank(bonusUrl)) {
                cardInfo.put("bonus_url", bonusUrl);
            }
            if (StringUtils.isNotBlank(bonusCleared)) {
                cardInfo.put("bonus_cleared", bonusCleared);
            }
            //json
            String bonusRule = memberModel.getBonusRule();
            if (StringUtils.isNotBlank(bonusRule)) {
                JSONObject jsonObject = JSONObject.parseObject(bonusRule);
                cardInfo.put("bonus_rule", jsonObject);
            }
            String bonusRules = memberModel.getBonusRules();
            if (StringUtils.isNotBlank(bonusRules)) {
                cardInfo.put("bonus_rules", bonusRules);
            }
        }


        //会员Json
        String customCell1 = memberModel.getCustomCell1();
        if (StringUtils.isNotBlank(customCell1)) {
            JSONObject jsonObject = JSONObject.parseObject(customCell1);
            cardInfo.put("custom_cell1", jsonObject);
        }
        String customField1 = memberModel.getCustomField1();
        if (StringUtils.isNotBlank(customField1)) {
            JSONObject jsonObject = JSONObject.parseObject(customField1);
            cardInfo.put("custom_field1", jsonObject);
        }
        String customField2 = memberModel.getCustomField2();
        if (StringUtils.isNotBlank(customField2)) {
            JSONObject jsonObject = JSONObject.parseObject(customField2);
            cardInfo.put("custom_field2", jsonObject);
        }
        String customField3 = memberModel.getCustomField3();
        if (StringUtils.isNotBlank(customField3)) {
            JSONObject jsonObject = JSONObject.parseObject(customField3);
            cardInfo.put("custom_field3", jsonObject);
        }
        return false;
    }
}
