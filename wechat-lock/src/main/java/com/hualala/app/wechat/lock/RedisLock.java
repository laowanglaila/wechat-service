package com.hualala.app.wechat.lock;

/**
 * Created by renjianfei on 2017/7/3.
 */
public interface RedisLock {

    Long Lock_Timeout = 30000L;


    /**
     * 在 timeoutSeconds 时间内尝试获取，超时候返回失败
     * @param lockKey
     * @param timeoutSeconds
     * @return
     */
    boolean tryLock(String lockKey, Long timeoutSeconds);


    void realseLock(String lockKey);


}
