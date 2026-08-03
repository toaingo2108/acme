package com.saas.acme.dto.response;

import com.saas.acme.entity.CustomerStatus;

import java.time.Instant;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        CustomerStatus status,
        Instant createdAt
) {
}
