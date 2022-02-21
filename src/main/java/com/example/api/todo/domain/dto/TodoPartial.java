package com.example.api.todo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Partial Info of To-do
 *
 * @author 이상진
 * @since 2022.02.21
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoPartial {

    private String name;

    private Boolean completed;

}
