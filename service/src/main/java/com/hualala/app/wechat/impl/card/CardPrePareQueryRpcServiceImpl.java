package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardDeleteRpcService;
import com.hualala.app.wechat.CardPrePareQueryRpcService;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.CardStatus;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.mapper.WechatMpMapper;
import com.hualala.app.wechat.mapper.card.AdvancedModelMapper;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.card.CouponModelMapper;
import com.hualala.app.wechat.mapper.card.MemberModelMapper;
import com.hualala.app.wechat.model.card.*;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.service.MpInfoService;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WechatNameConverterUtil;
import com.hualala.core.utils.DataUtils;
import com.hualala.core.utils.SystemUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by renjianfei on 2017/4/26.
 */
@Service
public class CardPrePareQueryRpcServiceImpl implements CardPrePareQueryRpcService {

    @Autowired
    private MemberModelMapper memberModelMapper;
    @Autowired
    private CouponModelMapper couponModelMapper;
    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;
    @Autowired
    private AdvancedModelMapper advancedModelMapper;
    @Autowired
    private BaseHttpService baseHttpService;

    /**
     * 查一个
     *
     * @param cardQuery
     * @return
     */
    @Override
    public MemberResData queryMemberByCardKey(CardQuery cardQuery) {
        String cardKey = cardQuery.getCardKey();
        if (StringUtils.isBlank(cardKey)) {
            return new MemberResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空");
        }
        MemberModel memberModel = memberModelMapper.selectByPrimaryKey(cardQuery.getCardKey());
        return DataUtils.copyProperties(memberModel, MemberResData.class);
    }

    /**
     * 查所有  目前只支持mpID
     *
     * @param cardQuery
     * @return
     */
    @Override
    public MemberResDataList queryMemberList(CardQuery cardQuery) {
        String mpID = cardQuery.getMpID();
        Long groupID = cardQuery.getGroupID();
        if (StringUtils.isBlank(mpID)) {
            return new MemberResDataList().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID不能为空！");
        }
        MemberModelQuery memberModelQuery = new MemberModelQuery();
        MemberModelQuery.Criteria criteria = memberModelQuery.createCriteria().andMpIDEqualTo(mpID);
        String title = cardQuery.getTitle();
        if (StringUtils.isNotBlank(title)) {
            criteria.andTitleLike(title);
        }

        String cardType = cardQuery.getCardType();
        if (StringUtils.isNotBlank(cardType)) {
            criteria.andCardTypeEqualTo(cardType);
        }
        MemberResDataList memberResData = new MemberResDataList();

        Integer pageNO = cardQuery.getPageNO();
        Integer pageSize = cardQuery.getPageSize();
        if (pageNO != null && pageSize != null) {
            int count = memberModelMapper.countByExample(memberModelQuery);
            memberModelQuery.setPageNo(pageNO);
            memberModelQuery.setPageSize(pageSize);
            memberResData.setPageNO(pageNO);
            memberResData.setPageSize(pageSize);
            memberResData.setTotleCount(count);
        }
        List<MemberResData> list = new ArrayList<>();
        List<MemberModel> memberModels = memberModelMapper.selectByExample(memberModelQuery);
        for (MemberModel memberModel : memberModels) {
            list.add(DataUtils.copyProperties(memberModel, MemberResData.class));
        }
        memberResData.setMemberResData(list);
        return memberResData;
    }




    /**
     * 查一个
     *
     * @param cardQuery
     * @return
     */
    @Override
    public CouponResData queryCouponByCardKey(CardQuery cardQuery) {
        String cardKey = cardQuery.getCardKey();
        if (StringUtils.isBlank(cardKey)) {
            return new CouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空");
        }
        CouponModel couponModel = couponModelMapper.selectByPrimaryKey(cardKey);

        if (null == couponModel) {
            return new CouponResData().setResultInfo(ErrorCodes.WECHAT_CARD_RESULT_NULL, "没有找到对应的结果");
        }

        return DataUtils.copyProperties(couponModel, CouponResData.class);
    }

