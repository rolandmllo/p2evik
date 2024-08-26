package com.mllo.p2evik.controller;

import com.mllo.p2evik.entity.User;
import com.mllo.p2evik.exception.UserNotFoundException;
import com.mllo.p2evik.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UserRepository repository;

    @GetMapping("/users")
    public List<User> getAll() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getPerson(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id)
        );
    }
}
