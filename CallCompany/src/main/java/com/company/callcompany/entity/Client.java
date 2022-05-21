package com.company.callcompany.entity;

import com.company.callcompany.entity.enums.PersonType;
import com.company.callcompany.entity.templates.CommonUniqueFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client extends CommonUniqueFields {
    private String firstName;
    private String lastName;
    private String address;
    @Enumerated(EnumType.STRING)
    private PersonType personType;
    private String passportNumber;
    private String givenPlace;
    private Date givenDate;
    @OneToMany(mappedBy = "client_tariff")
    private List<ClientTariff> clientTariff;
    @OneToMany(mappedBy = "client")
    private List<ClientPhoneNumbers> clientPhoneNumbers;
@OneToMany(mappedBy = "client_service")
    private List<ClientServices>clientServices;
@OneToMany(mappedBy = "client_packet")
private List<ClientPackets> packets;
}
