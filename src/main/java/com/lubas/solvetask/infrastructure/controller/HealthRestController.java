package com.lubas.solvetask.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/api/rest/v0.1")
public class HealthRestController {
    private final Map<String, String> result = Map.of("status", "UP");
    private final ResponseEntity<Map<String, String>> response = ResponseEntity.ok(result);

    @GetMapping(path = "/health")
    public ResponseEntity<Map<String, String>> handleHealthRequest() {
        return response;
    }
}
