package com.company.callcompany.entity;

import com.company.callcompany.entity.enums.PersonType;
import com.company.callcompany.entity.templates.CommonUniqueFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PhoneNumber extends CommonUniqueFields {
    @Column(nullable = false,updatable = false,unique = true)
    private String phoneNumber;
    @ManyToOne
    private Company company;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PersonType personType;
    private Boolean active;
    private double balance;
}
