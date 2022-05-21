package com.company.callcompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
