package com.automon.service;
import com.automon.model.LogEntry;
import com.automon.repository.LogRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public List<LogEntry> getAllLogs() {
        return (List<LogEntry>) logRepository.findAll();
    }
}

