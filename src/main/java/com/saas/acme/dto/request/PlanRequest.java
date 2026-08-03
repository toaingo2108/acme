package com.saas.acme.dto.request;

import com.saas.acme.entity.PlanCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PlanRequest(
        @NotBlank(message = "Name Khong duoc trong")
        String name,

        @NotBlank(message = "Sku khong duoc trong")
        String sku,

        @NotNull(message = "Category Khong duoc trong")
        PlanCategory category,

        @NotNull(message = "Giá không được trống")
        @Positive(message = "Giá phải lớn hơn 0")
        Integer priceCents
) {
}
