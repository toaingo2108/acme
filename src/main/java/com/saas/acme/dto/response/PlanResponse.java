package com.saas.acme.dto.response;

import com.saas.acme.entity.PlanCategory;

import java.time.Instant;

public record PlanResponse(
        Long id,
        String name,
        String sku,
        PlanCategory category,
        Integer priceCents,
        boolean active,
        Instant createdAt
) {
}
