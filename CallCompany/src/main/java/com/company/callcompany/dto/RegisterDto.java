package com.company.callcompany.dto;

import com.company.callcompany.entity.Company;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class RegisterDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Size(min = 7)
    private String username;
    @NotNull
    @Size(min = 7)
    private String password;
    @NotNull
    private Company company;
}
