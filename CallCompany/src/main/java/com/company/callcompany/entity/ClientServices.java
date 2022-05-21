package com.company.callcompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ClientServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Client client_service;
    @ManyToOne
    private PhoneNumber phoneNumber;
    @ManyToOne
    private Services services;
    private boolean enabled;
}
