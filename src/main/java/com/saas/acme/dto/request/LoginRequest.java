package com.saas.acme.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 6, message = "Mật khẩu tối thiểu 6 ký tự")
        String password
) {
}
