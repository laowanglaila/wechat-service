package com.hualala.app.wechat.impl.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.BaseRpcTest;
import com.hualala.app.wechat.service.BaseHttpService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by renjianfei on 2017/7/21.
 */
public class CardGiveOutRpcServiceImplTest extends BaseRpcTest {

    @Autowired
    private BaseHttpService baseHttpService;

    @Override
    public void test() {
        String url = "https://api.weixin.qq.com/card/paygiftcard/add";
        String mpID = "tut1Ceu1DX005996";
        Long cardKey = 6444812810940589187L;
        String cardID = "pXpuzwZdfqUSuLQJrZG_XvDXapPA";
        String mchID = "24251984";
        long begin = System.currentTimeMillis() / 1000;
        long end = begin + 60*60;
        String json = "{" +
                "    \"rule_info\": {" +
                "        \"type\": \"RULE_TYPE_PAY_MEMBER_CARD\"," +
                "        \"base_info\": {" +
                "            \"mchid_list\": [                " +
                "            \""+mchID+"\"" +
                "            ]," +
                "            \"begin_time\": "+ begin +"," +
                "            \"end_time\": "+end +
                "        }," +
                "        \"member_rule\": {" +
                "            \"card_id\": \""+cardID+"\"," +
                "            \"least_cost\": 2," +
                "            \"max_cost\": 20000," +
                "            \"jump_url\": \"www.hualala.com\"" +
                "        }    " +
                "     }" +
                "  }";

        System.out.println(json);
        JSONObject jsonObject = baseHttpService.commonHttpPost(url, json, mpID);
        System.out.println(jsonObject);
    }
}