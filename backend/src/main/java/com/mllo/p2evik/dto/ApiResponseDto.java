package com.mllo.p2evik.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object for API responses.
 *
 * <p>This class is used to encapsulate the data returned by an API. It includes
 * fields for indicating success, a message, and the actual response data. The
 * response data is of a generic type that extends {@link BaseDto}.</p>
 *
 * @param <T> the type of the response data, which must extend {@link BaseDto}
 */
@Data
@Builder
public class ApiResponseDto<T extends BaseDto> {
    private boolean isSuccess;
    private String message;
    private T response;
}