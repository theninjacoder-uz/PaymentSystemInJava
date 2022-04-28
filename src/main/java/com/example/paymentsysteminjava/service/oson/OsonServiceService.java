package com.example.paymentsysteminjava.service.oson;

import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.oson.OsonServiceEntity;
import com.example.paymentsysteminjava.repository.OsonServiceServiceRepository;
import com.example.paymentsysteminjava.repository.merchant.MerchantServiceRepository;
import com.example.paymentsysteminjava.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Component
public class OsonServiceService {

    private final OsonServiceServiceRepository osonServiceServiceRepository;
    private final FileStorageService fileStorageService;
    private final MerchantServiceRepository merchantServiceRepository;
    @Value("${storageUrl}")
   private String fileUrl;

    public String add(MultipartFile file, String name, Long merchantId) {

        String fileName = fileStorageService.storeFile(file);
        String imgUrl = fileUrl + "/" + fileName;

        MerchantServiceEntity merchantService = merchantServiceRepository.findById(merchantId).orElseThrow(() ->
            new RuntimeException("Merchant not found"));
        OsonServiceEntity build = OsonServiceEntity.builder()
            .name(name)
            .url(imgUrl)
            .merchantServiceEntity(merchantService).build();

        osonServiceServiceRepository.save(build);

        return "Successfully add";

    }
}