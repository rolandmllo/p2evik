package com.mllo.p2evik.service.impl;

import com.mllo.p2evik.dto.*;
import com.mllo.p2evik.entity.Role;
import com.mllo.p2evik.entity.User;
import com.mllo.p2evik.exception.RoleNotFoundException;
import com.mllo.p2evik.exception.UserAlreadyExistsException;
import com.mllo.p2evik.security.UserDetailsImpl;
import com.mllo.p2evik.security.jwt.JwtUtils;
import com.mllo.p2evik.service.AuthService;
import com.mllo.p2evik.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<ApiResponseDto<BaseDto>> signUpUser(SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
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

    private User createUser(SignUpRequestDto signUpRequestDto) throws RoleNotFoundException {
        return User.builder()
                .email(signUpRequestDto.getEmail())
                .name(signUpRequestDto.getUserName())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .roles(determineRoles(signUpRequestDto.getRoles()))
                .build();
    }

    private Set<Role> determineRoles(Set<String> strRoles) throws RoleNotFoundException {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(roleService.getInstance("user"));
        } else {
            for (String role : strRoles) {
                roles.add(roleService.getInstance(role));
            }
        }
        return roles;
    }

    @Override
    public ResponseEntity<ApiResponseDto<BaseDto>> signInUser(SignInRequestDto signInRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(), signInRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        SignInResponseDto signInResponseDto = SignInResponseDto.builder()
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .id(userDetails.getId())
                .token(jwt)
                .type("Bearer")
                .roles(roles)
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