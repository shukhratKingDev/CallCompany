package com.company.callcompany.dto;

import com.company.callcompany.entity.enums.ActionType;
import lombok.Data;

@Data
public class CallDto {
    private String phoneNumber;
    private String actionType;
    private long amount;
}
