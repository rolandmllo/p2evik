package com.mllo.p2evik;

import com.mllo.p2evik.controller.AuthController;
import com.mllo.p2evik.dto.ApiResponseDto;
import com.mllo.p2evik.dto.BaseDto;
import com.mllo.p2evik.dto.SignInRequestDto;
import com.mllo.p2evik.dto.SignUpRequestDto;
import com.mllo.p2evik.exception.RoleNotFoundException;
import com.mllo.p2evik.exception.UserAlreadyExistsException;
import com.mllo.p2evik.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private AuthenticationManager manager;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUserSuccessfully() throws UserAlreadyExistsException, RoleNotFoundException {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        ApiResponseDto<BaseDto> responseDto = ApiResponseDto.builder().isSuccess(true).message("User registered successfully").response(null).build();
        when(authService.signUpUser(any(SignUpRequestDto.class))).thenReturn(ResponseEntity.ok(responseDto));

        ResponseEntity<ApiResponseDto<BaseDto>> response = authController.registerUser(signUpRequestDto);

        assertEquals(ResponseEntity.ok(responseDto), response);
        verify(authService, times(1)).signUpUser(any(SignUpRequestDto.class));
    }

    @Test
    void registerUserThrowsUserAlreadyExistsException() throws UserAlreadyExistsException, RoleNotFoundException {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        when(authService.signUpUser(any(SignUpRequestDto.class))).thenThrow(new UserAlreadyExistsException("User already exists"));

        try {
            authController.registerUser(signUpRequestDto);
        } catch (UserAlreadyExistsException e) {
            assertEquals("User already exists", e.getMessage());
        }
    }

    @Test
    void registerUserThrowsRoleNotFoundException() throws UserAlreadyExistsException, RoleNotFoundException {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        when(authService.signUpUser(any(SignUpRequestDto.class))).thenThrow(new RoleNotFoundException("Role not found"));

        try {
            authController.registerUser(signUpRequestDto);
        } catch (RoleNotFoundException e) {
            assertEquals("Role not found", e.getMessage());
        }
    }

    @Test
    void loginSuccessfully() {
        SignInRequestDto signInRequestDto = new SignInRequestDto();
        signInRequestDto.setEmail("test@example.com");
        signInRequestDto.setPassword("password");
        Authentication authentication = mock(Authentication.class);
        when(manager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authService.generateToken(authentication)).thenReturn("token");

        String token = authController.login(signInRequestDto);

        assertEquals("token", token);
        verify(manager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(authService, times(1)).generateToken(authentication);
    }
}