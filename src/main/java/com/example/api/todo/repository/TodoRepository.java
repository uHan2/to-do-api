package com.example.api.todo.repository;

import com.example.api.todo.domain.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository About To-do
 *
 * @author 이상진
 * @since 2022.02.21
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
