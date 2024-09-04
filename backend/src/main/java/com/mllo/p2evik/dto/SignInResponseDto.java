package com.mllo.p2evik.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object for user sign-in responses.
 *
 * <p>This class is used to encapsulate the data returned when a user signs in.
 * It includes fields for username, email, token, user ID, type, and roles.</p>
 */
@Data
@Builder
public class SignInResponseDto implements BaseDto {
    private String username;
    private String email;
    private String token;
    private Long id;
    private String type;
    private List<String> roles;
}
