package com.example.paymentsysteminjava.dto.merchant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantServiceRegisterDto {
    private Long merchantId;
    private Long merchantServiceId;
}
