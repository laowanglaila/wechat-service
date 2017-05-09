package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardStatusEnum;
import com.hualala.app.wechat.CardSyncRpcService;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.mapper.WechatMpMapper;
import com.hualala.app.wechat.mapper.card.AdvancedModelMapper;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.card.CouponModelMapper;
import com.hualala.app.wechat.mapper.card.MemberModelMapper;
import com.hualala.app.wechat.model.card.*;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.card.CreateCardKeyService;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WechatNameConverterUtil;
import com.hualala.core.utils.DataUtils;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.LongHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by renjianfei on 2017/5/4.
 */
@Service
public class CardSyncRpcServiceImpl implements CardSyncRpcService {
    @Autowired
    private MemberModelMapper memberModelMapper;
    @Autowired
    private CouponModelMapper couponModelMapper;
    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;
    @Autowired
    private AdvancedModelMapper advancedModelMapper;

    @Autowired
    private WechatMpMapper wechatMpMapper;
    @Autowired
    private CreateCardKeyService createCardKeyService;

    @Autowired
    private BaseHttpService baseHttpService;

    @Override
    public CardListResData getCardList(CardListReqData cardListReqData) {
        String mpID = cardListReqData.getMpID();
        if (StringUtils.isBlank(mpID)) {
            return new CardListResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "公众号mpID不允许为空");
        }
        int count = cardListReqData.getCount();
        int offset = cardListReqData.getOffset();
        List<String> statusList = cardListReqData.getStatusList();

        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("count", count);
        params.put("status_list", statusList);
        JSONObject jsonObject = baseHttpService.getBatchCardID(params, mpID);

