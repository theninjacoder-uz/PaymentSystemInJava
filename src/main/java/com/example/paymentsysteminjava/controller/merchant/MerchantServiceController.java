package com.example.paymentsysteminjava.controller.merchant;

import com.example.paymentsysteminjava.dto.merchant.MerchantRegisterDto;
import com.example.paymentsysteminjava.dto.merchant.MerchantServiceRegisterDto;
import com.example.paymentsysteminjava.dto.response.BaseApiResponse;
import com.example.paymentsysteminjava.service.merchant.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant_service")
@RequiredArgsConstructor
public class MerchantServiceController {

    @Qualifier("merchantServiceServiceImp")
    private final MerchantService<MerchantServiceRegisterDto> merchantService;

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody MerchantServiceRegisterDto merchantServiceRegisterDto) {

        Boolean add = merchantService.add(merchantServiceRegisterDto);

        if (add)
            return ResponseEntity.ok().body(new BaseApiResponse(1, "Created", true));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseApiResponse(0, "creation error", false));
    }
}
