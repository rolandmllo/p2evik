package com.mllo.p2evik.controller;

import com.mllo.p2evik.dto.ApiResponseDto;
import com.mllo.p2evik.dto.BaseDto;
import com.mllo.p2evik.dto.SignInRequestDto;
import com.mllo.p2evik.dto.SignUpRequestDto;
import com.mllo.p2evik.exception.RoleNotFoundException;
import com.mllo.p2evik.exception.UserAlreadyExistsException;
import com.mllo.p2evik.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin("*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<BaseDto>> registerUser(
            @RequestBody @Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        log.debug("User registration: {}", signUpRequestDto.getEmail());
        return authService.signUpUser(signUpRequestDto);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody SignInRequestDto signInRequestDto) {
        Authentication auth = authService.authenticate(signInRequestDto);
        String token = authService.generateToken(auth);
        log.info("JWT token: {}", token);
        return token;
    }
}