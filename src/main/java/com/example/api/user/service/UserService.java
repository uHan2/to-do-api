package com.example.api.user.service;

import com.example.api.user.domain.dto.request.UserSignInRequest;

/**
 * Service Implements About To-do
 *
 * @author 이상진
 * @since 2022.02.21
 */
public interface UserService {

    /**
     * User Sign In Service Method
     *
     * @param userSignInRequest Class For Sign In (It Has Username, password Field)
     * @return JWT Token
     */
    String signIn(UserSignInRequest userSignInRequest);
}
