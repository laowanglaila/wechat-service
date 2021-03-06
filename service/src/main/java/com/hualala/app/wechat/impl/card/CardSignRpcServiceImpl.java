package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardSignRpcService;
import com.hualala.app.wechat.common.ErrorCodes;
import com.hualala.app.wechat.mapper.WechatMpMapper;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapperEXT;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.model.card.BaseInfoModelQuery;
import com.hualala.app.wechat.common.WechatMessageType;
import com.hualala.app.wechat.service.ApiTicketService;
import com.hualala.app.wechat.util.ResultUtil;
import com.hualala.app.wechat.util.WxCardSign;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by renjianfei on 2017/6/27.
 */
@Service
public class CardSignRpcServiceImpl implements CardSignRpcService{
    private static Logger logger = LoggerFactory.getLogger(CardSignRpcService.class);
    @Autowired
    private WechatMpMapper wechatMpMapper;
    @Autowired
    private BaseInfoModelMapperEXT baseInfoModelMapperEXT;
    @Autowired
    private ApiTicketService apiTicketService;
    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;
    @Override
    public CardSignResData getSign(CardSignReqData cardSignReqData) {
        //判断mpID,没有则调方法获取
        String mpID = cardSignReqData.getMpID();
        //groupID
        Long groupID = cardSignReqData.getGroupID();
        String hualalaCardCode = cardSignReqData.getHualalaCardCode();
        String customerID = cardSignReqData.getCustomerID();
        if (StringUtils.isBlank(hualalaCardCode)){
            return new CardSignResData().setResultInfo( ErrorCodes.WECHAT_ILLEGAL_ARGUMENTS, "非法参数:hualalaCardCode[null]！");
        }
        Long hualalaCardID = cardSignReqData.getHualalaCardID();
        if (hualalaCardID == null){
            return new CardSignResData().setResultInfo(ErrorCodes.WECHAT_ILLEGAL_ARGUMENTS, "非法参数:hualalaCardID不能为空！");
        }
        if (StringUtils.isBlank(mpID)) {
            if (groupID == null || hualalaCardID == null){
                if (logger.isErrorEnabled())
                    logger.error("非法参数:groupID["+groupID+"],hualalaCardID["+hualalaCardID+"]！");
                return new CardSignResData().setResultInfo(ErrorCodes.WECHAT_ILLEGAL_ARGUMENTS, "非法参数:groupID["+groupID+"],mpID["+mpID+"]！");
            }
            BaseInfoModelQuery baseInfoModelQuery = new BaseInfoModelQuery();
            baseInfoModelQuery.createCriteria().andHualalaCardIDEqualTo(hualalaCardID).andGroupIDEqualTo(groupID);
            List<BaseInfoModel> baseInfoModels = baseInfoModelMapper.selectByExample(baseInfoModelQuery);
            if (baseInfoModels.size() > 0){
                BaseInfoModel baseInfoModel = baseInfoModels.get(0);
                mpID = baseInfoModel.getMpID();
            }
            if (StringUtils.isBlank(mpID)) {
                return new CardSignResData().setResultInfo(ErrorCodes.WECHAT_CARD_MISMATCH, "未绑定微信会员卡！");
            }
        }
        if (groupID == null) {
            Map<String, Object> params = new HashMap<>();
            params.put("mpID", mpID);
            List<Map<String, Object>> maps = wechatMpMapper.queryByParams(params);

            if (maps.size() > 0) {
                Integer groupID1 = (Integer) maps.get(0).get("groupID");
                groupID = groupID1.longValue();
            } else {
                return new CardSignResData().setResultInfo(ErrorCodes.WECHAT_GROUP_ID_NULL, "获取GroupID失败！");
            }
        }

        WxCardSign signer = new WxCardSign();
        // api_ticket、timestamp、card_id、code、openid、nonce_str
        JSONObject ticketObject = apiTicketService.getWxCardApiTicket(mpID);
        if (!ticketObject.getBoolean(WechatMessageType.IS_SUCCESS)) {
            return ResultUtil.getResultInfoBean(ticketObject,CardSignResData.class);
        }
        String apiTicket = ticketObject.getString("ticket");
        signer.AddData(apiTicket);
        String nonceStr = UUID.randomUUID().toString().replaceAll("-","");
        signer.AddData(nonceStr);
        String openid = cardSignReqData.getOpenid();
        if (StringUtils.isNotBlank(openid)){
            signer.AddData(openid);
        }
        String code = cardSignReqData.getCode();
        if (StringUtils.isNotBlank(code)){
            signer.AddData(code);
        }
        Long timeStamp = System.currentTimeMillis()/1000;
        signer.AddData(timeStamp.toString());
        Map<String,Object> map = new HashMap<>();
        map.put("hualalaCardID",hualalaCardID);
        map.put("mpID",mpID);
        map.put("groupID",groupID);
        List<String> cardIds = baseInfoModelMapperEXT.giveOutCardId(map);
        if (cardIds == null || cardIds.size() == 0){
            return new CardSignResData().setResultInfo(ErrorCodes.WECHAT_CARD_MISMATCH, "在此公众号下没有找到对应的微信会员卡！");
        }else if (cardIds.size() > 1){
            if (logger.isInfoEnabled()) {
                logger.error("在此公众号下找到了多张匹配的微信会员卡！", cardIds.toString());
            }
            return new CardSignResData().setResultInfo(ErrorCodes.WECHAT_CARD_MISMATCH, "在此公众号下找到了多张匹配的微信会员卡！");
        }
        signer.AddData(cardIds.get(0));
        String signature = signer.GetSignature();
        CardSignResData cardSignResData = new CardSignResData();
        cardSignResData.setCardID(cardIds.get(0));
        cardSignResData.setNonceStr(nonceStr);
        cardSignResData.setSignature(signature);
        cardSignResData.setTimeStamp(timeStamp.toString());
        StringBuilder outStr = new StringBuilder("{\"groupID\":\""+groupID+"\",\"hualalaCardID\":\""+hualalaCardID+"\",\"hualalaCardCode\":\""+hualalaCardCode+"\"");
        if (StringUtils.isNotBlank(customerID)){
            outStr.append(",\"customerID\":\""+customerID+"\"");
        }
        outStr.append("}");
        String s = outStr.toString();
        if (logger.isDebugEnabled()){
            logger.debug("outStr: " + s);
        }
        String encodeOuterStr = Base64.encodeBase64URLSafeString(s.getBytes());
        cardSignResData.setOuterStr(encodeOuterStr);
        return cardSignResData;
    }

