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
    public void run(ApplicationArguments args) throws Exception {

        User user = User.builder()
                .userName("testUser")
                .password(passwordEncoder.encode("testPw"))
                .role("ROLE_USER") // 최초 가입시 USER 로 설정
                .delYn("N")
                .build();

        userRepository.save(user);

        todoRepository.save(Todo.builder()
                .name("testTodo1")
                .completed(false)
                .user(user)
                .build());

        todoRepository.save(Todo.builder()
                .name("testTodo2")
                .completed(false)
                .user(user)
                .build());

    }
}
