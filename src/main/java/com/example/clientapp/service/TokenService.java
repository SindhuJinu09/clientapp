package com.example.clientapp.service;

import com.example.clientapp.model.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {
    private final Map<String, Token> tokenStore = new ConcurrentHashMap<>();
    private static final String SECRET = "my-very-secret-key-for-jwt-signing";

    public Token issueToken(String clientId) {
        String tokenId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusHours(1);
        String jwt = Jwts.builder()
                .setId(tokenId)
                .setSubject(clientId)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiresAt.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
        Token token = new Token();
        token.setTokenId(tokenId);
        token.setClientId(clientId);
        token.setAccessToken(jwt);
        token.setTokenType("Bearer");
        token.setCreatedAt(now);
        token.setExpiresAt(expiresAt);
        tokenStore.put(tokenId, token);
        return token;
    }

    // Add more methods as needed
} 