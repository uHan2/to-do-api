package com.example.api.user.service;

import com.example.api.user.domain.request.UserSignInRequest;

public interface UserService {

    String signIn(UserSignInRequest userSignInRequest);

}
