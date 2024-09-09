package com.mllo.p2evik.service.impl;

import com.mllo.p2evik.dto.*;
import com.mllo.p2evik.entity.Role;
import com.mllo.p2evik.entity.User;
import com.mllo.p2evik.exception.UserAlreadyExistsException;
import com.mllo.p2evik.service.AuthService;
import com.mllo.p2evik.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Lazy
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder encoder;
    private @Value("${jwt.validity}") long validity;

    public Authentication authenticate(SignInRequestDto signInRequestDto) {


        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.getEmail(), signInRequestDto.getPassword()));
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        Instant expiryDate = Instant.from(Instant.ofEpochMilli(now.toEpochMilli() + validity));

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expiryDate)
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    @Override
    public ResponseEntity<ApiResponseDto<BaseDto>> signUpUser(SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException {
        if (userService.existsByEmail(signUpRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided email already exists. Try sign in or provide another email.");
        }
        if (userService.existsByUsername(signUpRequestDto.getUserName())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided username already exists. Try sign in or provide another username.");
        }

        User user = createUser(signUpRequestDto);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("User account has been successfully created!")
                        .build()
        );
    }

    private User createUser(SignUpRequestDto signUpRequestDto) {
        return User.builder()
                .email(signUpRequestDto.getEmail())
                .name(signUpRequestDto.getUserName())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .roles(determineRoles())
                .build();
    }

    private Set<Role> determineRoles() {
        return Set.of();
    }

    @Override
    public ResponseEntity<ApiResponseDto<BaseDto>> signInUser(SignInRequestDto signInRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.getEmail(), signInRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = generateToken(authentication);

        SignInResponseDto signInResponseDto = SignInResponseDto.builder()
                .email(signInRequestDto.getEmail())
                .token(jwt)
                .build();

        return ResponseEntity.ok(
                ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("Sign in successful!")
                        .response(signInResponseDto)
                        .build()
        );
    }
}