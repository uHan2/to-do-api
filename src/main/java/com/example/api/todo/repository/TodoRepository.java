package com.example.api.todo.repository;

import com.example.api.todo.domain.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

//     TodoPartial getTodoById(Long id);

}
