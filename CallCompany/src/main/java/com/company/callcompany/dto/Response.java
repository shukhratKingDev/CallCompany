package com.company.callcompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Response {
    private String message;
    private boolean success;
    private String token;

    public Response(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
