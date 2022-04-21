package com.example.paymentsysteminjava.repository;

import com.example.paymentsysteminjava.entity.user.UserEntity;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {
    @Test
    public void findByUsername(){
        UserEntity user  = new UserEntity("me","me","1234");
    }


}
