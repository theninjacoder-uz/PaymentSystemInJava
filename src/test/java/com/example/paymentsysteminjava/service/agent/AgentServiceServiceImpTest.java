package com.example.paymentsysteminjava.service.agent;

import com.example.paymentsysteminjava.dto.payload.agent.AgentServiceDto;
import com.example.paymentsysteminjava.entity.agent.AgentServiceEntity;
import com.example.paymentsysteminjava.repository.agent.AgentServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AgentServiceServiceImpTest {

    @Autowired
    private AgentServiceRepository agentServiceRepository;

    private AgentServiceServiceImp agentServiceServiceImp;

    @BeforeEach
    void setUp() {
//        agentServiceRepository = Mockito.mock(AgentServiceRepository.class);
        agentServiceServiceImp = new AgentServiceServiceImp(agentServiceRepository);

        AgentServiceEntity agentServiceEntity = new AgentServiceEntity();
        agentServiceEntity.setServiceId(1L);
        agentServiceEntity.setServiceId(100L);
        agentServiceEntity.setCommission(0.1);
        agentServiceRepository.save(agentServiceEntity);
    }

    @Test
    public void testAgentServiceDto(){

    }





}