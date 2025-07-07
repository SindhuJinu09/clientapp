package com.example.clientapp.model;

import java.time.LocalDateTime;
import java.util.Map;

public class AuditLog {
    private String auditId;
    private String entityType;
    private String entityId;
    private String action;
    private String performedBy;
    private LocalDateTime performedAt;
    private Map<String, Object> changes;
    private String reason;
    private String ipAddress;
    private String userAgent;

    // Getters and setters omitted for brevity

    // Constructors, equals, hashCode, toString can be generated as needed
} 