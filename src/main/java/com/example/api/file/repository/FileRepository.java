package com.example.api.file.repository;

import com.example.api.file.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository About File
 *
 * @author 이상진
 * @since 2022.02.21
 */
public interface FileRepository extends JpaRepository<File, Long> {
}
