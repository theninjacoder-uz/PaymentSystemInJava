package com.example.paymentsysteminjava.service.auth;

import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private AuthService authService;

    @BeforeEach
    void setUp() {

        testEntityManager.persist(new UserEntity("test", "ROLE_USER", "test", "test"));
        authService = Mockito.mock(AuthService.class);
    }

    @Test
    public void testUserDatabase(){
        Optional<UserEntity> optionalUser = userRepository.findByUsername("test");
        Assertions.assertTrue(optionalUser.isPresent());
        Assertions.assertEquals(optionalUser.get().getName(),"test");
        Assertions.assertEquals(optionalUser.get().getUsername(),"test");
    }

    @Test
    public void testUserLoad(){

        UserEntity userEntity = new UserEntity("test", "test", "test");

        Mockito.when(authService.loadUserByUsername("test"))
                .thenReturn(userEntity);

        UserDetails user = authService.loadUserByUsername("test");
        Assertions.assertNotNull(user);
        Assertions.assertEquals(userEntity.getUsername(), user.getUsername()    );

        Mockito.verify(authService).loadUserByUsername("test");
    }
}