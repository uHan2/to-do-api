package com.example.api.file.service.impl;

import com.example.api.file.domain.entity.File;
import com.example.api.file.repository.FileRepository;
import com.example.api.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public String upload(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        fileRepository.save(new File(fileName, file.getContentType(), file.getBytes()));
        return fileName;
    }

}
