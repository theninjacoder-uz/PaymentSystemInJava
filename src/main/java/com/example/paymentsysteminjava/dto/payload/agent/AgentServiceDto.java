package com.example.paymentsysteminjava.dto.payload.agent;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgentServiceDto {
    private double commission;
    private long agentId;
    private long serviceId;
}
