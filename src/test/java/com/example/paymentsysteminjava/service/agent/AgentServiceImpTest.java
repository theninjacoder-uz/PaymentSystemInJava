package com.example.paymentsysteminjava.service.agent;

import com.example.paymentsysteminjava.dto.payload.user.UserRegisterDto;
import com.example.paymentsysteminjava.entity.agent.AgentEntity;
import com.example.paymentsysteminjava.repository.agent.AgentDepositRepository;
import com.example.paymentsysteminjava.repository.agent.AgentRepository;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
class AgentServiceImpTest {

    @Autowired
    private AgentDepositRepository agentDepositRepository;
    private AgentRepository agentRepository;
    private PasswordEncoder passwordEncoder;
    private AgentServiceImp agentServiceImp;

    @BeforeEach
    void setUp() {
        agentRepository = Mockito.mock(AgentRepository.class);
//        agentDepositRepository = Mockito.mock(AgentDepositRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        agentServiceImp = new AgentServiceImp
                (agentRepository, agentDepositRepository, passwordEncoder);
    }

    @Test
    public void canAddAdmin(){
        UserRegisterDto user = UserRegisterDto.builder()
                .username("paynet")
                .password("paynet")
                .name("Paynet")
                .permission(List.of("ROLE_AGENT")).build();

        AgentEntity agentEntity = new AgentEntity();
        agentEntity.setName(user.getName());
        agentEntity.setPassword(user.getPassword());
        agentEntity.setUsername(user.getUsername());
        AgentEntity savedAgent = agentRepository.save(agentEntity);

//        Assertions.
    }

    @Test
    public void hasAgent(){
        when(agentRepository.findByUsername("payme")).thenReturn(null);
        AgentEntity paynet = agentRepository.findByUsername("payme");
        Assertions.assertNull(paynet);
        verify(agentRepository).findByUsername("payme");
    }

}