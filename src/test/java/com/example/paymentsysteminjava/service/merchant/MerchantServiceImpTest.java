package com.example.paymentsysteminjava.service.merchant;

import com.example.paymentsysteminjava.dto.payload.merchant.MerchantRegisterDto;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.repository.merchant.MerchantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class MerchantServiceImpTest {

    @InjectMocks
    private MerchantServiceImp merchantServiceImp;

    @Mock
    private MerchantRepository merchantRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        merchantRepository = Mockito.mock(MerchantRepository.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        merchantServiceImp = new MerchantServiceImp(merchantRepository, modelMapper , passwordEncoder);
    }

    @Test
    public void testAdd() {
        MerchantRegisterDto merchantRegisterDto = new MerchantRegisterDto();
        merchantRegisterDto.setUsername("username");
        merchantRegisterDto.setPassword("password");
        merchantRegisterDto.setName("name");
        merchantRegisterDto.setSecretKey("secretKey");

        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setUsername("username");
        merchantEntity.setPassword("password");
        merchantEntity.setName("name");
        merchantEntity.setSecretKey("secretKey");

        when(modelMapper.map(merchantRegisterDto, MerchantEntity.class)).thenReturn(merchantEntity);
        when(passwordEncoder.encode(merchantEntity.getPassword())).thenReturn(merchantEntity.getPassword());

        when(merchantRepository.save(merchantEntity)).thenReturn(merchantEntity);

        assertTrue(merchantServiceImp.add(merchantRegisterDto));
    }

    @Test
    public void testAddPasswordIsEncoded() {
        MerchantRegisterDto merchantRegisterDto = new MerchantRegisterDto();
        merchantRegisterDto.setPassword("password");

        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setPassword("password");

        when(modelMapper.map(merchantRegisterDto, MerchantEntity.class)).thenReturn(merchantEntity);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        merchantServiceImp.add(merchantRegisterDto);

        assertEquals("encodedPassword", merchantEntity.getPassword());
    }
}
