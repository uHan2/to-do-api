package com.example.api.todo.service;

import com.example.api.todo.domain.dto.TodoPartial;
import com.example.api.todo.domain.dto.request.CreateTodoRequest;
import com.example.api.todo.domain.dto.response.TodoApiResponse;

import java.util.List;

/**
 * Service About To-do
 *
 * @author 이상진
 * @since 2022.02.21
 */
public interface TodoService {

    /**
     * Get Single To-do Service Implements Method
     *
     * @param todosId To-do Id
     * @return To-do Response
     */
    TodoApiResponse getTodo(Long todosId);

    /**
     * Get List of To-do Service Method
     *
     * @param userId Current User Info - Id
     * @param limit  Limit Value of Pagination
     * @param skip   Skip Value of Pagination (Same Like Offset)
     * @return List of To-do Response
     */
    List<TodoApiResponse> getTodoList(Long userId, Integer limit, Integer skip);

    /**
     * Create To-do Service Method
     *
     * @param userId            Current User Info - Id
     * @param createTodoRequest Request of Create (It has To-do Name Field)
     * @return To-do Response
     */
    TodoApiResponse createTodo(Long userId, CreateTodoRequest createTodoRequest);

    /**
     * Update To-do Service Method
     *
     * @param todosId To-do id
     * @return Partial Info of Updated To-do
     */
    TodoPartial updateTodo(Long todosId);

    /**
     * Delete To-do Service Method
     *
     * @param todosId To-do Id
     */
    void deleteTodo(Long todosId);
}
