package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardDeleteRpcService;
import com.hualala.app.wechat.sdk.mp.common.ErrorCodes;
import com.hualala.app.wechat.sdk.mp.common.WechatMessageType;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/5/2.
 */
@Service
public class CardDeleteRpcServiceImpl implements CardDeleteRpcService {

    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;

    @Override
    public CardDeleteAndUnAbailableResData deleteCard(CardDeleteReqData cardDeleteReqData) {
        Long cardKey = cardDeleteReqData.getCardKey();
        if (cardKey == null){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "CardKey不能为空！");
        }
        BaseInfoModel baseInfoModel1 = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        if (null == baseInfoModel1){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");

        }
        String cardID = baseInfoModel1.getCardID();
        String mpID = baseInfoModel1.getMpID();
        String params = "{\"card_id\": \"" + cardID + "\"}";
        JSONObject jsonObject = baseHttpService.deleteCard(params, mpID);

        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {

            BaseInfoModel baseInfoModel = new BaseInfoModel();
            baseInfoModel.setCardKey(cardKey);
            baseInfoModel.setCardStatus(0);
            baseInfoModel.setAction(3);
            baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
//            advancedModelMapper.deleteByPrimaryKey(cardKey);
//            baseInfoModelMapper.deleteByPrimaryKey(cardKey);
//            memberModelMapper.deleteByPrimaryKey(cardKey);
        }
        return ResultUtil.getResultInfoBean(jsonObject, CardDeleteAndUnAbailableResData.class);
    }

    @Override
    public CardDeleteAndUnAbailableResData unAvailableCard(CardUnAvailableReqData cardUnAvailableReqData) {
        Long cardKey = cardUnAvailableReqData.getCardKey();
        if (cardKey == null){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "CardKey不能为空！");
        }
        BaseInfoModel baseInfoModel1 = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        if (null == baseInfoModel1){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");

        }
        String cardID = baseInfoModel1.getCardID();
        String mpID = baseInfoModel1.getMpID();
        String code = cardUnAvailableReqData.getCode();
        String reason = cardUnAvailableReqData.getReason();
        String params = "{\"code\": \""+code+"\",\"card_id\": \""+cardID+"\",\"reason\":\""+reason+"\"}";
        JSONObject jsonObject = baseHttpService.setCardUnavailable(params, mpID);
//        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)){
//            //修改状态
//            BaseInfoModel baseInfoModel = new BaseInfoModel();
//            baseInfoModel.setCardKey(cardKey);
//            baseInfoModel.setCardStatus(6);
//            baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
//        }
        return ResultUtil.getResultInfoBean(jsonObject,CardDeleteAndUnAbailableResData.class);
    }
//
//    @Override
//    public CardDeleteAndUnAbailableResData deleteCouponInfo(CardDeleteReqData cardDeleteReqData) {
//        Long cardKey = cardDeleteReqData.getCardKey();
//        if (cardKey == null){
//            return new CardDeleteAndUnAbailableResData()
//                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "CardKey不能为空！");
//        }
//        BaseInfoModel baseInfoModel1 = baseInfoModelMapper.selectByPrimaryKey(cardKey);
//        if (null == baseInfoModel1){
//            return new CardDeleteAndUnAbailableResData()
//                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
//
//        }
//        String cardID = baseInfoModel1.getCardID();
//        String mpID = baseInfoModel1.getMpID();
//        String params = "{\"card_id\": \"" + cardID + "\"}";
//        JSONObject jsonObject = baseHttpService.deleteCard(params, mpID);
//
//        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
//
//            BaseInfoModel baseInfoModel = new BaseInfoModel();
//            baseInfoModel.setCardKey(cardKey);
//            baseInfoModel.setCardStatus(0);
//            baseInfoModel.setAction(3);
//            baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
//
//        }
//        return ResultUtil.getResultInfoBean(jsonObject, CardDeleteAndUnAbailableResData.class);
//    }
//
//    @Override
//    public CardDeleteAndUnAbailableResData unAvailableCouponInfo(CardUnAvailableReqData cardUnAvailableReqData) {
//        Long cardKey = cardUnAvailableReqData.getCardKey();
//        if (cardKey == null){
//            return new CardDeleteAndUnAbailableResData()
//                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "CardKey不能为空！");
//        }
//        BaseInfoModel baseInfoModel1 = baseInfoModelMapper.selectByPrimaryKey(cardKey);
//        if (null == baseInfoModel1){
//            return new CardDeleteAndUnAbailableResData()
//                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");
//
//        }
//        String cardID = baseInfoModel1.getCardID();
//        String mpID = baseInfoModel1.getMpID();
//        String code = cardUnAvailableReqData.getCode();
//        String params = "{\"code\": \""+code+"\",\"card_id\": \""+cardID+"\"}";
//        JSONObject jsonObject = baseHttpService.deleteCard(params, mpID);
//        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)){
//            //修改状态
//            BaseInfoModel baseInfoModel = new BaseInfoModel();
//            baseInfoModel.setCardKey(cardKey);
//            baseInfoModel.setCardStatus(6);
//            baseInfoModelMapper.updateByPrimaryKeySelective(baseInfoModel);
//        }
//        return ResultUtil.getResultInfoBean(jsonObject,CardDeleteAndUnAbailableResData.class);
//    }
}
