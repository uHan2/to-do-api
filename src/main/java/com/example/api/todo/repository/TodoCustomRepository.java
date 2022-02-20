package com.example.api.todo.repository;

import com.example.api.todo.domain.response.TodoApiResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

import static com.example.api.todo.domain.entity.QTodo.todo;

@Repository
@RequiredArgsConstructor
public class TodoCustomRepository {

    private final EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<TodoApiResponse> findAllByUserId(Long userId, Pageable pageable) {
        return queryFactory.select(Projections.bean(TodoApiResponse.class
                        , todo.id
                        , todo.name
                        , todo.completed
                        , todo.completedAt
                        , todo.createdAt
                        , todo.updatedAt))
                .from(todo)
                .where(todo.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(todo.id.asc())
                .fetch();
    }

}
