package com.example.api.todo;

import com.example.api.todo.domain.dto.request.CreateTodoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@DisplayName("TodoController 테스트")
public class TodoControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;


    @Test
    @WithMockUser
    void todo_하나_가져오기_테스트_성공() throws Exception {
        // given

        // when
        RequestBuilder getTodo = MockMvcRequestBuilders.get("/todos/3");

        // then
        mockMvc.perform(getTodo).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void todo_하나_가져오기_테스트_실패() throws Exception {
        // given

        // when
        RequestBuilder getTodo = MockMvcRequestBuilders.get("/todos/999");

        // then
        mockMvc.perform(getTodo)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ServiceException))
                .andExpect(result -> assertEquals("존재하지 않는 Todo 입니다.", Objects.requireNonNull(
                        result.getResolvedException()).getMessage()));
    }

    @Test
    @WithUserDetails(value = "testUser1")
    void todo_리스트_가져오기_테스트_성공() throws Exception {
        // given

        // when
        RequestBuilder getTodoList = MockMvcRequestBuilders.get("/todos");

        // then
        mockMvc.perform(getTodoList).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithUserDetails(value = "testUser1")
    void todo_생성_테스트_성공() throws Exception {
        // given
        CreateTodoRequest createTodoRequest = new CreateTodoRequest("create todo test");

        // when
        RequestBuilder createTodo = MockMvcRequestBuilders.post("/todos")
                .content(objectMapper.writeValueAsBytes(createTodoRequest))
                .contentType(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(createTodo).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void todo_업데이트_테스트_실패() throws Exception {
        // given

        // when
        RequestBuilder updateTodo = MockMvcRequestBuilders.put("/todos/3");

        // then
        mockMvc.perform(updateTodo).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void todo_업데이트_테스트_성공() throws Exception {
        // given

        // when
        RequestBuilder updateTodo = MockMvcRequestBuilders.put("/todos/999");

        // then
        mockMvc.perform(updateTodo)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ServiceException))
                .andExpect(result -> assertEquals("존재하지 않는 Todo 입니다.", Objects.requireNonNull(
                        result.getResolvedException()).getMessage()));
    }

    @Test
    @Transactional
    @WithMockUser
    void todo_삭제_테스트_성공() throws Exception {
        // given
        RequestBuilder getTodo = MockMvcRequestBuilders.get("/todos/3");

        // when
        RequestBuilder deleteTodo = MockMvcRequestBuilders.delete("/todos/3");

        // then
        mockMvc.perform(getTodo).andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(deleteTodo);
        mockMvc.perform(getTodo).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Transactional
    @WithMockUser
    void todo_삭제_테스트_실패() throws Exception {
        // given

        // when
        RequestBuilder deleteTodo = MockMvcRequestBuilders.delete("/todos/999");

        // then
        mockMvc.perform(deleteTodo)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ServiceException))
                .andExpect(result -> assertEquals("존재하지 않는 Todo 입니다.", Objects.requireNonNull(
                        result.getResolvedException()).getMessage()));

    }


}
