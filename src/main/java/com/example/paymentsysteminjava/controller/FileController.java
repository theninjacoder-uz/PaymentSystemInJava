package com.example.paymentsysteminjava.controller;

import com.example.paymentsysteminjava.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resources/static/")
public class FileController {

    private final FileService fileService;

    @GetMapping(value = "img/{fileName}", produces = {
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_PNG_VALUE,
    })
    public void downloadFile(@PathVariable("fileName") String fileName,
                             HttpServletResponse response){
        int i = fileName.indexOf(".");
        String contentType = fileName.substring(i + 1);
        FileInputStream inputStream = fileService.getFile(fileName);
        response.setHeader("content-disposition", "inline; filename\"" + fileName);
        response.setContentType("image/" + contentType);

        try {
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
