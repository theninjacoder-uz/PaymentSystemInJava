package com.example.paymentsysteminjava.service.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

//    private final String IMAGE_PATH = "D:\\PDP\\OnsiteLessons\\PaymentSystemInJava\\src\\main\\resources\\static\\img\\";
    private final String IMAGE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\";
    public String saveFile(MultipartFile file) {
        try {

            String serverImageName = UUID.randomUUID().toString();
            String contentType = Objects.requireNonNull(file.getContentType()).replace("image/", "");
            String path = IMAGE_PATH + serverImageName + "." + contentType;
            File imageFile = new File(path);
            imageFile.createNewFile();

            byte[] allBytes = file.getInputStream().readAllBytes();

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            outputStream.write(allBytes);
            return "/api/resources/static/img/" + serverImageName + "." + contentType;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FileInputStream getFile(String fileName) {
        try {
            String path = IMAGE_PATH + "\\" + fileName;
            return new FileInputStream(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
