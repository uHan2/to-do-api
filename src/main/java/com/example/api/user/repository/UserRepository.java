package com.example.api.user.repository;

import com.example.api.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository of User Entity
 *
 * @author 이상진
 * @since 2022.02.21
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

}
