package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardCodeRpcService;
import com.hualala.app.wechat.CardUpdateRpcService;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 导入会员卡code
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

//    /**
//     * 导入优惠券code
//     * @param cardCodeImportReqData
//     * @return
//     */
//    @Override
//    public CardCodeImportResData importCouponCode(CardCodeImportReqData cardCodeImportReqData) {
//        Long cardKey = cardCodeImportReqData.getCardKey();
//        if (cardKey == null) {
//            return new CardUpdateRpcService.CardUpdateResData()
//                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空！");
//        }
//        BaseInfoModel baseInfoModel = this.baseInfoModel.selectByPrimaryKey(cardKey);
//        if (null == baseInfoModel) {
//            return new CardUpdateRpcService.CardUpdateResData()
//                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
//        }
//        String cardID = baseInfoModel.getCardID();
//        String mpID = baseInfoModel.getMpID();
//        List<String> code = cardCodeImportReqData.getCode();
//        Map<String, Object> params = new HashMap<>();
//        params.put("card_id", cardID);
//        params.put("code", code);
//        JSONObject jsonObject = baseHttpService.importCardCode(params, mpID);
//
//        return ResultUtil.getResultInfoBean(jsonObject, CardCodeImportResData.class);
//    }

    /**
     * 核销会员卡
     * @param cardCodeDestroyReqData
     * @return
     */
    @Override
    public CardCodeDestroyResData destoryCode(CardCodeDestroyReqData cardCodeDestroyReqData) {
        Long cardKey = cardCodeDestroyReqData.getCardKey();
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
        String code = cardCodeDestroyReqData.getCode();

        String params = "{" +
                "  \"code\": \"" + code + "\"," +
                "  \"card_id\": \"" + cardID + "\"" +
                "}";
        JSONObject jsonObject = baseHttpService.destoryCardCode(params, mpID);

        return ResultUtil.getResultInfoBean(jsonObject, CardCodeDestroyResData.class);
    }

//    /**
//     * 核销优惠券
//     * @param cardCodeDestroyReqData
//     * @return
//     */
//    @Override
//    public CardCodeDestroyResData destoryCouponCode(CardCodeDestroyReqData cardCodeDestroyReqData) {
//        Long cardKey = cardCodeDestroyReqData.getCardKey();
//        if (cardKey == null) {
//            return new CardUpdateRpcService.CardUpdateResData()
//                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不允许为空！");
//        }
//        BaseInfoModel baseInfoModel = this.baseInfoModel.selectByPrimaryKey(cardKey);
//        if (null == baseInfoModel) {
//            return new CardUpdateRpcService.CardUpdateResData()
//                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
//        }
//        String cardID = baseInfoModel.getCardID();
//        String mpID = baseInfoModel.getMpID();
//        String code = cardCodeDestroyReqData.getCode();
//
//        String params = "{" +
//                "  \"code\": \"" + code + "\"," +
//                "  \"card_id\": \"" + cardID + "\"" +
//                "}";
//        JSONObject jsonObject = baseHttpService.destoryCardCode(params, mpID);
//
//        return ResultUtil.getResultInfoBean(jsonObject, CardCodeDestroyResData.class);
//    }

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
}
