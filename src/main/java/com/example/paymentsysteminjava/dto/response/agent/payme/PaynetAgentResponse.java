package com.example.paymentsysteminjava.dto.response.agent.payme;

import com.example.paymentsysteminjava.dto.response.agent.BaseAgentResponse;
import com.example.paymentsysteminjava.entity.agent.AgentEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PaynetAgentResponse implements BaseAgentResponse {

    private String responseMessage;
    private Long transactionId;


    @Override
    public BaseAgentResponse success(TransactionEntity transaction) {
        return new PaynetAgentResponse("success", transaction.getId());
    }

    @Override
    public BaseAgentResponse error(TransactionEntity transaction) {
        return new PaynetAgentResponse("error", null);
    }
}
