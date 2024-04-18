package com.test.configuration.redisconfig;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Describe: RedisUtil
 * @Author Happy
 * @Create 2024-03-25 14:37:17
 **/
@Slf4j
@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    
    /**
     * insert data into the list on the right side
     *
     * @param key  key in redis
     * @param list value to insert redis
     *
     * @return the length of the list in this key after insertion
     */
    public Long rightPushList(String key, Collection<String> list) {
        Long lo;
        try {
            String jsonStr = JSONUtil.toJsonStr(list);
            lo = stringRedisTemplate.opsForList().rightPushAll(key, jsonStr);
        } catch (Exception e) {
            log.error("error in RedisConfig right push, key:[{}] list:[{}] errorMsg:[{}]", key, list, e.getMessage());
            lo = -1L;
        }
        return lo;
    }
}
