package com.company.callcompany.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TariffDto {
    @NotNull
    private String name;
    @NotNull
    private double price;
    @NotNull
    private String description;
    @NotNull
    private boolean isJuridical;
    @NotNull
    private double mb;
    @NotNull
    private long minuteIn;
    @NotNull
    private long minuteOut;
    @NotNull
    private long sms;
    @NotNull
    private Integer duration;
    @NotNull
    private double afterExpireDate;
}
