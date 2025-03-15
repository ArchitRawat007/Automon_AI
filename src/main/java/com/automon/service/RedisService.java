package com.automon.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Store system metrics in Redis with expiration
    public void cacheMetrics(String key, String metrics) {
        redisTemplate.opsForValue().set(key, metrics, Duration.ofMinutes(10)); // Cache for 10 minutes
    }

    // Retrieve cached system metrics
    public String getCachedMetrics(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}


