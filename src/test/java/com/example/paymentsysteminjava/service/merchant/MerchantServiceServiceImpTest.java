package com.example.paymentsysteminjava.service.merchant;

import com.example.paymentsysteminjava.dto.payload.merchant.MerchantServiceRegisterDto;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.repository.merchant.MerchantRepository;
import com.example.paymentsysteminjava.repository.merchant.MerchantServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class MerchantServiceServiceImpTest {

    @InjectMocks
    private MerchantServiceServiceImp merchantServiceServiceImp;

    @Mock
    private MerchantServiceRepository merchantServiceRepository;

    @Mock
    private MerchantRepository merchantRepository;

    @BeforeEach
    void setUp() {
        merchantServiceRepository = Mockito.mock(MerchantServiceRepository.class);
        merchantRepository = Mockito.mock(MerchantRepository.class);
        merchantServiceServiceImp = new MerchantServiceServiceImp(merchantServiceRepository, merchantRepository);
    }

    @Test
    public void testAdd() {
        MerchantServiceRegisterDto merchantServiceRegisterDto = new MerchantServiceRegisterDto("name", 1L, 1L);
        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setId(1L);
        Optional<MerchantEntity> optionalMerchant = Optional.of(merchantEntity);

        when(merchantRepository.findById(merchantServiceRegisterDto.getMerchantId())).thenReturn(optionalMerchant);

        merchantServiceServiceImp.add(merchantServiceRegisterDto);

        Mockito.verify(merchantRepository, times(1)).findById(merchantServiceRegisterDto.getMerchantId());
    }

    @Test
    public void testAddMerchantNotFound() {
        MerchantServiceRegisterDto merchantServiceRegisterDto = new MerchantServiceRegisterDto("name", 1L, 1L);
        when(merchantRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> merchantServiceServiceImp.add(merchantServiceRegisterDto));
    }
}
