package com.example.clientapp.service;

import com.example.clientapp.model.Client;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClientService {
    private final Map<String, Client> clientStore = new ConcurrentHashMap<>();

    public Client registerClient(Client client) {
        client.setClientId(UUID.randomUUID().toString());
        client.setCreatedAt(LocalDateTime.now());
        client.setStatus("active");
        clientStore.put(client.getClientId(), client);
        return client;
    }

    public Optional<Client> getClientById(String clientId) {
        return Optional.ofNullable(clientStore.get(clientId));
    }

    // Add more methods as needed
} 