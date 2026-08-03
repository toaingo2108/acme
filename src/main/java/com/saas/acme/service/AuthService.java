package com.saas.acme.service;

import com.saas.acme.dto.request.LoginRequest;
import com.saas.acme.dto.request.RegisterRequest;
import com.saas.acme.dto.response.AuthResponse;
import com.saas.acme.entity.Role;
import com.saas.acme.entity.User;
import com.saas.acme.exception.DuplicateResourceException;
import com.saas.acme.exception.UnauthorizedException;
import com.saas.acme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            throw new DuplicateResourceException("Email đã tồn tại: " + req.email());
        }
        User user = User.builder()
                .email(req.email())
                .passwordHash(passwordEncoder.encode(req.password())) // Băm mật khẩu
                .name(req.name())
                .role(Role.MEMBER)
                .build();
        User saved = userRepository.save(user);

        String token = jwtService.generateToken(saved.getEmail());
        return new AuthResponse(token, saved.getEmail(), saved.getName(), saved.getRole());
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByEmail(req.email()).orElseThrow(() -> new UnauthorizedException("Email hoặc mật khẩu sai"));

        // SO KHỚP mật khẩu thô với hash
        if (!passwordEncoder.matches(req.password(), user.getPasswordHash())) {
            throw new UnauthorizedException("Email hoặc mật khẩu sai");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(), user.getName(), user.getRole());
    }
}