    /**
     * 查多个
     * @param cardQuery
     * @return
     */
    @Override
    public CouponResDataList queryCouponList(CardQuery cardQuery) {
        String mpID = cardQuery.getMpID();
        Long groupID = cardQuery.getGroupID();
        if (StringUtils.isBlank(mpID)) {
            return new CouponResDataList().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID不能为空！");
        }
        CouponModelQuery couponModelQuery = new CouponModelQuery();
        CouponModelQuery.Criteria criteria = couponModelQuery.createCriteria().andMpIDEqualTo(mpID);

        String title = cardQuery.getTitle();
        if (StringUtils.isNotBlank(title)) {
            criteria.andTitleLike(title);
        }

        String cardType = cardQuery.getCardType();
        if (StringUtils.isNotBlank(cardType)) {
            criteria.andCardTypeEqualTo(cardType);
        }
        CouponResDataList couponResData = new CouponResDataList();

        Integer pageNO = cardQuery.getPageNO();
        Integer pageSize = cardQuery.getPageSize();
        if (pageNO != null && pageSize != null) {
            int count = couponModelMapper.countByExample(couponModelQuery);
            couponModelQuery.setPageNo(pageNO);
            couponResData.setPageNO(pageNO);
            couponModelQuery.setPageSize(pageSize);
            couponResData.setPageSize(pageSize);
            couponResData.setTotleCount(count);
        }

        List<CouponModel> couponModels = couponModelMapper.selectByExample(couponModelQuery);
        List<CouponResData> list = new ArrayList<>();
        for (CouponModel couponModel : couponModels) {
            list.add(DataUtils.copyProperties(couponModel, CouponResData.class));
        }


        couponResData.setCouponResDataList(list);
        return couponResData;
    }




    @Override
    public CardBaseInfoResData queryBaseInfoByCardKey(CardQuery cardQuery) {
        String cardKey = cardQuery.getCardKey();
        if (StringUtils.isBlank(cardKey)) {
            return new CardBaseInfoResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空");
        }
        BaseInfoModel baseInfoModel = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        return DataUtils.copyProperties(baseInfoModel, CardBaseInfoResData.class);
    }

    @Override
    public CardAdvancedInfoResData queryAdvancedInfoByCardKey(CardQuery cardQuery) {
        String cardKey = cardQuery.getCardKey();
        if (StringUtils.isBlank(cardKey)) {
            return new CardAdvancedInfoResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空");
        }
        AdvancedModel advancedModel = advancedModelMapper.selectByPrimaryKey(cardKey);
        return DataUtils.copyProperties(advancedModel, CardAdvancedInfoResData.class);
    }

    @Override
    public CardSyncResData syncCouponInfo(CardSyncReqData cardSyncReqData) {
        String cardKey = cardSyncReqData.getCardKey();
        CouponModel couponModel = couponModelMapper.selectByPrimaryKey(cardKey);
        if (null == couponModel){
            return new CardSyncResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");

        }
        String cardID = couponModel.getCardID();
        String mpID = couponModel.getMpID();
        String params = "{\"card_id\":\""+cardID+"\"}";
        JSONObject cardInfo = baseHttpService.getCardInfo(params, mpID);
        if (!cardInfo.getBoolean(WechatMessageType.IS_SUCCESS)){
            return ResultUtil.getResultInfoBean(cardInfo,CardSyncResData.class);
        }
        JSONObject card = (JSONObject)cardInfo.get("card");
        String cardType = (String) card.get("card_type");
        JSONObject coupon = (JSONObject)card.get(cardType.toLowerCase());
        Map<String, Object> map = WechatNameConverterUtil.convertToJavaStyle(coupon);
        JSONObject baseInfo = (JSONObject) JSONObject.toJSON(map.get("baseInfo"));
        //TODO Status 需要判断
//“CARD_STATUS_NOT_VERIFY”,待审核；
//“CARD_STATUS_VERIFY_FAIL”,审核失败；
//“CARD_STATUS_VERIFY_OK”，通过审核；
//“CARD_STATUS_DELETE”，卡券被商户删除；
//“CARD_STATUS_DISPATCH”，在公众平台投放过的卡券；
        String status = baseInfo.getString("status");
        CardStatus cardStatus = CardStatus.valueOf(status);
        CouponModel couponModel1 = DataUtils.mapToBean(map, CouponModel.class);
        couponModel1.setCardStatus(cardStatus.getValue());
        couponModel1.setCardKey(cardKey);

        couponModelMapper.updateByPrimaryKeySelective(couponModel);

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
        advancedInfo.replace("timeLimit",timeLimit);
        advancedInfo.remove("textImage");
        advancedInfo.put("textImageList",textImageList);
        advancedInfo.replace("businessService",businessService);
        //TODO Advanced 选项 consumeShareCardList, shareFriends  待处理 创建字段中没有 查询字段中出现的
        advancedInfo.replace("consumeShareCardList",consumeShareCardList);
        advancedInfo.replace("shareFriends",shareFriends);

        advancedInfo.remove("abstract");
        advancedInfo.put("abstractInfo",anAbstract);
        advancedInfo.replace("useCodition",useCodition);
        AdvancedModel advancedModel = DataUtils.mapToBean(advancedInfo, AdvancedModel.class);
        advancedModel.setCardKey(cardKey);

        advancedModelMapper.updateByPrimaryKeySelective(advancedModel);

        return ResultUtil.getResultInfoBean(cardInfo,CardSyncResData.class);
    }

