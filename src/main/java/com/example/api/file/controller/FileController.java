package com.example.api.file.controller;

import com.example.api.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller about File
 *
 * @author 이상진
 * @since 2022.02.21
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    /**
     * File Upload Controller Method
     *
     * @param file File For Upload
     * @return Uploaded File Name
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        return fileService.upload(file);
    }

}
