package com.example.api.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Service About File
 *
 * @author 이상진
 * @since 2022.02.21
 */
public interface FileService {

    /**
     * File Upload Service Method
     *
     * @param file File For Upload
     * @return Uploaded File Name
     */
    String upload(MultipartFile file) throws IOException;

}
