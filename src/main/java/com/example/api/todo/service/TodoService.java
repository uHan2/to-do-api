package com.example.api.todo.service;

import com.example.api.todo.domain.response.TodoApiResponse;

import java.util.List;

public interface TodoService {
    TodoApiResponse getTodo(Long todosId);

    List<TodoApiResponse> getTodoList(String userName, Integer limit, Integer skip);
}
