package com.example.api.todo.controller;

import com.example.api.todo.domain.entity.TodoPartial;
import com.example.api.todo.domain.response.TodoApiResponse;
import com.example.api.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    }

    @GetMapping("")
    public List<TodoApiResponse> getTodosList() {

    }

    @PostMapping("")
    public TodoPartial createTodo() {

    }

    @PutMapping("/{todosId}")
    public TodoPartial updateTodo(@PathVariable Long todosId) {

    }


    @DeleteMapping("/{todosId}")
    public void deleteTodo(@PathVariable Long todosId){

    }

}
