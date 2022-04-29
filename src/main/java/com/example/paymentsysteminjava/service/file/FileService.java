package com.example.paymentsysteminjava.service.file;


import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.logging.FileHandler;

@Service
public class FileService {

    public boolean saveFile(MultipartFile file){
        file.getSize();
        file.getOriginalFilename();
        file.getResource();
    }
}
