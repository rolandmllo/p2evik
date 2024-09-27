package com.mllo.p2evik.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for error messages.
 */
@Data
public class ErrorMessagesDto implements IDto {

    private List<String> messages;

    public void add(String message) {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

}
