package com.example.api.user.controller;

import com.example.api.user.domain.dto.request.UserSignInRequest;
import com.example.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller about User
 *
 * @author 이상진
 * @since 2022.02.21
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Sign In Controller Method
     *
     * @param userSignInRequest Class For Sign In (It Has Username, password Field)
     * @return JWT Token
     */
    @PostMapping("/sign-in")
    public String signIn(@RequestBody UserSignInRequest userSignInRequest) {
        return userService.signIn(userSignInRequest);
    }

}
