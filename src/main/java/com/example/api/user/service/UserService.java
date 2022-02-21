package com.example.api.user.service;

import com.example.api.user.domain.dto.request.UserSignInRequest;

public interface UserService {
    String signIn(UserSignInRequest userSignInRequest);
}
