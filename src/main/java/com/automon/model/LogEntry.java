package com.automon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import java.time.LocalDateTime;

@Document(indexName = "logs")
public class LogEntry {

    @Id
    private String id;
    private String message;


    private LocalDateTime timestamp;

    public LogEntry(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
