package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardCodeRpcService;
import com.hualala.app.wechat.CardUpdateRpcService;
import com.hualala.app.wechat.sdk.mp.common.ErrorCodes;
import com.hualala.app.wechat.sdk.mp.common.WechatExceptionTypeEnum;
import com.hualala.app.wechat.sdk.mp.exception.WechatException;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.card.MemberMsgModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.MemberMsgModel;
import com.hualala.app.wechat.model.card.MemberMsgModelQuery;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by renjianfei on 2017/5/3.
 */
@Service
public class CardCodeRpcServiceImpl implements CardCodeRpcService {


    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private BaseInfoModelMapper baseInfoModel;

    @Autowired
    private MemberMsgModelMapper memberMsgModelMapper;
    /**
     * 导入code
     * @param cardCodeImportReqData
     * @return
     */
    @Override
    public CardCodeImportResData importCode(CardCodeImportReqData cardCodeImportReqData) {
        Long cardKey = cardCodeImportReqData.getCardKey();
        if (cardKey == null) {
            return new CardUpdateRpcService.CardUpdateResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空！");
        }
        BaseInfoModel baseInfoModel = this.baseInfoModel.selectByPrimaryKey(cardKey);
        if (null == baseInfoModel) {
            return new CardUpdateRpcService.CardUpdateResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
        }
        String cardID = baseInfoModel.getCardID();
        String mpID = baseInfoModel.getMpID();
        List<String> code = cardCodeImportReqData.getCode();
        Map<String, Object> params = new HashMap<>();
        params.put("card_id", cardID);
        params.put("code", code);
        JSONObject jsonObject = baseHttpService.importCardCode(params, mpID);

        return ResultUtil.getResultInfoBean(jsonObject, CardCodeImportResData.class);
    }

    /**
     * 核销卡券
     * @param cardCodeDestroyReqData
     * @return
     */
    @Override
    public CardCodeDestroyResData destoryCode(CardCodeDestroyReqData cardCodeDestroyReqData) {
        Long cardKey = cardCodeDestroyReqData.getCardKey();
        if (cardKey == null) {
            return new CardCodeDestroyResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空！");
        }
        BaseInfoModel baseInfoModel = this.baseInfoModel.selectByPrimaryKey(cardKey);
        if (null == baseInfoModel) {
            return new CardCodeDestroyResData().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
        }
        String cardID = baseInfoModel.getCardID();
        String mpID = baseInfoModel.getMpID();
        String code = cardCodeDestroyReqData.getCode();

        String params = "{" +
                "  \"code\": \"" + code + "\"," +
                "  \"card_id\": \"" + cardID + "\"" +
                "}";
        JSONObject jsonObject = baseHttpService.destoryCardCode(params, mpID);

        return ResultUtil.getResultInfoBean(jsonObject, CardCodeDestroyResData.class);
    }

    /**
     * code解码
     * @param cardCodeDecodingReqData
     * @return
     */
    @Override
    public CardCodeDecodingResData decodingCardCode(CardCodeDecodingReqData cardCodeDecodingReqData) {
        String encryptCode = cardCodeDecodingReqData.getEncryptCode();
        String mpID = cardCodeDecodingReqData.getMpID();
        if (StringUtils.isBlank(encryptCode)) {
            return new CardCodeDecodingResData().setResultInfo(ErrorCodes.WECHAT_CARD_ENCRYPT_CODE_NULL, "encryptCode不能为空！");
        }
        if (StringUtils.isBlank(mpID)) {
            return new CardCodeDecodingResData().setResultInfo(ErrorCodes.WECHAT_MPID_EMPTY, "mpID不能为空！");
        }
        String params = "{" +
                "  \"encrypt_code\":\"" + encryptCode + "\"" +
                "}";

        JSONObject jsonObject = baseHttpService.decodingCardCode(params, mpID);

        return ResultUtil.getResultInfoBean(jsonObject, CardCodeDecodingResData.class);
    }


    /**
     * 查询优惠券code状态
     */
    @Override
    public CardCodeQueryResData queryCouponStatus(CardCodeQueryReqData cardCodeQueryReqData) {
        String cardCode = cardCodeQueryReqData.getCardCode();
        Long cardKey = cardCodeQueryReqData.getCardKey();
        if (cardKey == null) {
            return new CardCodeQueryResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空！");
        }
        if (StringUtils.isBlank(cardCode)){
            return new CardCodeQueryResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_CODE_NULL, "cardCode不允许为空！");
        }
        BaseInfoModel baseInfoModel = this.baseInfoModel.selectByPrimaryKey(cardKey);
        if (null == baseInfoModel) {
            return new CardCodeQueryResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
        }

