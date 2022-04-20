package com.example.paymentsysteminjava.service.gateway;


import com.example.paymentsysteminjava.dto.request.gateway.payme.PaymeCheckTransactionRequestDto;
import com.example.paymentsysteminjava.dto.response.gateway.payme.PaymeCheckTransactioniResponseDto;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PaymeTransactionServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PaymeTransactionService paymeTransactionService;



    @BeforeEach
    void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        rabbitTemplate =Mockito.mock(RabbitTemplate.class);
        paymeTransactionService = new PaymeTransactionService(restTemplate, rabbitTemplate);

    }

    @Test
    public void testPay() {
        TransactionEntity transactionEntity = TransactionEntity.builder()
            .transactionState(TransactionState.CHECKED.getState())
            .transactionAmountFromAgent(new BigDecimal(100))
            .transactionAmountToMerchant(new BigDecimal(100))
            .transactionAccount("123456789")
            .build();

        PaymeCheckTransactioniResponseDto.Result result = new PaymeCheckTransactioniResponseDto.Result();
        result.setState(6);

        when(restTemplate.postForObject(anyString(), any(), any())).thenReturn(result);

        TransactionEntity transactionEntity1 = paymeTransactionService.pay(transactionEntity);

        assertEquals(TransactionState.SUCCESS.getState(), transactionEntity1.getTransactionState());
    }

    @Test
    public void testPayException() {
        TransactionEntity transactionEntity = TransactionEntity.builder()
            .transactionState(TransactionState.CHECKED.getState())
            .transactionAmountFromAgent(new BigDecimal(100))
            .transactionAmountToMerchant(new BigDecimal(100))
            .transactionAccount("123456789")
            .build();

        MerchantServiceEntity merchantServiceEntity = MerchantServiceEntity.builder()
            .merchantServiceId(1l)
            .name("test")
            .build();

        PaymeCheckTransactionRequestDto paymeCheckTransactionRequestDto =
            new PaymeCheckTransactionRequestDto(
                merchantServiceEntity.getId(),
                Timestamp.valueOf(LocalDateTime.now()),
                transactionEntity.getTransactionAccount(),
                transactionEntity.getTransactionAmountToMerchant()
            );

        PaymeCheckTransactioniResponseDto.Result result = new PaymeCheckTransactioniResponseDto.Result();
        result.setState(7);

        when(restTemplate.postForObject(anyString(), any(), any())).thenThrow(new RuntimeException());

        TransactionEntity resultTransaction = paymeTransactionService.pay(transactionEntity);

        assertEquals(TransactionState.IN_PROCESS.getState(), resultTransaction.getTransactionState());

    }

    @Test
    public void testCreateTransaction() {
        TransactionEntity transactionEntity = TransactionEntity.builder()
            .transactionState(TransactionState.CREATED.getState())
            .transactionAmountFromAgent(new BigDecimal(100))
            .transactionAmountToMerchant(new BigDecimal(100))
            .transactionAccount("123456789")
            .build();

        MerchantServiceEntity merchantServiceEntity = MerchantServiceEntity.builder()
            .name("test")
            .merchantServiceId(1L)
            .build();
        merchantServiceEntity.setId(1l);

        PaymeCheckTransactionRequestDto paymeCheckTransactionRequestDto = new PaymeCheckTransactionRequestDto(
            "1",
            Timestamp.valueOf(LocalDateTime.now()),
            "123456789",
            new BigDecimal(100)
        );

        PaymeCheckTransactioniResponseDto.Result result = new PaymeCheckTransactioniResponseDto.Result();
        result.setState(6);

        when(restTemplate.postForObject(anyString(), any(), any())).thenReturn(result);

        TransactionEntity transactionEntity1 = paymeTransactionService.createTransaction(transactionEntity, merchantServiceEntity);

        assertEquals(TransactionState.CHECKED.getState(), transactionEntity1.getTransactionState());

    }

    @Test
    public void testCreateTransactionWithNullResult() {
        TransactionEntity transactionEntity = TransactionEntity.builder()
            .transactionAccount("123456789")
            .transactionAmountToMerchant(BigDecimal.valueOf(100))
            .build();

        MerchantServiceEntity merchantServiceEntity = MerchantServiceEntity.builder()

            .build();

        merchantServiceEntity.setId(1l);

        when(restTemplate.postForObject(anyString(), any(), any())).thenReturn(null);

        TransactionEntity result = paymeTransactionService.createTransaction(transactionEntity, merchantServiceEntity);

        assertEquals(TransactionState.CHECK_ERROR.getState(), result.getTransactionState());
    }
}
