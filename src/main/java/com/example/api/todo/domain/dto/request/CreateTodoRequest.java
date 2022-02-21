package com.example.api.todo.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request Object For Create To-do
 *
 * @author 이상진
 * @since 2022.02.21
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTodoRequest {
    private String name;
}
