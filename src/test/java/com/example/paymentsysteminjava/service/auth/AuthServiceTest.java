package com.example.paymentsysteminjava.service.auth;

import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AuthServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void canLoadUser(){
        UserEntity build = UserEntity.builder()
            .name("test")
            .password("test")
            .username("test")
            .permission("test")
            .build();
        userRepository.save(build);

        Optional<UserEntity> test = userRepository.findByUsername("test");
        assertTrue(test.isPresent());
    }

}
