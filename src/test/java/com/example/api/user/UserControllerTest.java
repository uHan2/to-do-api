package com.example.api.user;

import com.example.api.user.domain.dto.request.UserSignInRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@DisplayName("UserController 테스트")
public class UserControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Test
    void 로그인_테스트_성공() throws Exception {
        // given
        UserSignInRequest userSignInRequest = new UserSignInRequest("testUser1", "testPw");

        // when
        RequestBuilder signIn = MockMvcRequestBuilders.post("/user/sign-in")
                .content(objectMapper.writeValueAsString(userSignInRequest))
                .contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(signIn).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void 로그인_테스트_아이디_실패() throws Exception {
        // given
        UserSignInRequest userSignInRequest = new UserSignInRequest("failUser", "testPw");

        // when
        RequestBuilder signIn = MockMvcRequestBuilders.post("/user/sign-in")
                .content(objectMapper.writeValueAsString(userSignInRequest))
                .contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(signIn)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UsernameNotFoundException))
                .andExpect(result -> assertEquals("가입되지 않은 아이디 입니다.", Objects.requireNonNull(
                        result.getResolvedException()).getMessage()));;
    }

    @Test
    void 로그인_테스트_비밀번호_실패() throws Exception {
        // given
        UserSignInRequest userSignInRequest = new UserSignInRequest("testUser1", "failPw");

        // when
        RequestBuilder signIn = MockMvcRequestBuilders.post("/user/sign-in")
                .content(objectMapper.writeValueAsString(userSignInRequest))
                .contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(signIn)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UsernameNotFoundException))
                .andExpect(result -> assertEquals("잘못된 비밀번호입니다.", Objects.requireNonNull(
                        result.getResolvedException()).getMessage()));;
    }

}