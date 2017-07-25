package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.StringUtil;
import com.hualala.app.wechat.CardGiveOutRpcService;
import com.hualala.app.wechat.ErrorCodes;
import com.hualala.app.wechat.mapper.card.BaseInfoModelMapper;
import com.hualala.app.wechat.model.card.BaseInfoModel;
import com.hualala.app.wechat.service.BaseHttpService;
import com.hualala.app.wechat.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.List;

/**
 * Created by renjianfei on 2017/7/12.
 */
@Service
public class CardGiveOutRpcServiceImpl implements CardGiveOutRpcService {

    private static Logger logger = LoggerFactory.getLogger(CardGiveOutRpcService.class);

    @Autowired
    private BaseInfoModelMapper baseInfoModelMapper;

    @Autowired
    private BaseHttpService baseHttpService;
    /**
     * 支付即会员接口
     * @param payToMemberReq
     * @return
     */
    @Override
    public PayToMemberRes payToMember(PayToMemberReq payToMemberReq) {
        Long cardKey = payToMemberReq.getCardKey();
        Integer beginTime = payToMemberReq.getBeginTime();
        Integer endTime = payToMemberReq.getEndTime();
        String leastCost = payToMemberReq.getLeastCost();
        String maxCost = payToMemberReq.getMaxCost();
        String jumpUrl = payToMemberReq.getJumpUrl();
        if (cardKey == null || cardKey == 0) {
            return new PayToMemberRes().setResultInfo(ErrorCodes.WECHAT_CARD_KEY_NULL,"cardKey不能为空!");
        }
        long currentTime = System.currentTimeMillis() / 1000;
        if (beginTime == null || endTime == null || beginTime < (currentTime-60*60*24) || endTime < currentTime) {
            return new PayToMemberRes().setResultInfo(ErrorCodes.WECHAT_TIME_RANGE_WRONG,"时间范围有误!");
        }
        if (StringUtils.isBlank(leastCost) || StringUtils.isBlank(maxCost)){
            int i = Integer.parseInt(leastCost);
        }
        Integer leastCostInt = null;
        Integer maxCostInt = null;
        try {
        leastCostInt = Integer.valueOf(leastCost);
        maxCostInt = Integer.valueOf(maxCost);
        }catch (NumberFormatException e){
            e.printStackTrace();
            if (logger.isErrorEnabled()){
                logger.error("时间范围有误!\nleastCost:["+leastCost+"]\nmaxCost:["+maxCost+"]",e);
            }
            return new PayToMemberRes().setResultInfo(ErrorCodes.WECHAT_AMOUNT_RANGE_WRONG,"时间范围有误!\nleastCost:["+leastCost+"]\nmaxCost:["+maxCost+"]");
        }


        BaseInfoModel baseInfoModel = baseInfoModelMapper.selectByPrimaryKey(cardKey);
        String cardID = baseInfoModel.getCardID();
        String mpID = baseInfoModel.getMpID();
        if (StringUtils.isBlank(cardID)){
            return new PayToMemberRes().setResultInfo(ErrorCodes.WECHAT_CARD_ID_MISSED,"没有对应的微信会员卡！");
        }
        if (StringUtils.isBlank(mpID)){
            return new PayToMemberRes().setResultInfo(ErrorCodes.WECHAT_MP_NULL,"mpID没有找到！");
        }
        //TODO  通过mpID获取mchID
        List<String> mchidList = null;
        String jsonString = "{" +
                "       \"rule_info\": {" +
                "        \"type\": \"RULE_TYPE_PAY_MEMBER_CARD\"," +
                "        \"base_info\": {" +
                "            \"mchid_list\": "+ JSONArray.toJSONString(mchidList)+"," +
                "            \"begin_time\": "+beginTime+"," +
                "            \"end_time\": "+endTime+"" +
                "        }," +
                "        \"member_rule\": {" +
                "            \"card_id\": \""+cardID+"\"," +
                "            \"least_cost\": "+leastCostInt+"," +
                "            \"max_cost\": "+maxCost;

        StringBuilder stringBuilder = new StringBuilder(jsonString);
        if (StringUtils.isNotBlank(jumpUrl)){
            stringBuilder.append(",\"jump_url\": \""+jumpUrl+"\"");
        }
        stringBuilder.append("}}}");
        JSONObject jsonObject = baseHttpService.payGiftCard(stringBuilder.toString(), mpID);
        String result = "  {\n" +
                        "    \"errcode\": 0,\n" +
                        "    \"errmsg\": \"ok\",\n" +
                        "    \"rule_id\": 1231243,\n" +
                        "    \"fail_mchid_list\": [\n" +
                        "        {\n" +
                        "            \"mchid\": \"111\",\n" +
                        "            \"errcode\": 23112,\n" +
                        "            \"errmsg\": \"err\",\n" +
                        "            \"occupy_rule_id\": 12332123,\n" +
                        "            \"occupy_appid\": \"appid\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "    \"succ_mchid_list\": [\n" +
                        "        \"123\",\n" +
                        "        \"456\"\n" +
                        "    ]\n" +
                        "}";


        return null;
    }

    @Override
    public PayToGiveCouponsRes payToGiveCoupon(PayToGiveCouponsReq payToGiveCouponsReq) {
        return null;
    }
}
