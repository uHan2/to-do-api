package com.example.api.todo.service;

import com.example.api.todo.domain.entity.TodoPartial;
import com.example.api.todo.domain.request.CreateTodoRequest;
import com.example.api.todo.domain.response.TodoApiResponse;

import java.util.List;

public interface TodoService {
    TodoApiResponse getTodo(Long todosId);

    List<TodoApiResponse> getTodoList(Long userId, Integer limit, Integer skip);

    TodoPartial createTodo(Long userId, CreateTodoRequest createTodoRequest);
}
