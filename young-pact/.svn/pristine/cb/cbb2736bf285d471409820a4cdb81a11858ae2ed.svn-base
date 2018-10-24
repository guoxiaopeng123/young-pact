package com.young.pact.manager.common.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Component;

import com.tools.common.redis.client.RedisClient;
import com.tools.common.redis.exception.RedisAccessException;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.manager.common.CommonManager;

/**
 * @描述 : 公用Manager组件
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月3日 下午2:49:22
 */
@Component("commonManager")
public class CommonManagerImpl implements CommonManager {
    private static Log LOG = LogFactoryImpl.getLog(CommonManagerImpl.class);
    private RedisClient redisClient;
    @Override
    public void deleteRedis(String[] arr) {
            if(null!= arr){
                for (int i = 0; i < arr.length; i++) {
                    try {
                        redisClient.del(arr[i]);
                    } catch (RedisAccessException e) {
                        LOG.error(ErrorPactConsts.REDIS_SET_EROR + arr[i] , e);
                        throw new PactManagerExcepion(ErrorPactConsts.REDIS_SET_EROR + arr[i], e);
                    }
                }
            }
    }
    public RedisClient getRedisClient() {
        return redisClient;
    }
    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }
}
