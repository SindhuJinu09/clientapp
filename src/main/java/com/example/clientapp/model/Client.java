package com.example.clientapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String clientId;
    private String clientSecret;
    private String clientName;
    private String organizationUuid;
    private List<String> redirectUris;
    private String clientType;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private Map<String, Object> metadata;
    private List<String> auditTrailIds;
}