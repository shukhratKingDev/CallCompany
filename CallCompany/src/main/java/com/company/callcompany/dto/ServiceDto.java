package com.company.callcompany.dto;

import com.company.callcompany.entity.USSD_Codes;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ServiceDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String  type;
    @NotNull
    private double price;

    private USSD_Codes ussdCodes;
}
