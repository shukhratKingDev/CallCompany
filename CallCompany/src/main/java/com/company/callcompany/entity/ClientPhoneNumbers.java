package com.company.callcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientPhoneNumbers  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private PhoneNumber phoneNumber;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Tariffs tariffs;
    private double balance;
    private boolean enabled;
}
