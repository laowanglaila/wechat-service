package com.hualala.app.wechat.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by renjianfei on 2017/7/3.
 */
@Service
public class RedisLockHandler {

    private static final Long Lock_Timeout = 30000L;
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    @Qualifier("jedisPool")JedisPool jedisPool;
//    private RedisLockHandler(){
//        jedis = jedisPool.getResource();
//    }
    private boolean innerTryLock(String lockKey){
        Jedis jedis = jedisPool.getResource();
        try {
            long currentTime = System.currentTimeMillis();
            String lockTimeDuration = String.valueOf(currentTime + Lock_Timeout);
            Long result = jedis.setnx(lockKey, lockTimeDuration);

            if(result == 1){
                return true;
            }else {
                if(checkIfLockTimeout(currentTime, lockKey)){
                    String preLockTimeDuration = jedis.getSet(lockKey,lockTimeDuration);
                    if(currentTime > Long.valueOf(preLockTimeDuration==null?"0":preLockTimeDuration)){
                        return true;
                    }
                }
                return false;
            }
        }finally {
            jedis.close();
        }
    }


    public boolean tryLock(String lockKey, Long timeout){
        try{
            Long currentTime = System.currentTimeMillis();
            boolean result = false;

            while (true){
                if((System.currentTimeMillis() - currentTime) > timeout){
                    logger.info("Execute RedisLockHandler.tryLock method, Time out.");
                    break;
                }else {
                    result = innerTryLock(lockKey);
                    if(result){
                        break;
                    }else {
                        logger.debug("Try to get the Lock,and wait 100 millisecond....");
                        Thread.sleep(100);
                    }
                }
            }
            return result;
        }catch (Exception e){
            logger.error("Failed to run RedisLockHandler.tryLock method.", e);
            return false;
        }
    }


    public void realseLock(String lockKey) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(lockKey);
        }finally {
            jedis.close();
        }
    }


    public boolean checkIfLockTimeout(Long currentTime, String lockKey){
        Jedis jedis = jedisPool.getResource();
        try {
            String s = jedis.get(lockKey);
            if(currentTime > Long.valueOf(s==null?"0":s)){
                return true;
            }else {
                return false;
            }
        }finally {
            jedis.close();
        }

    }

}
