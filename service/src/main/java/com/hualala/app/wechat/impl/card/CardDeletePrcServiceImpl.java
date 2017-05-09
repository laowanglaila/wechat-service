package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardDeleteRpcService;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.mapper.card.AdvancedModelMapper;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.card.CouponModelMapper;
import com.hualala.app.wechat.mapper.card.MemberModelMapper;
import com.hualala.app.wechat.model.card.CouponModel;
import com.hualala.app.wechat.model.card.MemberModel;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renjianfei on 2017/5/2.
 */
@Service
public class CardDeletePrcServiceImpl implements CardDeleteRpcService {

    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private MemberModelMapper memberModelMapper;
    @Autowired
    private CouponModelMapper couponModelMapper;
    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;
    @Autowired
    private AdvancedModelMapper advancedModelMapper;

    @Override
    public CardDeleteAndUnAbailableResData deleteMemberInfo(CardDeleteReqData cardDeleteReqData) {
        Long cardKey = cardDeleteReqData.getCardKey();
        if (cardKey == null){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "CardKey不能为空！");
        }
        MemberModel memberModel = memberModelMapper.selectByPrimaryKey(cardKey);
        if (null == memberModel){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");

        }
        String cardID = memberModel.getCardID();
        String mpID = memberModel.getMpID();
        String params = "{\"card_id\": \"" + cardID + "\"}";
        JSONObject jsonObject = baseHttpService.deleteCard(params, mpID);

        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            advancedModelMapper.deleteByPrimaryKey(cardKey);
            baseInfoModelMapper.deleteByPrimaryKey(cardKey);
            memberModelMapper.deleteByPrimaryKey(cardKey);
        }
        return ResultUtil.getResultInfoBean(jsonObject, CardDeleteAndUnAbailableResData.class);
    }

    @Override
    public CardDeleteAndUnAbailableResData unAvailableMemberInfo(CardUnAvailableReqData cardUnAvailableReqData) {
        Long cardKey = cardUnAvailableReqData.getCardKey();
        if (cardKey == null){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "CardKey不能为空！");
        }
        MemberModel memberModel = memberModelMapper.selectByPrimaryKey(cardKey);
        if (null == memberModel){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");

        }
        String cardID = memberModel.getCardID();
        String mpID = memberModel.getMpID();
        String code = cardUnAvailableReqData.getCode();
        String params = "{\"code\": \""+code+"\",\"card_id\": \""+cardID+"\"}";
        JSONObject jsonObject = baseHttpService.deleteCard(params, mpID);
        return ResultUtil.getResultInfoBean(jsonObject,CardDeleteAndUnAbailableResData.class);
    }

    @Override
    public CardDeleteAndUnAbailableResData deleteCouponInfo(CardDeleteReqData cardDeleteReqData) {
        Long cardKey = cardDeleteReqData.getCardKey();
        if (cardKey == null){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "CardKey不能为空！");
        }
        CouponModel couponModel = couponModelMapper.selectByPrimaryKey(cardKey);
        if (null == couponModel){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");

        }
        String cardID = couponModel.getCardID();
        String mpID = couponModel.getMpID();
        String params = "{\"card_id\": \"" + cardID + "\"}";
        JSONObject jsonObject = baseHttpService.deleteCard(params, mpID);

        if (jsonObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            advancedModelMapper.deleteByPrimaryKey(cardKey);
            baseInfoModelMapper.deleteByPrimaryKey(cardKey);
            couponModelMapper.deleteByPrimaryKey(cardKey);
        }
        return ResultUtil.getResultInfoBean(jsonObject, CardDeleteAndUnAbailableResData.class);
    }

    @Override
    public CardDeleteAndUnAbailableResData unAvailableCouponInfo(CardUnAvailableReqData cardUnAvailableReqData) {
        Long cardKey = cardUnAvailableReqData.getCardKey();
        if (cardKey == null){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "CardKey不能为空！");
        }
        CouponModel couponModel = couponModelMapper.selectByPrimaryKey(cardKey);
        if (null == couponModel){
            return new CardDeleteAndUnAbailableResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的Key！");

        }
        String cardID = couponModel.getCardID();
        String mpID = couponModel.getMpID();
        String code = cardUnAvailableReqData.getCode();
        String params = "{\"code\": \""+code+"\",\"card_id\": \""+cardID+"\"}";
        JSONObject jsonObject = baseHttpService.deleteCard(params, mpID);
        return ResultUtil.getResultInfoBean(jsonObject,CardDeleteAndUnAbailableResData.class);
    }
}
