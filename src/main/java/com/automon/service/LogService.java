package com.automon.service;

import com.automon.model.LogEntry;
import com.automon.repository.LogRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void saveLog(String message) {
        LogEntry logEntry = new LogEntry(message);
        logRepository.save(logEntry);
        System.out.println("Saved log: " + message);
    }

    // Modified: Paginated fetch for all logs
    public List<LogEntry> getAllLogs(int page, int size) {
        return (List<LogEntry>) logRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.desc("timestamp"))));
    }

    // Fetch latest log entry (most recent by timestamp)
    public LogEntry getLatestLogEntry() {
       // List<LogEntry> logs = logRepository.findAll(PageRequest.of(0, 1, Sort.by(Sort.Order.desc("timestamp")))).toList();
       // return logs.isEmpty() ? null : logs.get(0);
        // Generate a random log entry
        String randomLog = generateRandomLog();

        // Create a new LogEntry with the formatted log
        LogEntry newLogEntry = new LogEntry(randomLog);

        // Return the newly created log entry
        return newLogEntry;
    }

    // Fetch first log entry (earliest by timestamp)
    public LogEntry getFirstLogEntry() {
        List<LogEntry> logs = logRepository.findAll(PageRequest.of(0, 1, Sort.by(Sort.Order.asc("timestamp")))).toList();
        return logs.isEmpty() ? null : logs.get(0);
    }

    // Helper method to generate a random log message in the required format
    private String generateRandomLog() {
        // Generate a random CPU usage value between 0 and 100
        double cpuUsage = generateRandomDouble(0, 100);

        // Generate a random memory usage value between 0 and 100
        double memoryUsage = generateRandomDouble(0, 100);

        // Get the current timestamp in ISO-8601 format
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        // Format the log entry as a JSON string
        return String.format("{\"timestamp\":\"%s\", \"cpu\": %.2f, \"memory\": %.2f}",
                timestamp, cpuUsage, memoryUsage);
    }

    // Helper method to generate a random double between a given range
    private double generateRandomDouble(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }
}
