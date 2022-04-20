package com.example.paymentsysteminjava.entity.agent;

import com.example.paymentsysteminjava.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AgentServiceEntity extends BaseEntity {

    private Long agentId;
    private Long serviceId;
    private double commission;

}
