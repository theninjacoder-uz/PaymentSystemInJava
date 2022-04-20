package com.example.paymentsysteminjava.dto.payload.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRegisterDto {

    private String username;
    private String password;
    private String name;
    private List<String> permission;

}
