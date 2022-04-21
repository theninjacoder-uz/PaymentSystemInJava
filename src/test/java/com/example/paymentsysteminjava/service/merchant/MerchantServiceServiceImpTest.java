package com.example.paymentsysteminjava.service.merchant;

import com.example.paymentsysteminjava.dto.payload.merchant.MerchantServiceRegisterDto;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.repository.merchant.MerchantRepository;
import com.example.paymentsysteminjava.repository.merchant.MerchantServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MerchantServiceServiceImpTest {

    private MerchantServiceServiceImp merchantServiceServiceImp;
    private MerchantServiceRepository merchantServiceRepository;
    private MerchantRepository merchantRepository;

    @BeforeEach
    void setUp() {

        merchantRepository = Mockito.mock(MerchantRepository.class);
        merchantServiceRepository = Mockito.mock(MerchantServiceRepository.class);
        merchantServiceServiceImp = new MerchantServiceServiceImp(
                merchantServiceRepository, merchantRepository
        );
    }

    @Test
    public void testAdd() {
        MerchantServiceRegisterDto merchantServiceRegisterDto = new MerchantServiceRegisterDto("name", 1L, 1L);
        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setId(1L);
        merchantEntity.setName("name");
        merchantEntity.setUsername("username");
        merchantEntity.setPassword("password");
        merchantEntity.setSecretKey("secretKey");

        MerchantServiceEntity merchantServiceEntity = new MerchantServiceEntity();
        merchantServiceEntity.setId(1L);
        merchantServiceEntity.setName("name");
        merchantServiceEntity.setMerchantServiceId(1L);
        merchantServiceEntity.setMerchantEntity(merchantEntity);

        when(merchantRepository.findById(1L)).thenReturn(Optional.of(merchantEntity));
        when(merchantServiceRepository.save(merchantServiceEntity)).thenReturn(merchantServiceEntity);

        Boolean result = merchantServiceServiceImp.add(merchantServiceRegisterDto);

        verify(merchantRepository.findById(1L));
        verify(merchantServiceRepository.save(merchantServiceEntity));
        verify(merchantServiceServiceImp.add(merchantServiceRegisterDto));
        Assertions.assertTrue(result);
    }

    @Test
    public void testAddMerchantNotFound() {
        MerchantServiceRegisterDto merchantServiceRegisterDto = new MerchantServiceRegisterDto("name", 1L, 1L);

        when(merchantRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> merchantServiceServiceImp.add(merchantServiceRegisterDto));
    }
}