    @Override
    public CardBachSignResData getSignList(CardBachSignReqData cardSignReqData) {
        String mpID = cardSignReqData.getMpID();
        Long groupID = cardSignReqData.getGroupID();
        String openid = cardSignReqData.getOpenid();
        String customerID = cardSignReqData.getCustomerID();
        CardSignReqData cardSignReqData1 = new CardSignReqData();
        List <CardSignResData> cardSignResDataList
                = cardSignReqData.getItems()
                                .stream()
                                .map( item -> {
                                    cardSignReqData1.setMpID( mpID );
                                    cardSignReqData1.setGroupID( groupID );
                                    cardSignReqData1.setOpenid( openid );
                                    cardSignReqData1.setCustomerID( customerID );
                                    cardSignReqData1.setHualalaCardID( item.getHualalaCardID() );
                                    cardSignReqData1.setHualalaCardCode( item.getHualalaCardCode() );
                                    cardSignReqData1.setCode( item.getCode() );
                                    CardSignResData sign = this.getSign( cardSignReqData1 );
                                    sign.setEffectiveTime( item.getEffectiveTime() );
                                    return sign;
                                } ).collect( Collectors.toList() );
        CardBachSignResData cardBachSignResData = new CardBachSignResData();
        cardBachSignResData.setSigns( cardSignResDataList );
        return cardBachSignResData;
    }
}
