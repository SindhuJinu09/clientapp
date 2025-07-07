package com.example.clientapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}