        return ResultUtil.getResultInfoBean(jsonObject, CardListResData.class);
    }


    @Override
    public CardSyncResData syncMemberInfo(CardSyncReqData cardSyncReqData) {
        Long cardKey = cardSyncReqData.getCardKey();
        MemberModel memberModel = memberModelMapper.selectByPrimaryKey(cardKey);
        if (null == memberModel) {
            return new CardSyncResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");

        }
        String cardID = memberModel.getCardID();
        String mpID = memberModel.getMpID();
        String params = "{\"card_id\":\"" + cardID + "\"}";
        JSONObject cardInfo = baseHttpService.getCardInfo(params, mpID);
        if (!cardInfo.getBoolean(WechatMessageType.IS_SUCCESS)) {
            return ResultUtil.getResultInfoBean(cardInfo, CardSyncResData.class);
        }
        JSONObject card = (JSONObject) cardInfo.get("card");
        String cardType = (String) card.get("card_type");
        JSONObject memberCard = (JSONObject) card.get(cardType.toLowerCase());
        Map<String, Object> map = WechatNameConverterUtil.convertToJavaStyle(memberCard);
        JSONObject memberInfo = (JSONObject) JSONObject.toJSON(map);

        //将json格式字段转为String
        String customField1 = memberInfo.getString("customField1");
        memberInfo.replace("customField1", customField1);

        String customField2 = memberInfo.getString("customField2");
        memberInfo.replace("customField2", customField2);

        String customField3 = memberInfo.getString("customField3");
        memberInfo.replace("customField3", customField3);

        String customCell1 = memberInfo.getString("customCell1");
        memberInfo.replace("customCell1", customCell1);

        String bonusRule = memberInfo.getString("bonusRule");
        memberInfo.replace("bonusRule", bonusRule);


        JSONObject baseInfo = memberInfo.getJSONObject("baseInfo");
        //判断会员卡状态
        String status = baseInfo.getString("status");
        CardStatusEnum cardStatusEnum = CardStatusEnum.valueOf(status);
        MemberModel memberModel1 = DataUtils.mapToBean(memberInfo, MemberModel.class);
        memberModel1.setCardStatus(cardStatusEnum.getValue());
        memberModel1.setCardKey(cardKey);

        memberModelMapper.updateByPrimaryKeySelective(memberModel1);

        String dateInfo = baseInfo.getString("dateInfo");
        baseInfo.replace("dateInfo", dateInfo);
        JSONObject sku = baseInfo.getJSONObject("sku");
        Integer totalQuantity = sku.getInteger("totalQuantity");
        baseInfo.replace("sku", totalQuantity);
        //TODO 将实时库存放入Redis中保存
//        Integer quantity = sku.getInteger("quantity");


        String locationIdList = baseInfo.getString("locationIdList");
        String locationList = locationIdList.replace("[", "").replace("]", "");
        locationList = StringUtils.isBlank(locationList) ? null : locationList;
        baseInfo.replace("locationIdList", locationList);
        BaseInfoModel baseInfoModel = DataUtils.mapToBean(baseInfo, BaseInfoModel.class);
        baseInfoModel.setCardKey(cardKey);

        baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);

        JSONObject advancedInfo = (JSONObject) JSONObject.toJSON(map.get("advancedInfo"));
        String timeLimit = advancedInfo.getString("timeLimit");
        String textImageList = advancedInfo.getString("textImageList");
        String businessService = advancedInfo.getString("businessService");
        String consumeShareCardList = advancedInfo.getString("consumeShareCardList");
        Boolean shareFriends = advancedInfo.getBoolean("shareFriends");
        String anAbstract = advancedInfo.getString("abstract");
        String useCodition = advancedInfo.getString("useCodition");
        advancedInfo.replace("timeLimit", timeLimit);
        advancedInfo.remove("textImage");
        advancedInfo.put("textImageList", textImageList);
        advancedInfo.replace("businessService", businessService);
        //TODO Advanced 选项 consumeShareCardList, shareFriends  待处理 创建字段中没有 查询字段中出现的
        advancedInfo.replace("consumeShareCardList", consumeShareCardList);
        advancedInfo.replace("shareFriends", shareFriends);

        advancedInfo.remove("abstract");
        advancedInfo.put("abstractInfo", anAbstract);
        advancedInfo.replace("useCodition", useCodition);
        AdvancedModel advancedModel = DataUtils.mapToBean(advancedInfo, AdvancedModel.class);
        advancedModel.setCardKey(cardKey);

        advancedModelMapper.updateByPrimaryKeySelective(advancedModel);

        return ResultUtil.getResultInfoBean(cardInfo, CardSyncResData.class);
    }

    @Override
    public CardSyncResData syncCouponInfo(CardSyncReqData cardSyncReqData) {
        Long cardKey = cardSyncReqData.getCardKey();
        if (cardKey == null) {
            return new CardSyncResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不能为空！");
        }
        CouponModel couponModel = couponModelMapper.selectByPrimaryKey(cardKey);
        if (null == couponModel) {
            return new CardSyncResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");

        }
        String cardID = couponModel.getCardID();
        String mpID = couponModel.getMpID();
        String params = "{\"card_id\":\"" + cardID + "\"}";
        JSONObject cardInfo = baseHttpService.getCardInfo(params, mpID);
        if (!cardInfo.getBoolean(WechatMessageType.IS_SUCCESS)) {
            return ResultUtil.getResultInfoBean(cardInfo, CardSyncResData.class);
        }
        JSONObject card = (JSONObject) cardInfo.get("card");
        String cardType = (String) card.get("card_type");
        JSONObject coupon = (JSONObject) card.get(cardType.toLowerCase());
        Map<String, Object> map = WechatNameConverterUtil.convertToJavaStyle(coupon);
        JSONObject baseInfo = (JSONObject) JSONObject.toJSON(map.get("baseInfo"));
        //tatus 需要判断
//“CARD_STATUS_NOT_VERIFY”,待审核；
//“CARD_STATUS_VERIFY_FAIL”,审核失败；
//“CARD_STATUS_VERIFY_OK”，通过审核；
//“CARD_STATUS_DELETE”，卡券被商户删除；
//“CARD_STATUS_DISPATCH”，在公众平台投放过的卡券；
        String status = baseInfo.getString("status");
        CardStatusEnum cardStatusEnum = CardStatusEnum.valueOf(status);
        CouponModel couponModel1 = DataUtils.mapToBean(map, CouponModel.class);
        couponModel1.setCardStatus(cardStatusEnum.getValue());
        couponModel1.setCardKey(cardKey);

        couponModelMapper.updateByPrimaryKeySelective(couponModel1);

        String dateInfo = baseInfo.getString("dateInfo");
        baseInfo.replace("dateInfo", dateInfo);
        JSONObject sku = baseInfo.getJSONObject("sku");
        Integer totalQuantity = sku.getInteger("totalQuantity");
        baseInfo.replace("sku", totalQuantity);
        //TODO 将实时库存放入Redis中保存
//        Integer quantity = sku.getInteger("quantity");


        String locationIdList = baseInfo.getString("locationIdList");
        String locationList = locationIdList.replace("[", "").replace("]", "");
        locationList = StringUtils.isBlank(locationList) ? null : locationList;
        baseInfo.replace("locationIdList", locationList);
        BaseInfoModel baseInfoModel = DataUtils.mapToBean(baseInfo, BaseInfoModel.class);
        baseInfoModel.setCardKey(cardKey);

        baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);

        JSONObject advancedInfo = (JSONObject) JSONObject.toJSON(map.get("advancedInfo"));
        String timeLimit = advancedInfo.getString("timeLimit");
        String textImageList = advancedInfo.getString("textImageList");
        String businessService = advancedInfo.getString("businessService");
        String consumeShareCardList = advancedInfo.getString("consumeShareCardList");
        Boolean shareFriends = advancedInfo.getBoolean("shareFriends");
        String anAbstract = advancedInfo.getString("abstract");
        String useCodition = advancedInfo.getString("useCodition");
        advancedInfo.replace("timeLimit", timeLimit);
        advancedInfo.remove("textImage");
        advancedInfo.put("textImageList", textImageList);
        advancedInfo.replace("businessService", businessService);
        //TODO Advanced 选项 consumeShareCardList, shareFriends  待处理 创建字段中没有 查询字段中出现的
        advancedInfo.replace("consumeShareCardList", consumeShareCardList);
        advancedInfo.replace("shareFriends", shareFriends);

        advancedInfo.remove("abstract");
        advancedInfo.put("abstractInfo", anAbstract);
        advancedInfo.replace("useCodition", useCodition);
        AdvancedModel advancedModel = DataUtils.mapToBean(advancedInfo, AdvancedModel.class);
        advancedModel.setCardKey(cardKey);

        advancedModelMapper.updateByPrimaryKeySelective(advancedModel);

        return ResultUtil.getResultInfoBean(cardInfo, CardSyncResData.class);
    }


    @Override
    public CardDownloadResData downloadCardInfo(CardDownloadReqData cardSyncReqData) {
        String mpID = cardSyncReqData.getMpID();
        String cardID = cardSyncReqData.getCardID();

        if (StringUtils.isBlank(cardID)) {
            return new CardDownloadResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_ID_NULL, "cardID不能为空");
        }
        String params = "{\"card_id\":\"" + cardID + "\"}";
        JSONObject cardInfo = baseHttpService.getCardInfo(params, mpID);
        if (!cardInfo.getBoolean(WechatMessageType.IS_SUCCESS)) {
            return ResultUtil.getResultInfoBean(cardInfo, CardDownloadResData.class);
        }
        JSONObject card = cardInfo.getJSONObject("card");
        String cardType = card.getString("card_type");
        //判断卡类型，决定存入哪张表
        Long cardKey = null;
        if ("MEMBER_CARD".equals(cardType)) {
            try {
                cardKey = saveMemberInfo(mpID, cardID, cardInfo);
            } catch (ExecutionException e) {
                e.printStackTrace();
                return new CardDownloadResData()
                        .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey获取失败\r\n"+e.getMessage());
            }
        } else {
            try {
                cardKey = saveCoupon(mpID, cardID, cardInfo);
            } catch (ExecutionException e) {
                e.printStackTrace();
                return new CardDownloadResData()
                        .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey获取失败:\r\n"+e.getMessage());
            }
        }

        CardDownloadResData resultInfoBean = ResultUtil.getResultInfoBean(cardInfo, CardDownloadResData.class);
        resultInfoBean.setCardKey(cardKey);
        return resultInfoBean;
    }

    /**
     * 将请求回来的数据存入数据库，如果存在则更新，不存在则插入
     *
     * @param cardID
     * @param cardInfo
     */
    private Long saveCoupon(String mpID, String cardID, JSONObject cardInfo) throws ExecutionException {
        JSONObject card = (JSONObject) cardInfo.get("card");
        String cardType = (String) card.get("card_type");
        JSONObject coupon = (JSONObject) card.get(cardType.toLowerCase());
        Map<String, Object> map = WechatNameConverterUtil.convertToJavaStyle(coupon);
        JSONObject baseInfo = (JSONObject) JSONObject.toJSON(map.get("baseInfo"));
        //tatus 需要判断
//“CARD_STATUS_NOT_VERIFY”,待审核；
//“CARD_STATUS_VERIFY_FAIL”,审核失败；
//“CARD_STATUS_VERIFY_OK”，通过审核；
//“CARD_STATUS_DELETE”，卡券被商户删除；
//“CARD_STATUS_DISPATCH”，在公众平台投放过的卡券；
        String status = baseInfo.getString("status");
        String title = baseInfo.getString("title");
        CardStatusEnum cardStatusEnum = CardStatusEnum.valueOf(status);
        CouponModel couponModel1 = DataUtils.mapToBean(map, CouponModel.class);
        couponModel1.setCardStatus(cardStatusEnum.getValue());
        couponModel1.setTitle(title);
        couponModel1.setCardType(cardType);
        couponModel1.setCardID(cardID);

        String dateInfo = baseInfo.getString("dateInfo");
        baseInfo.replace("dateInfo", dateInfo);
        JSONObject sku = baseInfo.getJSONObject("sku");
        Integer totalQuantity = sku.getInteger("totalQuantity");
        baseInfo.replace("sku", totalQuantity);
        //TODO 将实时库存放入Redis中保存
//        Integer quantity = sku.getInteger("quantity");


        String locationIdList = baseInfo.getString("locationIdList");
        String locationList = locationIdList.replace("[", "").replace("]", "");
        locationList = StringUtils.isBlank(locationList) ? null : locationList;
        baseInfo.replace("locationIdList", locationList);
        BaseInfoModel baseInfoModel = DataUtils.mapToBean(baseInfo, BaseInfoModel.class);

        JSONObject advancedInfo = (JSONObject) JSONObject.toJSON(map.get("advancedInfo"));
        String timeLimit = advancedInfo.getString("timeLimit");
        String textImageList = advancedInfo.getString("textImageList");
        String businessService = advancedInfo.getString("businessService");
        String consumeShareCardList = advancedInfo.getString("consumeShareCardList");
        Boolean shareFriends = advancedInfo.getBoolean("shareFriends");
        String anAbstract = advancedInfo.getString("abstract");
        String useCodition = advancedInfo.getString("useCodition");
        advancedInfo.replace("timeLimit", timeLimit);
        advancedInfo.remove("textImage");
        advancedInfo.put("textImageList", textImageList);
        advancedInfo.replace("businessService", businessService);
        //TODO Advanced 选项 consumeShareCardList, shareFriends  待处理 创建字段中没有 查询字段中出现的
        advancedInfo.replace("consumeShareCardList", consumeShareCardList);
        advancedInfo.replace("shareFriends", shareFriends);

        advancedInfo.remove("abstract");
        advancedInfo.put("abstractInfo", anAbstract);
        advancedInfo.replace("useCodition", useCodition);
        AdvancedModel advancedModel = DataUtils.mapToBean(advancedInfo, AdvancedModel.class);

        CouponModelQuery couponModelQuery = new CouponModelQuery();
        couponModelQuery.createCriteria()
                .andCardIDEqualTo(cardID);
        List<CouponModel> couponModels = couponModelMapper.selectByExample(couponModelQuery);
        Long cardKey = null;
        if (couponModels != null && couponModels.size() > 0) {
            //如果已经存在就更新
            cardKey = couponModels.get(0).getCardKey();
            couponModel1.setCardKey(cardKey);
            baseInfoModel.setCardKey(cardKey);
            advancedModel.setCardKey(cardKey);
            couponModelMapper.updateByPrimaryKeySelective(couponModel1);
            baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
            advancedModelMapper.updateByPrimaryKeySelective(advancedModel);
        } else {
            //否则就插入
            Map<String, Object> params = new HashMap<>();
            params.put("mpID", mpID);
            List<Map<String, Object>> maps = wechatMpMapper.queryByParams(params);
            if (maps.size() > 0) {
                //GroupID是不能为空的
                Integer groupIDs = (Integer) maps.get(0).get("groupID");
                couponModel1.setGroupID(groupIDs.longValue());
                cardKey = createCardKeyService.createCardKey(groupIDs.longValue());
            }
            couponModel1.setMpID(mpID);
            couponModel1.setCardKey(cardKey);
            baseInfoModel.setCardKey(cardKey);
            advancedModel.setCardKey(cardKey);
            couponModelMapper.insertSelective(couponModel1);
            baseInfoModelMapper.insertSelective(baseInfoModel);
            advancedModelMapper.insertSelective(advancedModel);


        }
        return cardKey;
    }

    private Long saveMemberInfo(String mpID, String cardID, JSONObject cardInfo) throws ExecutionException {
        JSONObject card = (JSONObject) cardInfo.get("card");
        String cardType = (String) card.get("card_type");
        JSONObject memberCard = (JSONObject) card.get(cardType.toLowerCase());
        Map<String, Object> map = WechatNameConverterUtil.convertToJavaStyle(memberCard);
        JSONObject memberInfo = (JSONObject) JSONObject.toJSON(map);

        //将json格式字段转为String
        String customField1 = memberInfo.getString("customField1");
        memberInfo.replace("customField1", customField1);

        String customField2 = memberInfo.getString("customField2");
        memberInfo.replace("customField2", customField2);

        String customField3 = memberInfo.getString("customField3");
        memberInfo.replace("customField3", customField3);

        String customCell1 = memberInfo.getString("customCell1");
        memberInfo.replace("customCell1", customCell1);

        String bonusRule = memberInfo.getString("bonusRule");
        memberInfo.replace("bonusRule", bonusRule);


        JSONObject baseInfo = memberInfo.getJSONObject("baseInfo");
        //判断会员卡状态
        String status = baseInfo.getString("status");
        String title = baseInfo.getString("title");
        CardStatusEnum cardStatusEnum = CardStatusEnum.valueOf(status);
        MemberModel memberModel1 = DataUtils.mapToBean(memberInfo, MemberModel.class);
        memberModel1.setCardStatus(cardStatusEnum.getValue());
        memberModel1.setTitle(title);
        memberModel1.setCardType(cardType);
        memberModel1.setCardID(cardID);

        String dateInfo = baseInfo.getString("dateInfo");
        baseInfo.replace("dateInfo", dateInfo);
        JSONObject sku = baseInfo.getJSONObject("sku");
        Integer totalQuantity = sku.getInteger("totalQuantity");
        baseInfo.replace("sku", totalQuantity);
        //TODO 将实时库存放入Redis中保存
//        Integer quantity = sku.getInteger("quantity");


        String locationIdList = baseInfo.getString("locationIdList");
        String locationList = locationIdList.replace("[", "").replace("]", "");
        locationList = StringUtils.isBlank(locationList) ? null : locationList;
        baseInfo.replace("locationIdList", locationList);
        BaseInfoModel baseInfoModel = DataUtils.mapToBean(baseInfo, BaseInfoModel.class);


        JSONObject advancedInfo = (JSONObject) JSONObject.toJSON(map.get("advancedInfo"));
        String timeLimit = advancedInfo.getString("timeLimit");
        String textImageList = advancedInfo.getString("textImageList");
        String businessService = advancedInfo.getString("businessService");
        String consumeShareCardList = advancedInfo.getString("consumeShareCardList");
        Boolean shareFriends = advancedInfo.getBoolean("shareFriends");
        String anAbstract = advancedInfo.getString("abstract");
        String useCodition = advancedInfo.getString("useCodition");
        advancedInfo.replace("timeLimit", timeLimit);
        advancedInfo.remove("textImage");
        advancedInfo.put("textImageList", textImageList);
        advancedInfo.replace("businessService", businessService);
        //TODO Advanced 选项 consumeShareCardList, shareFriends  待处理 创建字段中没有 查询字段中出现的
        advancedInfo.replace("consumeShareCardList", consumeShareCardList);
        advancedInfo.replace("shareFriends", shareFriends);

        advancedInfo.remove("abstract");
        advancedInfo.put("abstractInfo", anAbstract);
        advancedInfo.replace("useCodition", useCodition);
        AdvancedModel advancedModel = DataUtils.mapToBean(advancedInfo, AdvancedModel.class);
        MemberModelQuery memberModelQuery = new MemberModelQuery();
        memberModelQuery.createCriteria()
                .andCardIDEqualTo(cardID);
        List<MemberModel> memberModels = memberModelMapper.selectByExample(memberModelQuery);
        Long cardKey = null;
        if (memberModels != null && memberModels.size() > 0) {
            //如果已经存在就更新
            cardKey = memberModels.get(0).getCardKey();
            memberModel1.setCardKey(cardKey);
            baseInfoModel.setCardKey(cardKey);
            advancedModel.setCardKey(cardKey);
            memberModelMapper.updateByPrimaryKeySelective(memberModel1);
            baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
            advancedModelMapper.updateByPrimaryKeySelective(advancedModel);
        } else {
            //否则就插入
                    Map < String, Object > params = new HashMap<>();
            params.put("mpID", mpID);
            List<Map<String, Object>> maps = wechatMpMapper.queryByParams(params);
            if (maps.size() > 0) {
                //GroupID是不能为空的
                Integer groupIDs = (Integer) maps.get(0).get("groupID");
                memberModel1.setGroupID(groupIDs.longValue());
                cardKey = createCardKeyService.createCardKey(groupIDs.longValue());
            }
            memberModel1.setMpID(mpID);
            memberModel1.setCardKey(cardKey);
            baseInfoModel.setCardKey(cardKey);
            advancedModel.setCardKey(cardKey);
            memberModelMapper.insertSelective(memberModel1);
            baseInfoModelMapper.insertSelective(baseInfoModel);
            advancedModelMapper.insertSelective(advancedModel);
        }
        return cardKey;
    }

}
