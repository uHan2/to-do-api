package com.example.api.todo.service.impl;

import com.example.api.todo.domain.entity.Todo;
import com.example.api.todo.domain.entity.TodoPartial;
import com.example.api.todo.domain.request.CreateTodoRequest;
import com.example.api.todo.domain.request.SkipLimitRequest;
import com.example.api.todo.domain.response.TodoApiResponse;
import com.example.api.todo.repository.TodoCustomRepository;
import com.example.api.todo.repository.TodoRepository;
import com.example.api.todo.service.TodoService;
import com.example.api.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoCustomRepository todoCustomRepository;

    @Override
    public TodoApiResponse getTodo(Long todosId) {
        Todo todo = todoRepository.findById(todosId).orElseThrow(
                () -> new ServiceException("존재하지 않는 Todo 입니다."));

        return new TodoApiResponse(todo.getId(), todo.getName(), todo.getCompleted(), todo.getCompletedAt(),
                todo.getCreatedAt(), todo.getUpdatedAt());

    }

    @Override
    public List<TodoApiResponse> getTodoList(Long userId, Integer limit, Integer skip) {

        if (Objects.isNull(limit)) limit = 10;
        if (Objects.isNull(skip)) skip = 0;

        Pageable pageable = new SkipLimitRequest(limit, skip);

        return todoCustomRepository.findAllByUserId(userId, pageable);
    }

    @Override
    @Transactional
    public TodoPartial createTodo(Long userId, CreateTodoRequest createTodoRequest) {

        todoRepository.save(Todo.builder()
                .name(createTodoRequest.getName())
                .completed(createTodoRequest.getCompleted())
                .user(new User(userId))
                .build());

        return new TodoPartial(createTodoRequest.getName(), createTodoRequest.getCompleted());
    }

}
