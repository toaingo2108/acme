package com.saas.acme.dto.response;

import com.saas.acme.entity.SubscriptionStatus;

import java.time.Instant;

public record SubscriptionResponse(
        Long id,
        CustomerResponse customer,
        PlanResponse plan,
        SubscriptionStatus status,
        Instant currentPeriodStart,
        Instant currentPeriodEnd,
        Instant createdAt
) {
}
