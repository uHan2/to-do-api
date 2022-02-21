package com.example.api.user.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request Object For Sign In
 *
 * @author 이상진
 * @since 2022.02.21
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInRequest {

    private String userName;

    private String password;

}
