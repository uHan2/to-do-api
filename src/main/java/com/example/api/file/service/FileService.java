package com.example.api.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String upload(MultipartFile multipartFile) throws IOException;

}
