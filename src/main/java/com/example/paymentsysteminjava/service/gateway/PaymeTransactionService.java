package com.example.paymentsysteminjava.service.gateway;

import com.example.paymentsysteminjava.dto.payload.merchant.MerchantTransactionCheckDto;
import com.example.paymentsysteminjava.dto.request.gateway.payme.PaymeCheckTransactionRequestDto;
import com.example.paymentsysteminjava.dto.response.gateway.payme.PaymeCheckTransactioniResponseDto;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
//            PaymeCheckTransactioniResponseDto.Result result = restTemplate.postForObject(
//                    "https://eo5ypg4ok6vhnzs.m.pipedream.net",
//                    paymeCheckTransactionRequestDto,
//                    PaymeCheckTransactioniResponseDto.Result.class
//            );

//            HttpEntity<PaymeCheckTransactionRequestDto>
//                    paymeCheckTransactionRequestDtoHttpEntity = new HttpEntity<>(paymeCheckTransactionRequestDto);
//
//
//            MerchantTransactionCheckDto result = restTemplate.exchange(
//                    "https://eo5ypg4ok6vhnzs.m.pipedream.net",
//                    HttpMethod.POST,
//                    paymeCheckTransactionRequestDtoHttpEntity,
//                    MerchantTransactionCheckDto.class
//            ).getBody();

            MerchantTransactionCheckDto result = new MerchantTransactionCheckDto(
                    "100",
                    transactionEntity.getTransactionState(),
                    1
            );

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
        PaymeCheckTransactioniResponseDto.Result responseDto = restTemplate.postForObject(
                "https://eo5ypg4ok6vhnzs.m.pipedream.net",
                transactionEntity,
                PaymeCheckTransactioniResponseDto.Result.class
        );
        assert responseDto != null;
        if (responseDto.getState() == 1){
            transactionEntity.setTransactionState(TransactionState.SUCCESS.getState());
        } else {
            transactionEntity.setTransactionState(TransactionState.PAY_ERROR.getState());
        }
        return transactionEntity;

    }
}
