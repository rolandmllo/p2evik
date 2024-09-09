package com.mllo.p2evik.service;

import com.mllo.p2evik.dto.ApiResponseDto;
import com.mllo.p2evik.dto.BaseDto;
import com.mllo.p2evik.dto.SignInRequestDto;
import com.mllo.p2evik.dto.SignUpRequestDto;
import com.mllo.p2evik.exception.RoleNotFoundException;
import com.mllo.p2evik.exception.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

/**
 * Service interface for authentication-related operations.
 *
 * <p>This interface defines the contract for authentication services, including
 * user sign-up and sign-in operations. Implementations of this interface are
 * responsible for handling the business logic associated with these operations.</p>
 */
public interface AuthService {

    /**
     * Signs up a new user.
     *
     * @param signUpRequestDto the data transfer object containing the sign-up request details
     * @return a ResponseEntity containing an ApiResponseDto with the result of the sign-up operation
     * @throws UserAlreadyExistsException if a user with the given details already exists
     * @throws RoleNotFoundException if the specified role is not found
     */
    ResponseEntity<ApiResponseDto<BaseDto>> signUpUser(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException, RoleNotFoundException;

    /**
     * Signs in an existing user.
     *
     * @param signInRequestDto the data transfer object containing the sign-in request details
     * @return a ResponseEntity containing an ApiResponseDto with the result of the sign-in operation
     */
    ResponseEntity<ApiResponseDto<BaseDto>> signInUser(SignInRequestDto signInRequestDto);

    /**
     * Generates a JWT token for the given authentication.
     *
     * @param authentication the authentication object for which to generate a token
     * @return the generated JWT token
     */
    String generateToken(Authentication authentication);

    /**
     * Authenticates a user based on the given sign-in request.
     *
     * @param signInRequestDto the data transfer object containing the sign-in request details
     * @return the authentication object for the user
     */
    Authentication authenticate(SignInRequestDto signInRequestDto);

}
