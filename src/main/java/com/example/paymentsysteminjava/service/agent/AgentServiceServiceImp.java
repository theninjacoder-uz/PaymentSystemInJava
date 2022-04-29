package com.example.paymentsysteminjava.service.agent;

import com.example.paymentsysteminjava.dto.payload.agent.AgentServiceDto;
import com.example.paymentsysteminjava.dto.response.BaseApiResponse;
import com.example.paymentsysteminjava.entity.agent.AgentServiceEntity;
import com.example.paymentsysteminjava.repository.agent.AgentServiceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentServiceServiceImp implements AgentServiceService{

    private final AgentServiceRepository agentServiceRepository;
    private final ModelMapper modelMapper;


    @Override
    public BaseApiResponse add(AgentServiceDto agentServiceDto) {

        if(agentServiceRepository.findByServiceIdAndAgentId(
                agentServiceDto.getAgentId(),
                agentServiceDto.getServiceId()
        ).isPresent())
            return new BaseApiResponse(11, "Service Already exist", null);

        var entity = agentServiceRepository.save(modelMapper.map(agentServiceDto, AgentServiceEntity.class));
        return new BaseApiResponse(1, "Service Added", entity);
    }
}
