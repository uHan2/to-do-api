package com.example;

import com.example.api.todo.domain.entity.Todo;
import com.example.api.todo.repository.TodoRepository;
import com.example.api.user.domain.entity.User;
import com.example.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class TodoApiApplication implements ApplicationRunner {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(TodoApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {

        User user = User.builder()
                .userName("testUser1")
                .password(passwordEncoder.encode("testPw"))
                .role("ROLE_USER") // 최초 가입시 USER 로 설정
                .delYn("N")
                .build();

        User user2 = User.builder()
                .userName("testUser2")
                .password(passwordEncoder.encode("testPw"))
                .role("ROLE_USER") // 최초 가입시 USER 로 설정
                .delYn("N")
                .build();


        userRepository.saveAll(Arrays.asList(user, user2));

        Todo testTodo1 = Todo.builder()
                .name("testTodo1 for testUser1")
                .user(user)
                .build();

        Todo testTodo2 = Todo.builder()
                .name("testTodo2 for testUser1")
                .user(user)
                .build();

        Todo testTodo3 = Todo.builder()
                .name("testTodo3 for testUser2")
                .user(user2)
                .build();

        todoRepository.saveAll(Arrays.asList(testTodo1, testTodo2, testTodo3));
    }
}
