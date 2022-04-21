package com.example.paymentsysteminjava.service.agent;

import com.example.paymentsysteminjava.entity.agent.AgentServiceEntity;
import com.example.paymentsysteminjava.repository.agent.AgentServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AgentServiceServiceImpTest {

    @Autowired
    private AgentServiceRepository agentServiceRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    private AgentServiceServiceImp agentServiceServiceImp;


    @BeforeEach
    public void setUp() {
//        agentServiceRepository = Mockito.mock(AgentServiceRepository.class);
        agentServiceServiceImp = new AgentServiceServiceImp(agentServiceRepository);

        AgentServiceEntity agentServiceEntity = new AgentServiceEntity();
        agentServiceEntity.setAgentId(1L);
        agentServiceEntity.setServiceId(100L);
        agentServiceEntity.setCommission(0.1);

        AgentServiceEntity agentServiceEntity1 = new AgentServiceEntity();
        agentServiceEntity.setAgentId(2L);
        agentServiceEntity.setServiceId(200L);
        agentServiceEntity.setCommission(0.15);

        testEntityManager.persist(agentServiceEntity);
        testEntityManager.persist(agentServiceEntity1);
//        agentServiceRepository.save(agentServiceEntity);
//        agentServiceRepository.save(agentServiceEntity1);
    }

    @Test
    public void testAgentTableSize(){
        List<AgentServiceEntity> all = agentServiceRepository.findAll();
        Assertions.assertNotNull(all);
        Assertions.assertEquals(2, all.size());
    }


    @Test
    public void testHasAgentService(){
        Optional<AgentServiceEntity> entityOptional =
                agentServiceRepository.findByServiceIdAndAgentId(100L, 1L);

        Assertions.assertTrue(entityOptional.isPresent());
        Assertions.assertEquals(entityOptional.get().getAgentId(), 1L);
        Assertions.assertEquals(entityOptional.get().getServiceId(), 200L);
    }
}