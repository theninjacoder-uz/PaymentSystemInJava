package com.example.paymentsysteminjava.controller.service;

import com.example.paymentsysteminjava.service.oson.OsonServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.ResultSet;

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

    @GetMapping(consumes = {MediaType.ALL_VALUE} , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getOserServiceByPage(@RequestParam("page_size") int size,
                                                  @RequestParam("page_number") int number,
                                                  @RequestParam("desc") boolean desc,
                                                  @RequestParam("property") String property
                                                  ) {
        return ResponseEntity.ok(osonServiceService.getByPage(size, number, desc , property));
    }

    @GetMapping( produces = {MediaType.APPLICATION_JSON_VALUE} ,value = "search")
    public ResponseEntity<?> searchByName(
        @RequestParam("name") String name
    ){
        return ResponseEntity.ok(osonServiceService.search(name));
    }


}
