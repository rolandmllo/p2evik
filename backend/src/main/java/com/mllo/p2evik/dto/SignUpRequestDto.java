package com.mllo.p2evik.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


/**
 * Data Transfer Object for user sign-up requests.
 *
 * <p>This class is used to encapsulate the data required for a user to sign up.
 * It includes fields for username, email, password, and roles. Validation
 * constraints are applied to ensure the data is in the correct format.</p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto implements BaseDto {
    @NotBlank(message = "Username is required!")
    @Size(min= 3, message = "Username must have least 3 characters!")
    @Size(max= 20, message = "Username can have have almost 20 characters!")
    private String userName;

    @Email(message = "Email is not in valid format!")
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Password is required!")
    @Size(min = 8, message = "Password must have least 8 characters!")
    @Size(max = 20, message = "Password can have have almost 20 characters!")
    private String password;

    private Set<String> roles;

}