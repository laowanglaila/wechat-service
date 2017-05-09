package com.hualala.app.wechat.mapper.card;

import com.alibaba.fastjson.JSONObject;
import com.hualala.app.wechat.model.card.MemberModel;
import com.hualala.app.wechat.model.card.MemberModelQuery;
import com.hualala.core.base.BaseItem;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
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
    public void testMemberMapperInsert() throws MySQLIntegrityConstraintViolationException {
        MemberModel memberModel = new MemberModel();
        memberModel.setCardKey(123123L);
        memberModel.setMpID("adsf");
        memberModel.setGroupID(12321L);
        memberModel.setCardType("asdfasdf");
        memberModel.setTitle("海底捞测试");
        memberModelMapper.insertSelective(memberModel);
    }
    @Test
    public void testMemberMapperSelect(){
//        MemberModel memberModel = new MemberModel();
//        memberModel.setCardKey(99999L);
        MemberModelQuery memberModelQuery = new MemberModelQuery();
        MemberModelQuery.Criteria criteria = memberModelQuery.createCriteria();
        criteria.andCardKeyBetween(12312L,123123L);
        List<MemberModel> memberModels = memberModelMapper.selectByExample(memberModelQuery);
//        MemberModel memberModel1 = memberModelMapper.selectByPrimaryKey(99999L);
        System.out.println("+++++++++++++++++++++++++++++++++++++"+memberModels.get(0).toString());
        String s = JSONObject.toJSONString(memberModels.get(0));
        System.out.println("-------------------------------------"+s);
    }
@Test
    public void testMemberMapperUpdate(){
    MemberModel memberModel = new MemberModel();
    memberModel.setCardKey(123123L);
    memberModel.setAutoActivate(true);
    int i = memberModelMapper.updateByPrimaryKeySelective(memberModel);
//        MemberModel memberModel1 = memberModelMapper.selectByPrimaryKey(99999L);
        System.out.println("+++++++++++++++++++++++++++++++++++++"+i);

    }
@Test
    public void testMemberMapperDelete(){
    int i = memberModelMapper.deleteByPrimaryKey(123123L);
//        MemberModel memberModel1 = memberModelMapper.selectByPrimaryKey(99999L);
        System.out.println("+++++++++++++++++++++++++++++++++++++"+i);

    }


    @Test
    public void testMemberMapperLike(){
        MemberModelQuery memberModelQuery = new MemberModelQuery();
        MemberModelQuery.Criteria criteria = memberModelQuery.createCriteria();
        criteria.andTitleLike("%测试");
        List<MemberModel> memberModels = memberModelMapper.selectByExample(memberModelQuery);
        System.out.println("+++++++++++++++++++++++++++++++++++++"+memberModels.get(0).toString());
        String s = JSONObject.toJSONString(memberModels.get(0));
        System.out.println("-------------------------------------"+s);
    }
    @Test
    public void testBaseItem(){

        BaseItem baseItem = new BaseItem();
        System.out.println(baseItem);
    }

}
