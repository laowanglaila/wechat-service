package com.hualala.app.wechat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.mapper.WechatMemberInfoMapper;
import com.hualala.app.wechat.mapper.card.MemberModelItemMapper;
import com.hualala.app.wechat.model.WechatMemberInfoModel;
import com.hualala.app.wechat.model.card.MemberModelItem;
import com.hualala.app.wechat.service.FastMemberInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Created by renjianfei on 2017/3/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FastMenberInfoServiceTest {

    @Autowired
    private FastMemberInfoService memberInfoService;

    @Autowired
    private WechatMemberInfoMapper memberInfoMapper;

    @Test
    public void memberInfoTest(){
        long startTime = System.currentTimeMillis();    //获取开始时间
        memberInfoService.setAppId("wxca431740658cd706");
        memberInfoService.setAppSecret("12e0401d258b7e7423c84c0a8704c8e0");
//        String accessToken = "fN7UjjtD3ipQzC6yjkUrowK_O0pKQgGVu5-2rV2-iCFQjsMoSdEStpJ_qjoG2YHZaKNhdJSLAVEqHfpgYnl88VTL1QXEoQMlJe_YEGYrHB4FSSbAHATGG";
//        accessToken = memberInfoService.getAccessToken();
//        memberInfoService.setAccessToken(accessToken);
        memberInfoService.setStartLine(1);
        memberInfoService.setCacheNo(1000);
        memberInfoService.setThreadNO(50);

        memberInfoService.loadInfo("C:\\Users\\Administrator\\Desktop\\code_pQqgCs4wFvht1uuiJNgSktnB894E.txt");
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime)/60*1000 + "s");    //输出程序运行时间
        System.out.println("accessToken : [" + memberInfoService.getAccessToken() + "]");

    }

    @Autowired
    private MemberModelItemMapper memberModelItemMapper;
    @Test
    public void memberInfoFormat() {
        Integer totleCount = memberInfoMapper.queryCount();
        double count = totleCount.doubleValue();
        Double pageSize = 5000d;

        Double d = count / pageSize;

        double ceil = Math.ceil(d);

        for (int n = 0; n < ceil; n++) {
            HashMap<String, Object> params = new HashMap<>();
            params.put("offset", pageSize.intValue() * n);
            params.put("pageSize", pageSize.intValue());
            List<WechatMemberInfoModel> wechatMemberInfoModels = memberInfoMapper.queryList(params);

            for (WechatMemberInfoModel wechatMemberInfoModel : wechatMemberInfoModels) {
                MemberModelItem memberModelItem = new MemberModelItem();
                String wechatJson = wechatMemberInfoModel.getWechatJson();
                JSONObject jsonObject = JSONObject.parseObject(wechatJson);
                Integer errcode = jsonObject.getInteger("errcode");
                memberModelItem.setErrcode(errcode);
                String openid = jsonObject.getString("openid");
                memberModelItem.setOpenid(openid);
                String nickname = jsonObject.getString("nickname");
                memberModelItem.setNickname(nickname);
                Long membershipNumber = jsonObject.getLong("membership_number");
                memberModelItem.setMembershipNumber(membershipNumber);
                Boolean hasActive = jsonObject.getBoolean("has_active");
                memberModelItem.setHasActive(hasActive);
                Integer bonus = jsonObject.getInteger("bonus");
                memberModelItem.setBonus(bonus);
                BigDecimal balance = jsonObject.getBigDecimal("balance");
                memberModelItem.setBalance(balance);
                String sex = jsonObject.getString("sex");
                memberModelItem.setSex(sex);
                String userCardStatus = jsonObject.getString("user_card_status");
                memberModelItem.setUserCardStatus(userCardStatus);

                JSONObject user_info = jsonObject.getJSONObject("user_info");
                if (null == user_info) {
                    memberModelItemMapper.insertSelective(memberModelItem);
                    continue;
                }
                JSONArray common_field_list = user_info.getJSONArray("common_field_list");
                for (int i = 0; i < common_field_list.size(); i++) {
                    JSONObject jsonObject1 = common_field_list.getJSONObject(i);
                    String name = jsonObject1.getString("name");
                    CommonFieldType commonFieldType = CommonFieldType.valueOf(name);
                    switch (commonFieldType) {
                        case USER_FORM_INFO_FLAG_NAME:
                            String value = jsonObject1.getString("value");
                            memberModelItem.setName(value);
                            break;
                        case USER_FORM_INFO_FLAG_MOBILE:
                            Long value1 = jsonObject1.getLong("value");
                            memberModelItem.setPhone(value1);
                            break;
                        case USER_FORM_INFO_FLAG_BIRTHDAY:
                            String value2 = jsonObject1.getString("value");
                            memberModelItem.setBirthday(value2);
                    }

                }
                memberModelItemMapper.insertSelective(memberModelItem);
            }

        }
    }

    enum CommonFieldType{
        USER_FORM_INFO_FLAG_MOBILE(),
        USER_FORM_INFO_FLAG_NAME(),
        USER_FORM_INFO_FLAG_SEX(),
        USER_FORM_INFO_FLAG_BIRTHDAY(),
        USER_FORM_INFO_FLAG_IDCARD(),
        USER_FORM_INFO_FLAG_EMAIL(),
        USER_FORM_INFO_FLAG_LOCATION(),
        USER_FORM_INFO_FLAG_EDUCATION_BACKGROUND(),
        USER_FORM_INFO_FLAG_INDUSTRY(),
        USER_FORM_INFO_FLAG_INCOME(),
        USER_FORM_INFO_FLAG_POST_CODE(),
        USER_FORM_INFO_FLAG_HABIT();

    }

}
