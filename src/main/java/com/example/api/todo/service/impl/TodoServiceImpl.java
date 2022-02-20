package com.example.api.todo.service.impl;

import com.example.api.todo.domain.entity.Todo;
import com.example.api.todo.domain.request.SkipLimitRequest;
import com.example.api.todo.domain.response.TodoApiResponse;
import com.example.api.todo.repository.TodoCustomRepository;
import com.example.api.todo.repository.TodoRepository;
import com.example.api.todo.service.TodoService;
import com.example.api.user.domain.entity.User;
import com.example.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoCustomRepository todoCustomRepository;
    private final UserRepository userRepository;

    @Override
    public TodoApiResponse getTodo(Long todosId) {
        Todo todo = todoRepository.findById(todosId).orElseThrow(
                () -> new ServiceException("존재하지 않는 Todo 입니다."));

        return new TodoApiResponse(todo.getId(), todo.getName(), todo.getCompleted(), todo.getCompletedAt(), todo.getCreatedAt(), todo.getUpdatedAt());

    }

    @Override
    public List<TodoApiResponse> getTodoList(String userName, Integer limit, Integer skip) {

        if(Objects.isNull(limit)) limit = 10;
        if(Objects.isNull(skip)) skip = 0;

        Pageable pageable = new SkipLimitRequest(limit, skip);

        User user = userRepository.findByUserName(userName).orElseThrow(() -> new ServiceException("존재하지 않는 회원입니다."));

        return todoCustomRepository.findAllByUserId(user.getId(), pageable);
    }

}
