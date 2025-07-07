package com.example.clientapp.controller;

import com.example.clientapp.model.Client;
import com.example.clientapp.model.Token;
import com.example.clientapp.service.ClientService;
import com.example.clientapp.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/clients")
    public ResponseEntity<?> registerClient(@RequestBody Map<String, Object> request) {
        Client client = new Client();
        client.setClientName((String) request.get("client_name"));
        client.setClientType((String) request.get("client_type"));
        client.setRedirectUris((java.util.List<String>) request.get("redirect_uris"));
        client.setMetadata((Map<String, Object>) request.get("metadata"));
        // For PoC, generate a random secret
        client.setClientSecret(java.util.UUID.randomUUID().toString().replace("-", ""));
        client.setOrganizationUuid("org-demo"); // Hardcoded for PoC
        client.setCreatedBy("system"); // Hardcoded for PoC
        Client saved = clientService.registerClient(client);
        // Simulate encrypted secret in response
        return ResponseEntity.ok(Map.of(
                "client_id", saved.getClientId(),
                "encrypted_client_secret", Map.of(
                        "key_id", "kms_key_demo",
                        "encrypted_value", saved.getClientSecret(),
                        "encryption_scheme", "AES-256-GCM",
                        "iv", "demoiv"
                ),
                "client_name", saved.getClientName(),
                "status", saved.getStatus(),
                "created_at", saved.getCreatedAt().toString()
        ));
    }

    @PostMapping("/token")
    public ResponseEntity<?> issueToken(@RequestBody Map<String, Object> request) {
        String clientId = (String) request.get("client_id");
        // For PoC, skip encrypted credential validation
        if (clientService.getClientById(clientId).isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid client_id"));
        }
        Token token = tokenService.issueToken(clientId);
        return ResponseEntity.ok(Map.of(
                "access_token", token.getAccessToken(),
                "token_type", token.getTokenType(),
                "expires_in", 3600
        ));
    }
} 