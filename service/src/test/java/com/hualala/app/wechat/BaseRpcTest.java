package com.hualala.app.wechat;

import com.hualala.core.client.BaseRpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by renjianfei on 2017/4/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseRpcTest {

    @Autowired
    public BaseRpcClient baseRpcClient;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseRpcTest.class);
    @Test
    public abstract void test();

}
