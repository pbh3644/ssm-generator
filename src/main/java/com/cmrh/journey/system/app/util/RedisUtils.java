package com.cmrh.journey.system.app.util;

import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;

/**
 * @Author：pbh
 * @Date：2018-09-09 19:24
 * @Description：redis缓存工具类
 */
@Data
public class RedisUtils {
    /**
     * redisTemplate
     */
    public static RedisTemplate redisTemplate;

    /**
     * operations
     */
    public static ValueOperations<String, Map<String, Object>> operations;


    /**
     * 该工程服务的名字
     */
    public static String applicationName;

    /**
     * 雪花算法获取数字ID
     */
    public static Long nextId(Class cla) {
        Map<String, Object> map = operations.get(applicationName);
        int applicationId = Integer.parseInt(map.get("applicationId").toString());
        String simpleName = cla.getSimpleName();
        int organizationId = Integer.parseInt(map.get(simpleName).toString());
        return new SnowflakeIdWorker(applicationId, organizationId).nextId();
    }
}
