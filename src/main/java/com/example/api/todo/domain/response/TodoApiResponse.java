package com.example.api.todo.domain.response;

import com.example.api.todo.domain.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoApiResponse {

    private Long id;

    private String name;

    private Boolean completed;

    private LocalDateTime completedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public TodoApiResponse(Todo todo){
        this.id = todo.getId();
        this.name = todo.getName();
        this.completed = todo.getCompleted();
        this.completedAt = todo.getCompletedAt();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
    }

}