    @Override
    public CardSyncResData syncMemberInfo(CardSyncReqData cardSyncReqData) {
        String cardKey = cardSyncReqData.getCardKey();
        MemberModel memberModel = memberModelMapper.selectByPrimaryKey(cardKey);
        if (null == memberModel){
            return new CardSyncResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");

        }
        String cardID = memberModel.getCardID();
        String mpID = memberModel.getMpID();
        String params = "{\"card_id\":\""+cardID+"\"}";
        JSONObject cardInfo = baseHttpService.getCardInfo(params, mpID);
        if (!cardInfo.getBoolean(WechatMessageType.IS_SUCCESS)){
            return ResultUtil.getResultInfoBean(cardInfo,CardSyncResData.class);
        }
        JSONObject card = (JSONObject)cardInfo.get("card");
        String cardType = (String) card.get("card_type");
        JSONObject memberCard = (JSONObject)card.get(cardType.toLowerCase());
        Map<String, Object> map = WechatNameConverterUtil.convertToJavaStyle(memberCard);
        JSONObject memberInfo = (JSONObject) JSONObject.toJSON(map);

        //将json格式字段转为String
        String customField1 = memberInfo.getString("customField1");
        memberInfo.replace("customField1",customField1);

        String customField2 = memberInfo.getString("customField2");
        memberInfo.replace("customField2",customField2);

        String customField3 = memberInfo.getString("customField3");
        memberInfo.replace("customField3",customField3);

        String customCell1 = memberInfo.getString("customCell1");
        memberInfo.replace("customCell1",customCell1);

        String bonusRule = memberInfo.getString("bonusRule");
        memberInfo.replace("bonusRule",bonusRule);


        JSONObject baseInfo = memberInfo.getJSONObject("baseInfo");

        //TODO Status 需要判断
//“CARD_STATUS_NOT_VERIFY”,待审核；
//“CARD_STATUS_VERIFY_FAIL”,审核失败；
//“CARD_STATUS_VERIFY_OK”，通过审核；
//“CARD_STATUS_DELETE”，卡券被商户删除；
//“CARD_STATUS_DISPATCH”，在公众平台投放过的卡券；
        String status = baseInfo.getString("status");
        CardStatus cardStatus = CardStatus.valueOf(status);
        CouponModel couponModel1 = DataUtils.mapToBean(memberInfo, CouponModel.class);
        couponModel1.setCardStatus(cardStatus.getValue());
        couponModel1.setCardKey(cardKey);

        memberModelMapper.updateByPrimaryKeySelective(memberModel);

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
        advancedInfo.replace("timeLimit",timeLimit);
        advancedInfo.remove("textImage");
        advancedInfo.put("textImageList",textImageList);
        advancedInfo.replace("businessService",businessService);
        //TODO Advanced 选项 consumeShareCardList, shareFriends  待处理 创建字段中没有 查询字段中出现的
        advancedInfo.replace("consumeShareCardList",consumeShareCardList);
        advancedInfo.replace("shareFriends",shareFriends);

        advancedInfo.remove("abstract");
        advancedInfo.put("abstractInfo",anAbstract);
        advancedInfo.replace("useCodition",useCodition);
        AdvancedModel advancedModel = DataUtils.mapToBean(advancedInfo, AdvancedModel.class);
        advancedModel.setCardKey(cardKey);

        advancedModelMapper.updateByPrimaryKeySelective(advancedModel);

        return ResultUtil.getResultInfoBean(cardInfo,CardSyncResData.class);
    }
}
