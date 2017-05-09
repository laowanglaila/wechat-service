package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.CardEventRpcService;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.mapper.card.MemberModelMapper;
import com.hualala.app.wechat.model.card.MemberModel;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by renjianfei on 2017/5/5.
 */
public class CardEventRpcServiceImpl implements CardEventRpcService {

    @Autowired
    private BaseHttpService baseHttpService;
    @Autowired
    private MemberModelMapper memberModelMapper;
    /**
     * 激活会员卡接口
     * @param activateMemberCardReqData
     * @return
     */
    @Override
    public ActivateMemberCardResData activateMemberCard(ActivateMemberCardReqData activateMemberCardReqData) {

        Long cardKey = activateMemberCardReqData.getCardKey();
        if (null == cardKey){
            return new ActivateMemberCardResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL, "cardKey不能为空！");
        }
        MemberModel memberModel = memberModelMapper.selectByPrimaryKey(cardKey);
        if (null == memberModel){
            return new ActivateMemberCardResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NONE, "不存在指定的cardKey！");
        }
        String cardID = memberModel.getCardID();
        String mpID = memberModel.getMpID();
        String membershipNumber = activateMemberCardReqData.getMembershipNumber();
        if (StringUtils.isBlank(membershipNumber)){
            return new ActivateMemberCardResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_MEMBER_NUM_NULL, "会员卡号不能为空！");
        }
        String code = activateMemberCardReqData.getCode();
        if (StringUtils.isBlank(code)){
            return new ActivateMemberCardResData()
                    .setResultInfo(ErrorCodes.WECHAT_CARD_COLOR_NULL, "会员卡code码不能为空！");
        }

        StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\": \""+code+"\",").append("\"card_id\": \""+cardID+"\",").append("\"membership_number\": \""+membershipNumber+"\",");

        Integer activateBeginTime = activateMemberCardReqData.getActivateBeginTime();
        if (activateBeginTime != null){
            sb.append("\"activate_begin_time\": "+activateBeginTime+",");
        }
        Integer activateEndTime = activateMemberCardReqData.getActivateEndTime();
        if (activateEndTime != null){
            sb.append("\"activateEndTime\": "+activateEndTime+",");
        }
        String backgroundPicUrl = activateMemberCardReqData.getBackgroundPicUrl();
        if (StringUtils.isNotBlank(backgroundPicUrl)){
            sb.append("\"background_pic_url\": \""+backgroundPicUrl+"\",");
        }
        Integer initBalance = activateMemberCardReqData.getInitBalance();
        if (initBalance != null){
            sb.append("\"initBalance\": "+initBalance+",");
        }
        Integer initBonus = activateMemberCardReqData.getInitBonus();
        if (initBonus != null){
            sb.append("\"initBonus\": "+initBonus+",");
        }
        String initBonusRecord = activateMemberCardReqData.getInitBonusRecord();
        if (StringUtils.isNotBlank(initBonusRecord)){
            sb.append("\"initBonusRecord\": \""+initBonusRecord+"\",");
        }
        String initCustomFieldValue1 = activateMemberCardReqData.getInitCustomFieldValue1();
        if (StringUtils.isNotBlank(initCustomFieldValue1)){
            sb.append("\"initCustomFieldValue1\": \""+initCustomFieldValue1+"\",");
        }
        String initCustomFieldValue2 = activateMemberCardReqData.getInitCustomFieldValue2();
        if (StringUtils.isNotBlank(initCustomFieldValue2)){
            sb.append("\"initCustomFieldValue2\": \""+initCustomFieldValue2+"\",");
        }
        String initCustomFieldValue3 = activateMemberCardReqData.getInitCustomFieldValue3();
        if (StringUtils.isNotBlank(initCustomFieldValue3)){
            sb.append("\"initCustomFieldValue3\": \""+initCustomFieldValue3+"\",");
        }
        int length = sb.length();
        sb.replace(length-1,length,"}");

        JSONObject jsonObject = baseHttpService.activate(sb.toString(), mpID);

        return ResultUtil.getResultInfoBean(jsonObject,ActivateMemberCardResData.class);
    }
}
