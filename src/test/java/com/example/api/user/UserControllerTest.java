package com.example.api.user;

import com.example.api.user.domain.dto.request.UserSignInRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

}