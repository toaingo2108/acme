package com.saas.acme.dto.response;

import com.saas.acme.entity.Role;

public record AuthResponse(
        String token,
        String email,
        String name,
        Role role
) {
}
