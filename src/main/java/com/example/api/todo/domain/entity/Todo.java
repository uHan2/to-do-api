package com.example.api.todo.domain.entity;

import com.example.api.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * To-do Entity
 *
 * @author 이상진
 * @since 2022.02.21
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Boolean completed = false;

    private LocalDateTime completedAt;

//    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;

//    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Todo(String name, User user) {
        this.name = name;
        this.completed = false;
        this.user = user;
    }

    public void updateCompletedStatus() {
        this.completed = true;
        this.completedAt = LocalDateTime.now();
    }

    public void setUserForTest(User user) {
        this.user = user;
    }

}
