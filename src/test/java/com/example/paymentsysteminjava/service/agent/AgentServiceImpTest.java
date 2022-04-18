package com.example.paymentsysteminjava.service.agent;

import com.example.paymentsysteminjava.repository.agent.AgentDepositRepository;
import com.example.paymentsysteminjava.repository.agent.AgentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AgentServiceImpTest {

    private AgentRepository agentRepository;
    private AgentDepositRepository agentDepositRepository;
    private PasswordEncoder passwordEncoder;
    private AgentServiceImp agentServiceImp;

    @BeforeEach
    void setUp(){
        agentRepository = mock(AgentRepository.class);
        agentDepositRepository = mock(AgentDepositRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        agentServiceImp = new AgentServiceImp(agentRepository, agentDepositRepository, passwordEncoder);
    }



    @Test
    public void canAgentAdd(){



    }


}