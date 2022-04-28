package com.example.paymentsysteminjava.repository.merchant;

import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MerchantServiceRepository extends JpaRepository<MerchantServiceEntity, Long> {


    MerchantServiceEntity findByMerchantServiceId(Long id);
}
