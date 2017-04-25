package com.hualala.app.wechat.mapper.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.model.card.MemberModel;
import com.hualala.app.wechat.model.card.MemberModelQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by renjianfei on 2017/4/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class cardMapperTest {

    @Autowired
    private MemberModelMapper memberModelMapper;


    @Test
    public void testMemberMapperInsert(){
        MemberModel memberModel = new MemberModel();
        memberModel.setCardKey(399999L);
        memberModelMapper.insertSelective(memberModel);
    }
    @Test
    public void testMemberMapperSelect(){
//        MemberModel memberModel = new MemberModel();
//        memberModel.setCardKey(99999L);
        MemberModelQuery memberModelQuery = new MemberModelQuery();
        MemberModelQuery.Criteria criteria = memberModelQuery.createCriteria();
        criteria.andCardKeyBetween(19999L,99999L);
        List<MemberModel> memberModels = memberModelMapper.selectByExample(memberModelQuery);
//        MemberModel memberModel1 = memberModelMapper.selectByPrimaryKey(99999L);
        System.out.println("+++++++++++++++++++++++++++++++++++++"+memberModels.get(0).toString());
        String s = JSONObject.toJSONString(memberModels.get(0));
        System.out.println("-------------------------------------"+s);
    }
@Test
    public void testMemberMapperUpdate(){
    MemberModel memberModel = new MemberModel();
    memberModel.setCardKey(399999L);
    int i = memberModelMapper.updateByPrimaryKeySelective(memberModel);
//        MemberModel memberModel1 = memberModelMapper.selectByPrimaryKey(99999L);
        System.out.println("+++++++++++++++++++++++++++++++++++++"+i);

    }
@Test
    public void testMemberMapperDelete(){
    int i = memberModelMapper.deleteByPrimaryKey(399999L);
//        MemberModel memberModel1 = memberModelMapper.selectByPrimaryKey(99999L);
        System.out.println("+++++++++++++++++++++++++++++++++++++"+i);

    }


}
