package com.example.todoapi.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String roles = "";

    private String permissions = "";

    private String delYn;

    @Builder
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
