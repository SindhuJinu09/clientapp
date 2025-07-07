package com.example.clientapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String tokenId;
    private String clientId;
    private String accessToken;
    private String tokenType;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
}