        String params = "{" +
                        "   \"card_id\" : \""+baseInfoModel.getCardID()+"+\"," +
                        "   \"code\" : \""+cardCode+"\"," +
                        "   \"check_consume\" : true" +
                        "}";
        JSONObject jsonObject = baseHttpService.getCodeStatus(params, baseInfoModel.getMpID());
//        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
//            BaseInfoModel baseInfoModel1 = new BaseInfoModel();
//                baseInfoModel1.setCardKey(cardKey);
//                String status = jsonObject.getString("user_card_status");
//                CardStatusEnum cardStatusEnum = CardStatusEnum.valueOf(status);
//                baseInfoModel1.setCardStatus(cardStatusEnum.getValue());
//                this.baseInfoModel.updateByPrimaryKeySelective(baseInfoModel1);
//        }
        CardCodeQueryResData resultInfoBean = ResultUtil.getResultInfoBean(jsonObject, CardCodeQueryResData.class);
        resultInfoBean.setCardKey(cardKey);
        resultInfoBean.setBeginTime(jsonObject.getLong("begin_time"));
        resultInfoBean.setEndTime(jsonObject.getLong("end_time"));
        return resultInfoBean;
    }

    /**
     * 更新会员实例信息
     * @param memberItemUpdateReq
     * @return
     */
    @Override
    public MemberItemUpdateRes updateMemberItem(MemberItemUpdateReq memberItemUpdateReq) {
        String cardCode = memberItemUpdateReq.getCardCode();
        Long cardKey = memberItemUpdateReq.getCardKey();
        Long msgCreateTime = memberItemUpdateReq.getMsgCreateTime();
        if (msgCreateTime == null){
            return new MemberItemUpdateRes().setResultInfo(ErrorCodes.WECHAT_CARD_MSGCREATETIME_EMPTY, "msgCreateTime不允许为空！");
        }
        if (cardKey == null) {
            return new MemberItemUpdateRes().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空！");
        }
        if (StringUtils.isBlank(cardCode)){
            return new MemberItemUpdateRes().setResultInfo(ErrorCodes.WECHAT_CARD_CODE_NULL, "cardCode不允许为空！");
        }
        BaseInfoModel baseInfoModel = this.baseInfoModel.selectByPrimaryKey(cardKey);
        if (null == baseInfoModel) {
            return new MemberItemUpdateRes().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
        }
        //校验消息时间，如果消息创建时间小于上一次消息的创建时间，则不执行该条语句。
        msgLock(cardCode, msgCreateTime, baseInfoModel);


        String addBonus = memberItemUpdateReq.getAddBonus();
        String bonus = memberItemUpdateReq.getBonus();
        String addBalance = memberItemUpdateReq.getAddBalance();
        String balance = memberItemUpdateReq.getBalance();
        String backgroundPicUrl = memberItemUpdateReq.getBackgroundPicUrl();
        String customFieldValue1 = memberItemUpdateReq.getCustomFieldValue1();
        String customFieldValue2 = memberItemUpdateReq.getCustomFieldValue2();
        String customFieldValue3 = memberItemUpdateReq.getCustomFieldValue3();
        String recordBalance = memberItemUpdateReq.getRecordBalance();
        String recordBonus = memberItemUpdateReq.getRecordBonus();
        String isNotifyBalance = memberItemUpdateReq.getIsNotifyBalance();
        String isNotifyBonus = memberItemUpdateReq.getIsNotifyBonus();
        boolean notifyCustomField1 = memberItemUpdateReq.isNotifyCustomField1();
        boolean notifyCustomField2 = memberItemUpdateReq.isNotifyCustomField2();
        boolean notifyCustomField3 = memberItemUpdateReq.isNotifyCustomField3();

        String json = "{" +
                "    \"code\": \""+cardCode+"\"," +
                "    \"card_id\": \""+baseInfoModel.getCardID()+"\", ";

        StringBuilder sb = new StringBuilder(json);
        if (StringUtils.isNotBlank(bonus))
            sb.append("\"bonus\": "+bonus+",");
        if (StringUtils.isNotBlank(addBonus))
            sb.append("\"add_bonus\": "+addBonus+",");
        if (StringUtils.isNotBlank(balance)) {
            BigDecimal bigDecimal = new BigDecimal(balance);
            BigDecimal bigDecimal1 = new BigDecimal("100");
            BigDecimal decimal = bigDecimal.multiply(bigDecimal1);
            sb.append("\"balance\": "+decimal.longValue()+",");
        }
        if (StringUtils.isNotBlank(addBalance)) {
            BigDecimal bigDecimal = new BigDecimal(addBalance);
            BigDecimal bigDecimal1 = new BigDecimal("100");
            BigDecimal decimal = bigDecimal.multiply(bigDecimal1);
            sb.append("\"add_balance\": " + decimal.longValue() + ",");
        }
        if (StringUtils.isNotBlank(backgroundPicUrl))
            sb.append("\"background_pic_url\": \""+backgroundPicUrl+"\",");
        if (StringUtils.isNotBlank(recordBonus))
            sb.append("\"record_bonus\": \""+recordBonus+"\",");
        if (StringUtils.isNotBlank(recordBalance))
            sb.append("\"record_balance\": \""+recordBalance+"\",");
        if (StringUtils.isNotBlank(customFieldValue1))
            sb.append("\"custom_field_value1\": \""+customFieldValue1+"\"，");
        if (StringUtils.isNotBlank(customFieldValue2))
            sb.append("\"custom_field_value2\": \""+customFieldValue2+"\"，");
        if (StringUtils.isNotBlank(customFieldValue3))
            sb.append("\"custom_field_value3\": \""+customFieldValue3+"\"，");
        sb.append("\"notify_optional\": {");
        if (StringUtils.isNotBlank(isNotifyBonus) && "false".equals(isNotifyBonus))
            sb.append("  \"is_notify_bonus\": false,");
        else
            sb.append("  \"is_notify_bonus\": true,");
        if (StringUtils.isNotBlank(isNotifyBalance) && "false".equals(isNotifyBalance))
            sb.append("  \"is_notify_balance\": false,");
        else
            sb.append("  \"is_notify_balance\": true,");
        sb.append("  \"is_notify_custom_field1\":"+notifyCustomField1+",");
        sb.append("  \"is_notify_custom_field2\":"+notifyCustomField2+",");
        sb.append("  \"is_notify_custom_field3\":"+notifyCustomField3+",");
        sb.replace(sb.length()-1,sb.length(),"    } }");
        JSONObject jsonObject = baseHttpService.updateMemberInfo(sb.toString(), baseInfoModel.getMpID());
        return ResultUtil.getResultInfoBean(jsonObject,MemberItemUpdateRes.class);
    }

    /**
     * 校验消息时间，如果消息创建时间小于上一次消息的创建时间，则抛出ServiceException不执行该条语句。
     * @param cardCode
     * @param msgCreateTime
     * @param baseInfoModel
     */
    private void msgLock(String cardCode, Long msgCreateTime, BaseInfoModel baseInfoModel) {
        MemberMsgModelQuery memberMsgModelQuery = new MemberMsgModelQuery();
        memberMsgModelQuery.createCriteria()
                .andCardIDEqualTo(baseInfoModel.getCardID())
                .andCodeEqualTo(cardCode);
        int count = memberMsgModelMapper.countByExample(memberMsgModelQuery);
        int rows = 0;
        if (count == 0){
            MemberMsgModel memberMsgModel = new MemberMsgModel();
            memberMsgModel.setCardID(baseInfoModel.getCardID());
            memberMsgModel.setCode(cardCode);
            memberMsgModel.setMsgCreateTime(msgCreateTime);
            rows = memberMsgModelMapper.insertSelective(memberMsgModel);
        }else {
            MemberMsgModel memberMsgModel = new MemberMsgModel();
            memberMsgModel.setCardID(baseInfoModel.getCardID());
            memberMsgModel.setCode(cardCode);
            memberMsgModel.setMsgCreateTime(msgCreateTime);
            MemberMsgModelQuery updateQuery = new MemberMsgModelQuery();
            memberMsgModelQuery.createCriteria()
                    .andCardIDEqualTo(baseInfoModel.getCardID())
                    .andCodeEqualTo(cardCode)
                    .andMsgCreateTimeLessThan(msgCreateTime);
            rows = memberMsgModelMapper.updateByCondition(memberMsgModel);
        }
        if (rows != 1){
            throw new WechatException(WechatExceptionTypeEnum.WECHAT_CARD_LOCK_ERROR);
        }
    }
}
