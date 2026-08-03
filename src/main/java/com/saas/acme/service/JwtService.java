package com.saas.acme.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-ms}")
    private long expirationMs;

    // Tạo key từ chuỗi secret
    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // 1. Sinh token từ email (Subject)
    public String generateToken(String email) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .subject(email) // Thêm email vào token
                .issuedAt(now)
                .expiration(expiry)
                .signWith(key())  // ký bằng secret
                .compact();
    }

    // 2. Đọc email từ token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 3. Kiểm tra token còn hạn + đúng chữ ký
    public boolean isTokenValid(String token, String email) {
        return email.equals(extractEmail(token)) && !isExpired(token);
    }

    public boolean isExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    // Helper: Giải mã token -> lấy 1 claim bất kỳ
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser()
                .verifyWith(key()) // xac thuc chu ky
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return resolver.apply(claims);
    }
}
