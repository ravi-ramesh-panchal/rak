package com.example.feeservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Component
public class StudentClient {

    private final WebClient webClient;
    private final Duration timeout = Duration.ofSeconds(3);

    public StudentClient(@Value("${student.service.base-url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<Map> getStudentById(String studentId) {
        return webClient.get()
                .uri("/api/students/{studentId}", studentId)
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(timeout);
    }
}

