package com.example.paymentsysteminjava.service.auditing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

class AuditorAwareImplTest {

    @BeforeEach
    void setUp() {
        Authentication authentication =
            new UsernamePasswordAuthenticationToken(
                "jamshid",
                null,
                null
            );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    public void testGetCurrentUser(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assertions.assertEquals("jamshid", username);
    }
}
