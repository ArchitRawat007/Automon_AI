package com.automon.service;

import com.automon.handler.WebSocketHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class MetricsPublisherService {
    private final Random random = new Random();
    private final RedisService redisService;

    public MetricsPublisherService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Scheduled(fixedRate = 5000) // Every 5 seconds
    public void sendMetrics() throws IOException {
        double cpuUsage = 20 + (random.nextDouble() * 50);
        double memoryUsage = 30 + (random.nextDouble() * 40);

        String metrics = String.format(
                "{\"timestamp\":\"%s\", \"cpu\": %.2f, \"memory\": %.2f}",
                LocalDateTime.now(), cpuUsage, memoryUsage);

        // Store metrics in Redis
        redisService.cacheMetrics("latestMetrics", metrics);

        // Broadcast via WebSocket
        WebSocketHandler.broadcast(metrics);

        System.out.println("Broadcasted & Cached: " + metrics);
    }
}


