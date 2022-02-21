package com.example.api.file.service.impl;

import com.example.api.file.domain.entity.File;
import com.example.api.file.repository.FileRepository;
import com.example.api.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Objects;

/**
 * Service Implements About File
 *
 * @author 이상진
 * @since 2022.02.21
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    /**
     * File Upload Service Implements Method
     *
     * @param file File For Upload
     * @return Uploaded File Name
     */
    @Override
    @Transactional
    public String upload(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        fileRepository.save(new File(fileName, file.getContentType(), file.getBytes()));
        return fileName;
    }

}
