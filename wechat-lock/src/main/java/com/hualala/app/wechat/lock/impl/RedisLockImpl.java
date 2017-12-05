package com.hualala.app.wechat.lock.impl;

import com.hualala.app.wechat.lock.RedisLock;
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
public class RedisLockImpl implements RedisLock{

    private static final Long Lock_Timeout = 30000L;
    private Logger logger = Logger.getLogger(this.getClass());
    private ThreadLocal<Jedis>jedisThreadLocal ;
    @Autowired
    @Qualifier("jedisPool")JedisPool jedisPool;
    private RedisLockImpl(){
        jedisThreadLocal = new ThreadLocal<>();
    }
    private boolean innerTryLock(String lockKey){
        Jedis jedis = jedisThreadLocal.get();
        ThreadLocal<Jedis>jedisThreadLocal = new ThreadLocal<>();
        jedisThreadLocal.set(jedis);
            long currentTime = System.currentTimeMillis();
            String lockTimeDuration = String.valueOf(currentTime + Lock_Timeout);
            Long result = jedis.setnx(lockKey, lockTimeDuration);

            if(result == 1){
                return true;
            }else {
                if(checkIfLockTimeout(currentTime, lockKey)){
                    String preLockTimeDuration = jedis.getSet(lockKey,lockTimeDuration);
                    if(currentTime > Long.parseLong(preLockTimeDuration==null?"0":preLockTimeDuration)){
                        return true;
                    }
                }
                return false;
            }
    }


    public boolean tryLock(String lockKey, Long timeoutSeconds){
        Jedis jedis = jedisPool.getResource();
        if (jedis == null || !jedis.isConnected()){
            logger.info("Execute jedisPool.getResource method, erro.");
            return false;
        }
        jedisThreadLocal.set(jedis);
        try{
            Long currentTime = System.currentTimeMillis();
            boolean result = false;

            while (true){
                if((System.currentTimeMillis() - currentTime)/1000 > timeoutSeconds){
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
            if (!result && jedis != null && jedis.isConnected()){
                jedis.close();
            }
            return result;
        }catch (Exception e){
            logger.error("Failed to run RedisLockHandler.tryLock method.", e);
            if (jedis != null && jedis.isConnected()){
                jedis.close();
            }
            return false;
        }
    }


    public void realseLock(String lockKey) {
        Jedis jedis = jedisThreadLocal.get();
        if (jedis != null && jedis.isConnected()){
            try {
                jedis.del(lockKey);
            }finally {
                jedis.close();
            }
        }
    }


    public boolean checkIfLockTimeout(Long currentTime, String lockKey){
        Jedis jedis = jedisThreadLocal.get();
            String s = jedis.get(lockKey);
            if(currentTime > Long.parseLong(s==null?"0":s)){
                return true;
            }else {
                return false;
            }
    }

}
