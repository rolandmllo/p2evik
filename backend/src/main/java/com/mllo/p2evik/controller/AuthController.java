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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<BaseDto>> registerUser(@RequestBody @Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUpUser(signUpRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<BaseDto>> signInUser(@RequestBody @Valid SignInRequestDto signInRequestDto) {
        return authService.signInUser(signInRequestDto);
    }

}