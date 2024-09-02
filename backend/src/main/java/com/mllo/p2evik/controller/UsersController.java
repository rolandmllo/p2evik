package com.mllo.p2evik.controller;

import com.mllo.p2evik.entity.User;
import com.mllo.p2evik.exception.UserNotFoundException;
import com.mllo.p2evik.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UsersController {
    private final UserRepository repository;


    @Tag(name = "GetAll", description = "Get all users")
    @Operation(summary = "Get all users", description = "Get all users as a list")
    @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = User.class)) })
    @ApiResponse(responseCode = "404", description = "User not found",
                content = @Content(mediaType = "application/json"))
    @GetMapping("/users")
    public List<User> getAll() {
        log.info("Getting all users");
        return repository.findAll();
    }


    @Tag(name = "GetUser", description = "Get user by id")
    @Parameter(name = "id", description = "User id", required = true)
    @GetMapping("/users/{id}")
    public User getPerson(@PathVariable Long id) {
        log.info("Getting user with id: {}", id);

        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
