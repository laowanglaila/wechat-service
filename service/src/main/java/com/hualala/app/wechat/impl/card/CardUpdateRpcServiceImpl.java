package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardUpdateRpcService;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.card.CouponModelMapper;
import com.hualala.app.wechat.mapper.card.MemberModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.CouponModel;
import com.hualala.app.wechat.model.card.MemberModel;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WechatNameConverterUtil;
import com.hualala.core.utils.DataUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thinkpad on 2017/4/29 0029.
 */
@Service
public class CardUpdateRpcServiceImpl implements CardUpdateRpcService {

    @Autowired
    private MemberModelMapper memberModelMapper;
    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;
    @Autowired
    private CouponModelMapper couponModelMapper;
    @Autowired
    private BaseHttpService baseHttpService;

    /**
     * 更新会员信息（只支持部分信息修改）
     *
     * @param memberUpdateReqData
     * @return
     */
    @Override
    public CardUpdateResData updateMemberInfo(MemberUpdateReqData memberUpdateReqData) {
        //获取mpID
        Long cardKey = memberUpdateReqData.getCardKey();
        if (cardKey == null) {
            return new CardUpdateResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空！");
        }
        BaseInfoModel baseInfoModel1 = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        if (null == baseInfoModel1) {
            return new CardUpdateResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
        }

        String mpID = baseInfoModel1.getMpID();
        String cardType = baseInfoModel1.getCardType();
        String cardID = baseInfoModel1.getCardID();

        Map<String, Object> params = new HashMap<>();
        params.put("card_id", cardID);

        //设置基本信息
        Map<String, Object> baseInfo = new HashMap<>();

        CardBaseInfoUpdateReqData cardBaseInfoUpdateReqData = memberUpdateReqData.getCardBaseInfoUpdateReqData();
        prepareBaseInfo(baseInfo, cardBaseInfoUpdateReqData);

        Map<String, Object> card = new HashMap<>();
        prepareMemberInfo(card, memberUpdateReqData);
        card.put("base_info", baseInfo);
        params.put(cardType.toLowerCase(), card);

        Map<String, Object> map = WechatNameConverterUtil.convertToDBStyle(params);
        JSONObject jsonObject = baseHttpService.updateCardInfo(map, mpID);

        CardUpdateResData resultInfoBean = ResultUtil.getResultInfoBean(jsonObject, CardUpdateResData.class);
        //判断是否在微信端更新成功,如果成功存入数据库
        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            MemberModel memberModel1 = DataUtils.copyProperties(memberUpdateReqData, MemberModel.class);
            memberModel1.setCardKey(cardKey);
            memberModelMapper.updateByPrimaryKeySelective(memberModel1);

            BaseInfoModel baseInfoModel = DataUtils.copyProperties(cardBaseInfoUpdateReqData, BaseInfoModel.class);
            boolean sendCheck = resultInfoBean.isSendCheck();
            if (sendCheck) {
                baseInfoModel.setCardStatus(2);
            }
            baseInfoModel.setCardKey(cardKey);
            baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
        }
        return resultInfoBean;

    }


    /**
     * 更新优惠券基本信息（只支持部分信息修改）
     *
     * @param couponInfoUpdateReqData
     * @return
     */
    @Override
    public CardUpdateResData updateCouponInfo(CouponInfoUpdateReqData couponInfoUpdateReqData) {
        //获取mpID
        Long cardKey = couponInfoUpdateReqData.getCardKey();
        if (cardKey == null) {
            return new CardUpdateResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空！");
        }
        BaseInfoModel baseInfoModel1 = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        if (null == baseInfoModel1) {
            return new CardUpdateResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
        }
        String mpID = baseInfoModel1.getMpID();
        String cardType = baseInfoModel1.getCardType();
        String cardID = baseInfoModel1.getCardID();

        Map<String, Object> params = new HashMap<>();
        params.put("card_id", cardID);

        //设置基本信息
        Map<String, Object> baseInfo = new HashMap<>();
        CardBaseInfoUpdateReqData cardBaseInfoUpdateReqData = couponInfoUpdateReqData.getCardBaseInfoUpdateReqData();

        prepareBaseInfo(baseInfo, cardBaseInfoUpdateReqData);

        Map<String, Object> card = new HashMap<>();
        card.put("base_info", baseInfo);
        params.put(cardType.toLowerCase(), card);

        Map<String, Object> map = WechatNameConverterUtil.convertToDBStyle(params);
        JSONObject jsonObject = baseHttpService.updateCardInfo(map, mpID);

        CardUpdateResData resultInfoBean = ResultUtil.getResultInfoBean(jsonObject, CardUpdateResData.class);
        //判断是否在微信端更新成功,如果成功存入数据库
        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            boolean sendCheck = resultInfoBean.isSendCheck();
            BaseInfoModel baseInfoModel = DataUtils.copyProperties(cardBaseInfoUpdateReqData, BaseInfoModel.class);
            if (sendCheck) {
                baseInfoModel.setCardStatus(2);
            }
            baseInfoModel.setCardKey(cardKey);
            baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
        }
        return resultInfoBean;
    }

    /**
     * 更新微信端库存，同时修改库存总数
     */
    @Override
    public CardUpdateResData updateCouponSku(CardSkuUpdateReqData cardSkuUpdateReqData) {
        Long cardKey = cardSkuUpdateReqData.getCardKey();
        int increaseStockValue = cardSkuUpdateReqData.getIncreaseStockValue();
        int reduceStockValue = cardSkuUpdateReqData.getReduceStockValue();
        if (cardKey == null) {
            return new CardUpdateResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空！");
        }
        BaseInfoModel baseInfoModel1 = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        if (null == baseInfoModel1) {
            return new CardUpdateResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
        }
        String cardID = baseInfoModel1.getCardID();
        String mpID = baseInfoModel1.getMpID();

        String params = "{" +
                "\"card_id\": \"" + cardID + "\"," +
                "\"increase_stock_value\": " + increaseStockValue + "," +
                "\"reduce_stock_value\": " + reduceStockValue +
                "}";

        JSONObject jsonObject = baseHttpService.setCardSku(params, mpID);

        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            BaseInfoModel baseInfoModel = new BaseInfoModel();
            baseInfoModel.setCardKey(cardKey);
            Integer sku = baseInfoModel1.getSku();
            baseInfoModel.setSku(sku + increaseStockValue - reduceStockValue);
            baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
        }

        return ResultUtil.getResultInfoBean(jsonObject, CardUpdateResData.class);
    }

    @Override
    public CardUpdateResData updateMemberSku(CardSkuUpdateReqData cardSkuUpdateReqData) {
        Long cardKey = cardSkuUpdateReqData.getCardKey();
        if (cardKey == null) {
            return new CardUpdateResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空！");
        }
        BaseInfoModel baseInfoModel1 = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        if (null == baseInfoModel1) {
            return new CardUpdateResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
        }
        String cardID = baseInfoModel1.getCardID();
        String mpID = baseInfoModel1.getMpID();
        int increaseStockValue = cardSkuUpdateReqData.getIncreaseStockValue();
        int reduceStockValue = cardSkuUpdateReqData.getReduceStockValue();
        String params = "{" +
                "\"card_id\": \"" + cardID + "\"," +
                "\"increase_stock_value\": " + increaseStockValue + "," +
                "\"reduce_stock_value\": " + reduceStockValue +
                "}";
        JSONObject jsonObject = baseHttpService.setCardSku(params, mpID);

        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            BaseInfoModel baseInfoModel = new BaseInfoModel();
            baseInfoModel.setCardKey(cardKey);
            Integer sku = baseInfoModel1.getSku();
            baseInfoModel.setSku(sku + increaseStockValue - reduceStockValue);
            baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
        }

        return ResultUtil.getResultInfoBean(jsonObject, CardUpdateResData.class);
    }

    private void prepareBaseInfo(Map<String, Object> baseInfo, CardBaseInfoUpdateReqData cardBaseInfoUpdateReqData) {
        if (cardBaseInfoUpdateReqData == null) {
            return;
        }

        Boolean canGiveFriend = cardBaseInfoUpdateReqData.getCanGiveFriend();
        baseInfo.put("canGiveFriend", canGiveFriend);
        Boolean canShare = cardBaseInfoUpdateReqData.getCanShare();
        baseInfo.put("canShare", canShare);
        String title = cardBaseInfoUpdateReqData.getTitle();
        if (StringUtils.isNotBlank(title)) {
            baseInfo.put("title", title);
        }
        String centerSubTitle = cardBaseInfoUpdateReqData.getCenterSubTitle();
        if (StringUtils.isNotBlank(centerSubTitle)) {
            baseInfo.put("centerSubTitle", centerSubTitle);
        }
        String centerTitle = cardBaseInfoUpdateReqData.getCenterTitle();
        if (StringUtils.isNotBlank(centerTitle)) {
            baseInfo.put("centerTitle", centerTitle);
        }
        String centerUrl = cardBaseInfoUpdateReqData.getCenterUrl();
        if (StringUtils.isNotBlank(centerUrl)) {
            baseInfo.put("centerUrl", centerUrl);
        }
        String codeType = cardBaseInfoUpdateReqData.getCodeType();
        if (StringUtils.isNotBlank(codeType)) {
            baseInfo.put("codeType", codeType);
        }
        String color = cardBaseInfoUpdateReqData.getColor();
        if (StringUtils.isNotBlank(color)) {
            baseInfo.put("color", color);
        }
        String customUrl = cardBaseInfoUpdateReqData.getCustomUrl();
        if (StringUtils.isNotBlank(customUrl)) {
            baseInfo.put("customUrl", customUrl);
        }
        String customUrlName = cardBaseInfoUpdateReqData.getCustomUrlName();
        if (StringUtils.isNotBlank(customUrlName)) {
            baseInfo.put("customUrlName", customUrlName);
        }
        String customUrlSubTitle = cardBaseInfoUpdateReqData.getCustomUrlSubTitle();
        if (StringUtils.isNotBlank(customUrlSubTitle)) {
            baseInfo.put("customUrlSubTitle", customUrlSubTitle);
        }
        String dateInfo = cardBaseInfoUpdateReqData.getDateInfo();
        if (StringUtils.isNotBlank(dateInfo)) {
            JSONObject jsonObject = JSONObject.parseObject(dateInfo);
            baseInfo.put("dateInfo", jsonObject);
        }
        String description = cardBaseInfoUpdateReqData.getDescription();
        if (StringUtils.isNotBlank(description)) {
            baseInfo.put("description", description);
        }
        Integer getLimit = cardBaseInfoUpdateReqData.getGetLimit();
        baseInfo.put("getLimit", getLimit);
        Boolean isPayAndQrcode = cardBaseInfoUpdateReqData.getIsPayAndQrcode();
        baseInfo.put("isPayAndQrcode", isPayAndQrcode);
        Boolean isSwipeCard = cardBaseInfoUpdateReqData.getIsSwipeCard();
        baseInfo.put("isSwipeCard", isSwipeCard);
        String locationIdList = cardBaseInfoUpdateReqData.getLocationIdList();
        if (StringUtils.isNotBlank(locationIdList)) {
            String[] split = locationIdList.split(", ");
            baseInfo.put("locationIdList", split);
        }
        String logoUrl = cardBaseInfoUpdateReqData.getLogoUrl();
        if (StringUtils.isNotBlank(logoUrl)) {
            baseInfo.put("logoUrl", logoUrl);
        }
        String notice = cardBaseInfoUpdateReqData.getNotice();
        if (StringUtils.isNotBlank(notice)) {
            baseInfo.put("notice", notice);
        }
        String promotionUrl = cardBaseInfoUpdateReqData.getPromotionUrl();
        if (StringUtils.isNotBlank(promotionUrl)) {
            baseInfo.put("promotionUrl", promotionUrl);
        }
        String promotionUrlName = cardBaseInfoUpdateReqData.getPromotionUrlName();
        if (StringUtils.isNotBlank(promotionUrlName)) {
            baseInfo.put("promotionUrlName", promotionUrlName);
        }
        String promotionUrlSubTitle = cardBaseInfoUpdateReqData.getPromotionUrlSubTitle();
        if (StringUtils.isNotBlank(promotionUrlSubTitle)) {
            baseInfo.put("promotionUrlSubTitle", promotionUrlSubTitle);
        }
        String servicePhone = cardBaseInfoUpdateReqData.getServicePhone();
        if (StringUtils.isNotBlank(servicePhone)) {
            baseInfo.put("servicePhone", servicePhone);
        }
        Boolean useAllLocations = cardBaseInfoUpdateReqData.getUseAllLocations();
        baseInfo.put("useAllLocations", useAllLocations);
    }

    private void prepareMemberInfo(Map<String, Object> card, MemberUpdateReqData memberUpdateReqData) {
        String activateUrl = memberUpdateReqData.getActivateUrl();
        if (StringUtils.isNotBlank(activateUrl)) {
            card.put("activateUrl", activateUrl);
        }
        Boolean autoActivate = memberUpdateReqData.getAutoActivate();
        card.put("autoActivate", autoActivate);
        String backgroundPicUrl = memberUpdateReqData.getBackgroundPicUrl();
        if (StringUtils.isNotBlank(backgroundPicUrl)) {
            card.put("backgroundPicUrl", backgroundPicUrl);
        }
        String balanceRules = memberUpdateReqData.getBalanceRules();
        if (StringUtils.isNotBlank(balanceRules)) {
            card.put("balanceRules", balanceRules);
        }
        String balanceUrl = memberUpdateReqData.getBalanceUrl();
        if (StringUtils.isNotBlank(balanceUrl)) {
            card.put("balanceUrl", balanceUrl);
        }
        String bonusCleared = memberUpdateReqData.getBonusCleared();
        if (StringUtils.isNotBlank(bonusCleared)) {
            card.put("bonusCleared", bonusCleared);
        }
        String bonusRule = memberUpdateReqData.getBonusRule();
        if (StringUtils.isNotBlank(bonusRule)) {
            JSONObject jsonObject = JSONObject.parseObject(bonusRule);
            card.put("bonusRule", jsonObject);
        }
        String bonusRules = memberUpdateReqData.getBonusRules();
        if (StringUtils.isNotBlank(bonusRules)) {
            card.put("bonusRules", bonusRules);
        }
        String bonusUrl = memberUpdateReqData.getBonusUrl();
        if (StringUtils.isNotBlank(bonusUrl)) {
            card.put("bonusUrl", bonusUrl);
        }
        String customCell1 = memberUpdateReqData.getCustomCell1();
        if (StringUtils.isNotBlank(customCell1)) {
            JSONObject jsonObject = JSONObject.parseObject(customCell1);
            card.put("customCell1", jsonObject);
        }
        String customField1 = memberUpdateReqData.getCustomField1();
        if (StringUtils.isNotBlank(customField1)) {
            JSONObject jsonObject = JSONObject.parseObject(customField1);
            card.put("customField1", jsonObject);
        }
        String customField2 = memberUpdateReqData.getCustomField2();
        if (StringUtils.isNotBlank(customField2)) {
            JSONObject jsonObject = JSONObject.parseObject(customField2);
            card.put("customField2", jsonObject);
        }
        String customField3 = memberUpdateReqData.getCustomField3();
        if (StringUtils.isNotBlank(customField3)) {
            JSONObject jsonObject = JSONObject.parseObject(customField3);
            card.put("customField3", jsonObject);
        }
        Integer discount = memberUpdateReqData.getDiscount();
        card.put("discount", discount);
        String prerogative = memberUpdateReqData.getPrerogative();
        if (StringUtils.isNotBlank(prerogative)) {
            card.put("prerogative", prerogative);
        }
        Boolean supplyBalance = memberUpdateReqData.getSupplyBalance();
        card.put("supplyBalance", supplyBalance);
        Boolean supplyBonus = memberUpdateReqData.getSupplyBonus();
        card.put("supplyBonus", supplyBonus);
        Boolean wxActivate = memberUpdateReqData.getWxActivate();
        card.put("wxActivate", wxActivate);

    }

}
