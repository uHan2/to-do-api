package com.example.api.todo.controller;

import com.example.api.todo.domain.entity.TodoPartial;
import com.example.api.todo.domain.request.CreateTodoRequest;
import com.example.api.todo.domain.response.TodoApiResponse;
import com.example.api.todo.service.TodoService;
import com.example.api.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{todosId}")
    public TodoApiResponse getTodo(@PathVariable Long todosId) {
        return todoService.getTodo(todosId);
    }

    @GetMapping("")
    public List<TodoApiResponse> getTodosList(@AuthenticationPrincipal User user,
                                              @RequestParam(required = false) Integer limit,
                                              @RequestParam(required = false) Integer skip) {
        return todoService.getTodoList(user.getId(), limit, skip);
    }

    @PostMapping("")
    public TodoApiResponse createTodo(@AuthenticationPrincipal User user,
                                      @RequestBody CreateTodoRequest createTodoRequest) {
        return todoService.createTodo(user.getId(), createTodoRequest);
    }

    @PutMapping("/{todosId}")
    public TodoPartial updateTodo(@PathVariable Long todosId) {
        return todoService.updateTodo(todosId);
    }


    @DeleteMapping("/{todosId}")
    public void deleteTodo(@PathVariable Long todosId) {
        todoService.deleteTodo(todosId);
    }

}
