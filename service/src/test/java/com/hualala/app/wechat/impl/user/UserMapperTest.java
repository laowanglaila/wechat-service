package com.hualala.app.wechat.impl.user;

import com.hualala.app.wechat.mapper.user.UserRelationModelMapper;
import com.hualala.app.wechat.model.user.UserRelationModel;
import com.hualala.app.wechat.model.user.UserRelationModelQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by renjianfei on 2017/9/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserRelationModelMapper userRelationModelMapper;
    @Test
    public void test(){
        UserRelationModelQuery userRelationModelQuery = new UserRelationModelQuery();
        List <UserRelationModel> userRelationModels = userRelationModelMapper.selectByExample( userRelationModelQuery );
        System.out.println(userRelationModels);

    }
    public void test2(){

    }

}
