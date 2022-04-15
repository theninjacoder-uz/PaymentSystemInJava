package com.example.paymentsysteminjava.dto.request.gateway.paynet;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
public class PaynetCheckTransactionRequestDto {

    private Long id;
    private Timestamp timestamp;
    private String account;
    private BigDecimal amount;

    private final String method = "CreateTransaction";
    private final PaynetCheckTransactionRequestDto.Params params = new PaynetCheckTransactionRequestDto.Params(id, timestamp, account, amount);

    public PaynetCheckTransactionRequestDto(
            String id,
            Timestamp timestamp,
            String transactionAccount,
            BigDecimal transactionAmountToMerchant) {

        this.id = Long.parseLong(id);
        this.timestamp = timestamp;
        this.account = transactionAccount;
        this.amount = transactionAmountToMerchant;
    }

    static class Params{
        private Long id;
        private Timestamp timestamp;
        private PaynetCheckTransactionRequestDto.Params.Account account;
        private BigDecimal amount;

        Params(Long id, Timestamp timestamp, String account, BigDecimal amount) {
            this.id = id;
            this.timestamp = timestamp;
            this.account = new PaynetCheckTransactionRequestDto.Params.Account(account);
            this.amount = amount;
        }

        static class Account{
            private String account;

            Account(String account) {
                this.account = account;
            }
        }
    }

}
