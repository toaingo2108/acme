package com.saas.acme.dto.request;

import jakarta.validation.constraints.NotNull;

public record SubscriptionRequest(
        @NotNull(message = "customerId khong duoc trong")
        Long customerId,

        @NotNull(message = "planId khong duoc trong")
        Long planId
) {
}
