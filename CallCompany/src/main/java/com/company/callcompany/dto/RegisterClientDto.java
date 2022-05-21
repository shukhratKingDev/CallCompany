package com.company.callcompany.dto;

import com.company.callcompany.entity.Client;
import com.company.callcompany.entity.enums.PersonType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class RegisterClientDto {
    private String firstName;
    private String lastName;
    private String address;
    private boolean isJuridical;
    private String passportNumber;
    private String givenPlace;
    private Date givenDate;
    private String phoneNumber;
    private String tariff;

}
