package com.example.api.user.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignInRequest {

    private String userName;

    private String password;

}
