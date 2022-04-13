package com.example.paymentsysteminjava.service.gateway;

import com.example.paymentsysteminjava.dto.request.gateway.payme.PaymeCheckTransactionRequestDto;
import com.example.paymentsysteminjava.dto.response.gateway.payme.PaymeCheckTransactioniResponseDto;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymeTransactionService {
    private final RestTemplate restTemplate;

    public TransactionEntity createTransaction(
            TransactionEntity transactionEntity,
            MerchantServiceEntity merchantServiceEntity
    )
    {
        PaymeCheckTransactionRequestDto paymeCheckTransactionRequestDto =
                new PaymeCheckTransactionRequestDto(
                merchantServiceEntity.getId(),
                Timestamp.valueOf(LocalDateTime.now()),
                transactionEntity.getTransactionAccount(),
                transactionEntity.getTransactionAmountToMerchant()
        );

        try {
            PaymeCheckTransactioniResponseDto paymeCheckTransactionRequestDto1 = restTemplate.postForObject(
                    "https://46c0-195-158-30-84.ngrok.io/api/payme/create/transaction",
                    paymeCheckTransactionRequestDto,
                    PaymeCheckTransactioniResponseDto.class
            );
            assert paymeCheckTransactionRequestDto1 != null;
            PaymeCheckTransactioniResponseDto.Result result = paymeCheckTransactionRequestDto1.getResult();
            if (result.getState() == 1){
                transactionEntity.setTransactionState(TransactionState.CHECKED.getState());
            } else {
                transactionEntity.setTransactionState(TransactionState.CHECK_ERROR.getState());
            }
            return transactionEntity;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public TransactionEntity pay(TransactionEntity transactionEntity){
        PaymeCheckTransactioniResponseDto responseDto = restTemplate.postForObject("https://46c0-195-158-30-84.ngrok.io/api/payme/create/transaction",
                transactionEntity,
                PaymeCheckTransactioniResponseDto.class
        );
        assert responseDto != null;
        if (responseDto.getResult().getState() == 1){
            transactionEntity.setTransactionState(TransactionState.SUCCESS.getState());
        } else {
            transactionEntity.setTransactionState(TransactionState.PAY_ERROR.getState());
        }
        return transactionEntity;

    }
}
