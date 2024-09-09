package com.mllo.p2evik.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Data Transfer Object for user sign-in requests.
 *
 * <p>This class is used to encapsulate the data required for a user to sign in.
 * It includes fields for email and password. Validation constraints are applied
 * to ensure the data is in the correct format.</p>
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignInRequestDto implements BaseDto {
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;

}