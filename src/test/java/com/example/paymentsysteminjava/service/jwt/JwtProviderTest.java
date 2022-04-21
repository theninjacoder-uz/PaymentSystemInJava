package com.example.paymentsysteminjava.service.jwt;

import com.example.paymentsysteminjava.entity.user.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class JwtProviderTest {

    private UserEntity userEntity;
    private JwtProvider jwtProvider;

    @BeforeEach
    public void setUp() {
        jwtProvider = Mockito.mock(JwtProvider.class);
        userEntity = Mockito.mock(UserEntity.class);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateAccessToken() {

        userEntity = new UserEntity("test", "test", "test");

        Mockito.when(jwtProvider.generateAccessToken(userEntity))
                .thenReturn("Bearer asdfase");

        String accessToken = jwtProvider.generateAccessToken(userEntity);
        Assertions.assertNotNull(accessToken);
        Assertions.assertTrue(accessToken.startsWith("Bearer "));
        Mockito.verify(jwtProvider).generateAccessToken(userEntity);
    }

}