package com.mllo.p2evik.controller.advice;

import com.mllo.p2evik.dto.ApiResponseDto;
import com.mllo.p2evik.dto.IDto;
import com.mllo.p2evik.dto.ErrorMessagesDto;
import com.mllo.p2evik.exception.ResourceNotFoundException;
import com.mllo.p2evik.exception.RoleNotFoundException;
import com.mllo.p2evik.exception.UserAlreadyExistsException;
import com.mllo.p2evik.exception.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Log4j2
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String resourceNotFoundHandler(ResourceNotFoundException ex) {
        log.info("Resource not found: {}", ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String personNotFoundHandler(UserNotFoundException ex) {
        log.info("User not found: {}", ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<IDto>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        var errorMessage = new ErrorMessagesDto();

        exception.getBindingResult().getFieldErrors().forEach(error -> errorMessage.add(error.getDefaultMessage()));

        return ResponseEntity
                .badRequest()
                .body(
                        ApiResponseDto.builder()
                                .isSuccess(false)
                                .message("Registration Failed: Please provide valid data.")
                                .response(errorMessage)
                                .build()
                );
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto<IDto>> userAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ApiResponseDto.builder()
                                .isSuccess(false)
                                .message(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<ApiResponseDto<IDto>> roleNotFoundExceptionHandler(RoleNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponseDto.builder()
                                .isSuccess(false)
                                .message(exception.getMessage())
                                .build()
                );
    }

}