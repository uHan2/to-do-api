package com.example.api.todo.controller;

import com.example.api.todo.domain.dto.TodoPartial;
import com.example.api.todo.domain.dto.request.CreateTodoRequest;
import com.example.api.todo.domain.dto.response.TodoApiResponse;
import com.example.api.todo.service.TodoService;
import com.example.api.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller about To-do
 *
 * @author 이상진
 * @since 2022.02.21
 */
@Slf4j
@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * Get Single To-do Controller Method
     *
     * @param todosId To-do Id
     * @return To-do Response
     */
    @GetMapping("/{todosId}")
    public TodoApiResponse getTodo(@PathVariable Long todosId) {
        return todoService.getTodo(todosId);
    }

    /**
     * Get List of To-do Controller Method
     *
     * @param user  Current User Info
     * @param limit Limit Value of Pagination
     * @param skip  Skip Value of Pagination (Same Like Offset)
     * @return List of To-do Response
     */
    @GetMapping("")
    public List<TodoApiResponse> getTodosList(@AuthenticationPrincipal User user,
                                              @RequestParam(required = false) Integer limit,
                                              @RequestParam(required = false) Integer skip) {
        return todoService.getTodoList(user.getId(), limit, skip);
    }

    /**
     * Create To-do Controller Method
     *
     * @param user              Current User Info
     * @param createTodoRequest Request of Create (It has To-do Name Field)
     * @return To-do Response
     */
    @PostMapping("")
    public TodoApiResponse createTodo(@AuthenticationPrincipal User user,
                                      @RequestBody CreateTodoRequest createTodoRequest) {
        return todoService.createTodo(user.getId(), createTodoRequest);
    }

    /**
     * Update To-do Controller Method
     *
     * @param todosId To-do id
     * @return Partial Info of Updated To-do
     */
    @PutMapping("/{todosId}")
    public TodoPartial updateTodo(@PathVariable Long todosId) {
        return todoService.updateTodo(todosId);
    }

    /**
     * Delete To-do Controller Method
     *
     * @param todosId To-do Id
     */
    @DeleteMapping("/{todosId}")
    public void deleteTodo(@PathVariable Long todosId) {
        todoService.deleteTodo(todosId);
    }

}
