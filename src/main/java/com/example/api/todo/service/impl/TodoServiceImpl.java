package com.example.api.todo.service.impl;

import com.example.api.todo.domain.entity.Todo;
import com.example.api.todo.domain.dto.TodoPartial;
import com.example.api.todo.domain.dto.request.CreateTodoRequest;
import com.example.api.todo.domain.dto.request.LimitSkipRequest;
import com.example.api.todo.domain.dto.response.TodoApiResponse;
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

/**
 * Service Implements About To-do
 *
 * @author 이상진
 * @since 2022.02.21
 */
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoCustomRepository todoCustomRepository;

    /**
     * Get Single To-do Service Implements Method
     *
     * @param todosId To-do Id
     * @return To-do Response
     */
    @Override
    public TodoApiResponse getTodo(Long todosId) {
        Todo todo = todoRepository.findById(todosId).orElseThrow(
                () -> new ServiceException("존재하지 않는 Todo 입니다."));

        return new TodoApiResponse(todo);
    }

    /**
     * Get List of To-do Service Implements Method
     *
     * @param userId Current User Info - Id
     * @param limit  Limit Value of Pagination
     * @param skip   Skip Value of Pagination (Same Like Offset)
     * @return List of To-do Response
     */
    @Override
    public List<TodoApiResponse> getTodoList(Long userId, Integer limit, Integer skip) {

        if (Objects.isNull(limit)) limit = 10;
        if (Objects.isNull(skip)) skip = 0;

        Pageable pageable = new LimitSkipRequest(limit, skip);

        return todoCustomRepository.findAllByUserId(userId, pageable);
    }

    /**
     * Create To-do Service Implements Method
     *
     * @param userId            Current User Info - Id
     * @param createTodoRequest Request of Create (It has To-do Name Field)
     * @return To-do Response
     */
    @Override
    @Transactional
    public TodoApiResponse createTodo(Long userId, CreateTodoRequest createTodoRequest) {

        Todo todo = Todo.builder()
                .name(createTodoRequest.getName())
                .user(new User(userId))
                .build();

        todoRepository.save(todo);


        return new TodoApiResponse(todo);
    }

    /**
     * Update To-do Service Implements Method
     *
     * @param todosId To-do id
     * @return Partial Info of Updated To-do
     */
    @Override
    @Transactional
    public TodoPartial updateTodo(Long todosId) {

        Todo todo = todoRepository.findById(todosId).orElseThrow(() -> new ServiceException("존재하지 않는 Todo 입니다."));
        todo.updateCompletedStatus();

        return new TodoPartial(todo.getName(), todo.getCompleted());
    }

    /**
     * Delete To-do Service Implements Method
     *
     * @param todosId To-do Id
     */
    @Override
    @Transactional
    public void deleteTodo(Long todosId) {

        Todo todo = todoRepository.findById(todosId).orElseThrow(() -> new ServiceException("존재하지 않는 Todo 입니다."));

        todoRepository.delete(todo);
    }

}
