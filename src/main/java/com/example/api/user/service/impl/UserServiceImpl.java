package com.example.api.user.service.impl;

import com.example.api.user.domain.dto.request.UserSignInRequest;
import com.example.api.user.domain.entity.User;
import com.example.api.user.repository.UserRepository;
import com.example.api.user.service.UserService;
import com.example.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public String signIn(UserSignInRequest userSignInRequest) {

        User user = userRepository.findByUserName(userSignInRequest.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("가입되지 않은 아이디 입니다."));

        if (!passwordEncoder.matches(userSignInRequest.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException("잘못된 비밀번호입니다.");
        }

        return jwtTokenProvider.createToken(user.getUsername(), user.getRole());
    }

}
