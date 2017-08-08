package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.service.BaseHttpService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by renjianfei on 2017/7/21.
 */
public class CardGiveOutRpcServiceImplTest extends BaseRpcTest {

    @Autowired
    private BaseHttpService baseHttpService;

    @Override
    public void test() {
        String url = "https://hushangayi.jikabao.com";
//        String url = "https://hushangayi.jikabao.com/wechat/get_coupon/5AlV2Y";
//        String mpID = "hualala_com";
        String mpID = "tut1Ceu1DX005996";
//        String cardID = "pXpuzwZdfqUSuLQJrZG_XvDXapPA";
//        String cardID = "pXpuzwdJJPIE9peUazT_JntW7spo";
        String cardID = "pXpuzwWVp7_3AgRI4rhm8SynQw3c";
//        String cardID = "pACwGs3Qk-mhtzuhDnC2mPno2208";
        String mchID = "24251984";
        String mchID1 = "25192989";
        long begin = System.currentTimeMillis() / 1000;
        long end = begin + 60*60;
        String json = "{" +
                "    \"rule_info\": {" +
                "        \"type\": \"RULE_TYPE_PAY_MEMBER_CARD\"," +
                "        \"base_info\": {" +
                "            \"mchid_list\": [                " +
                "            \""+mchID+"\"," +
                "            \""+mchID1+"\"" +
                "            ]," +
                "            \"begin_time\": "+ begin +"," +
                "            \"end_time\": "+end +
                "        }," +
                "        \"member_rule\": {" +
                "            \"card_id\": \""+cardID+"\"," +
                "            \"least_cost\": 1000," +
                "            \"max_cost\": 200000," +
                "            \"jump_url\": \""+url+"\"" +
                "        }    " +
                "     }" +
                "  }";

        System.out.println(json);
        JSONObject jsonObject = baseHttpService.commonHttpPost(url, json, mpID);
        System.out.println(jsonObject);
    }

    /**
     * 根据ruleID查询支付赠卡规则
     */
    @Test
    public void test1() {
        String url = "https://api.weixin.qq.com/card/paygiftcard/getbyid";
//        String mpID = "tut1Ceu1DX005996";
        String mpID = "tut1Ceu1DX005996";
        String ruleID = "428667898";
        String json = "{ \"rule_id\": "+ruleID+"}";
        JSONObject jsonObject = baseHttpService.commonHttpPost(url, json, mpID);
        System.out.println(jsonObject);
    }
    /**
     * 根据ruleID删除支付赠卡规则
     */
    @Test
    public void test2() {
        String url = "https://api.weixin.qq.com/card/paygiftcard/delete";
        String mpID = "tut1Ceu1DX005996";
        String ruleID = "428667898";
        String json = "{ \"rule_id\": "+ruleID+"}";
        JSONObject jsonObject = baseHttpService.commonHttpPost(url, json, mpID);
        System.out.println(jsonObject);
    }

}