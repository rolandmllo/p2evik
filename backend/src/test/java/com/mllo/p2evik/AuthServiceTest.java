package com.mllo.p2evik;

import com.mllo.p2evik.dto.*;
import com.mllo.p2evik.entity.User;
import com.mllo.p2evik.exception.UserAlreadyExistsException;
import com.mllo.p2evik.service.UserService;
import com.mllo.p2evik.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtEncoder jwtEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUpUser_Success() throws UserAlreadyExistsException {
        // Arrange
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setEmail("test@example.com");
        signUpRequestDto.setUserName("testuser");
        signUpRequestDto.setPassword("password");

        when(userService.existsByEmail(signUpRequestDto.getEmail())).thenReturn(false);
        when(userService.existsByUsername(signUpRequestDto.getUserName())).thenReturn(false);
        when(passwordEncoder.encode(signUpRequestDto.getPassword())).thenReturn("encodedPassword");

        // Act
        ResponseEntity<ApiResponseDto<BaseDto>> response = authService.signUpUser(signUpRequestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User account has been successfully created!", Objects.requireNonNull(response.getBody()).getMessage());
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    void testSignUpUser_EmailAlreadyExists() {
        // Arrange
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setEmail("existing@example.com");
        signUpRequestDto.setUserName("testuser");

        when(userService.existsByEmail(signUpRequestDto.getEmail())).thenReturn(true);

        // Act & Assert
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                () -> authService.signUpUser(signUpRequestDto));

        assertEquals("Registration Failed: Provided email already exists. Try sign in or provide another email.", exception.getMessage());
        verify(userService, never()).save(any(User.class));
    }

    @Test
    void testSignInUser_Success() {
        // Arrange
        SignInRequestDto signInRequestDto = new SignInRequestDto("test@example.com", "password");


        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testuser");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtEncoder.encode(ArgumentMatchers.any())).thenReturn(mock(org.springframework.security.oauth2.jwt.Jwt.class));

        // Act
        ResponseEntity<ApiResponseDto<BaseDto>> response = authService.signInUser(signInRequestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("testuser", ((SignInResponseDto) Objects.requireNonNull(response.getBody()).getResponse()).getUsername());
    }

    @Test
    void testSignInUser_InvalidCredentials() {
        // Arrange
        SignInRequestDto signInRequestDto = new SignInRequestDto();
        signInRequestDto.setEmail("invalid@example.com");
        signInRequestDto.setPassword("wrongpassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Bad credentials"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> authService.signInUser(signInRequestDto));
    }
}