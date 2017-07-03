package com.hualala.app.wechat.util;

import com.hualala.app.wechat.service.RedisLockHandler;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by renjianfei on 2017/7/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLockTest {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private RedisLockHandler redisLockHandler;
    private String key = "test_redis_key";
    @Autowired
    private RedissonClient redissonClient;

    private int count = 500;

    @Test
    public void testLock() {
//        RedisLockHandler redisLockHandler = new RedisLockHandler();
        String key = "test_redis_key";
        try {
            boolean test_redis_key = redisLockHandler.tryLock(key, 1000L);
            if (test_redis_key) {
//                work(name);
                System.out.println("-------------");
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            redisLockHandler.realseLock(key);
        }
    }

    public void testRedissionLock(String name) {
        RLock locker = redissonClient.getLock(key);
        try {
            locker.lock(10L, TimeUnit.SECONDS);


            work(name);


        } finally {
            if (locker != null && locker.isLocked()) {
                locker.unlock();
            }
        }
    }


    public void testLockedWork(String name) {
        try {
            boolean test_redis_key = redisLockHandler.tryLock(key, 1000L);
            if (test_redis_key) {
                work(name);
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            redisLockHandler.realseLock(key);
        }

    }

    @Test
    public void test() {
        System.out.println("开始操作！");
        for (int n = 1; n < 10; n++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                testLockedWork(name);
//                testRedissionLock(name);
            }, "线程：" + n).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        System.out.println("结束操作！");

    }

    public void work(String name) {
        while (count > 0) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info(name + "正在操作：" + count--);
        }
    }

}
