package com.example.api.todo.repository;

import com.example.api.todo.domain.dto.response.TodoApiResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

import static com.example.api.todo.domain.entity.QTodo.todo;

/**
 * Query DSL Class For To-do Entity
 *
 * @author 이상진
 * @since 2022.02.21
 */
@Repository
@RequiredArgsConstructor
public class TodoCustomRepository {

    private final EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * Get List of To-do Api Response
     *
     * @param userId Current User Id
     * @param pageable Info of Pageable(Limit, Skip)
     * @return List of To-do Api Response
     */
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
