package com.example.api.user.controller;

import com.example.api.user.domain.request.UserSignInRequest;
import com.example.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인
    @PostMapping("/sign-in")
    public String signIn(@RequestBody UserSignInRequest userSignInRequest) {
        return userService.signIn(userSignInRequest);
    }

}
