package com.example.paymentsysteminjava.service.jwt;

import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.exception.JwtExpiredTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class JwtProviderTest {

    @Mock
    private UserEntity userEntity;

    @InjectMocks
    private JwtProvider jwtProvider;

    @Test
    public void testGetAccessTokenFromRefreshToken() {
        String refreshToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU5MjY0NjQwMCwiaWF0IjoxNTkyNjQyODAwLCJhdXRob3JpdGllcyI6ImFkbWluIn0.q-_7-8_2-XvqZKg5kY_9xlL3m4f8n1Q7r2HVvRqXtZBHsK-uWYy7Dz9k6n1g5pf8lT_r2ePb4x3mRQoLcCKXEw";
        String accessToken = jwtProvider.getAccessTokenFromRefreshToken(refreshToken);
        assertNotNull(accessToken);
    }

    @Test
    public void testGetAccessTokenFromRefreshTokenExpired() {
        String refreshToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU5MjY0NjQwMCwiaWF0IjoxNTkyNjQ2MzQwLCJhdXRob3JpdGllcyI6ImFkbWluIn0.q-_7-8_v-XqZyKkY_l9g5f8x7L3n1r2m4HqXtRKvVgZyfDYsWnk1uB-9l5pP4b6e_8rL7xT3Cm2sQqcRVHhXEg";
        assertThrows(JwtExpiredTokenException.class, () -> jwtProvider.getAccessTokenFromRefreshToken(refreshToken));
    }

    @Test
    public void testParseAccessToken() {
        String accessToken = jwtProvider.generateAccessToken(userEntity);
        Claims claims = jwtProvider.parseAccessToken(accessToken);

        assertEquals(userEntity.getUsername(), claims.getSubject());
    }

    @Test
    public void testParseAccessTokenExpired() {
        String accessToken = "Bearer " + Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, jwtProvider.accessTokenSecretKey)
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() - jwtProvider.accessTokenExpiredTime))
            .setSubject("test")
            .claim("authorities", "test")
            .compact();

        assertThrows(JwtExpiredTokenException.class, () -> jwtProvider.parseAccessToken(accessToken));
    }

    @Test
    public void testGenerateRefreshToken() {
        String refreshToken = jwtProvider.generateRefreshToken(userEntity);
        assertNotNull(refreshToken);
    }

    @Test
    public void testGenerateAccessToken() {
        String accessToken = jwtProvider.generateAccessToken(userEntity);
        assertNotNull(accessToken);
    }
}
