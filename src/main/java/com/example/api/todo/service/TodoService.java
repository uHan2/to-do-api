package com.example.api.todo.service;

import com.example.api.todo.domain.dto.TodoPartial;
import com.example.api.todo.domain.dto.request.CreateTodoRequest;
import com.example.api.todo.domain.dto.response.TodoApiResponse;

import java.util.List;

public interface TodoService {
    TodoApiResponse getTodo(Long todosId);

    List<TodoApiResponse> getTodoList(Long userId, Integer limit, Integer skip);

    TodoApiResponse createTodo(Long userId, CreateTodoRequest createTodoRequest);

    TodoPartial updateTodo(Long todosId);

    void deleteTodo(Long todosId);
}
