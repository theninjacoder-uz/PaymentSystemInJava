package com.example.paymentsysteminjava.controller.oson;

import com.example.paymentsysteminjava.dto.response.BaseApiResponse;
import com.example.paymentsysteminjava.service.oson.OsonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.*;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/oson")
public class OsonController {

    private final OsonService osonService;

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping(value = "/add/{id}",
            consumes = {MULTIPART_FORM_DATA_VALUE, MULTIPART_MIXED_VALUE})
    public ResponseEntity<?> add(@PathVariable("id") Long merchantServId,
                                 @RequestParam(name = "file") MultipartFile file) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(osonService.add(merchantServId, file));
    }

    @GetMapping(value = "/get/list")
    public ResponseEntity<?> getList(@RequestParam("page") Integer page,
                                  @RequestParam("size") Integer size,
                                  @RequestParam("sortByName") String sortByName)
    {
        return ResponseEntity.ok(osonService.getList(page, size, sortByName));
    }
}
