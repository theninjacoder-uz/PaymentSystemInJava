package com.example.paymentsysteminjava.controller.service;

import com.example.paymentsysteminjava.service.oson.OsonServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/oson-service")
@RequiredArgsConstructor
public class OsonServiceController {

    private final OsonServiceService osonServiceService;

    @PostMapping()
    public ResponseEntity<?> addOsonService(
        @RequestParam("img") MultipartFile file,
        @RequestParam("name") String name,
        @RequestParam("merchant_id") Long merchantId
    ) {

        return ResponseEntity.ok(osonServiceService.add(file, name, merchantId));
    }

}
