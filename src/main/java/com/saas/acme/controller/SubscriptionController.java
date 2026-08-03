package com.saas.acme.controller;

import com.saas.acme.dto.request.SubscriptionRequest;
import com.saas.acme.dto.response.ApiResponse;
import com.saas.acme.dto.response.SubscriptionResponse;
import com.saas.acme.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> findById(@PathVariable Long id) {
        SubscriptionResponse sub = subscriptionService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(sub));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SubscriptionResponse>>> findAll() {
        List<SubscriptionResponse> subs = subscriptionService.findAll();
        return ResponseEntity.ok(ApiResponse.success(subs));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SubscriptionResponse>> create(@Valid @RequestBody SubscriptionRequest request) {
        SubscriptionResponse sub = subscriptionService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(sub));
    }
}
