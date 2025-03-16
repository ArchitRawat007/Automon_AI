package com.automon.controller;

import com.automon.service.LogService;
import com.automon.service.RedisService;
import com.automon.model.LogEntry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private final RedisService redisService;
    private final LogService logService;

    public MetricsController(RedisService redisService, LogService logService) {
        this.redisService = redisService;
        this.logService = logService;
    }

    // Existing endpoint to fetch the latest metrics and logs
    @GetMapping("/data")
    public String getLatestMetrics() {
        // Fetch the latest metrics from Redis
        String redisMetrics = redisService.getCachedMetrics("latestMetrics");

        // Fetch the latest logs
        LogEntry latestLogEntry = logService.getLatestLogEntry();

        // If there are no logs, create a new log entry and return it
        if (latestLogEntry == null) {
            latestLogEntry = createNewLogEntry("No logs found, creating a new log entry.");
        }

        // Return the latest log entry or Redis metrics
        return formatLogEntry(latestLogEntry);
    }

    // New endpoint to fetch the first log entry
    @GetMapping("/first-log")
    public String getFirstLog() {
        // Fetch the first log entry
        LogEntry firstLogEntry = logService.getFirstLogEntry();

        // If there is no first log, create one
        if (firstLogEntry == null) {
            firstLogEntry = createNewLogEntry("No first log found, creating a new log entry.");
        }

        // Return the first log message or a fallback message
        return formatLogEntry(firstLogEntry);
    }

    // Helper method to format LogEntry for Grafana
    private String formatLogEntry(LogEntry logEntry) {
        // Assuming Grafana expects JSON format with timestamp and message
        return String.format("{\"timestamp\": \"%s\", \"message\": \"%s\"}",
                logEntry.getTimestamp(), logEntry.getMessage());
    }

    // Helper method to create a new LogEntry and save it
    private LogEntry createNewLogEntry(String message) {
        LogEntry newLogEntry = new LogEntry(message);
        logService.saveLog(newLogEntry.getMessage());
        return newLogEntry;
    }
}



