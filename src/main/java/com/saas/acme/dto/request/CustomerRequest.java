package com.saas.acme.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank(message = "Name không được trống")
        String name,

        @NotBlank(message = "Email không được trống")
        @Email(message = "Email không hợp lệ")
        String email
) {
}
