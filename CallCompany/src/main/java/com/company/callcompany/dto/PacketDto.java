package com.company.callcompany.dto;

import com.company.callcompany.entity.USSD_Codes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacketDto {
    @NotNull
    private String name;
    @NotNull
    private double price;
    @NotNull
    private String type;
    @NotNull
    private  double amount;
    @NotNull
    private long duration;
    private boolean available;
    private USSD_Codes ussdCode;// to turn on this packet

}
