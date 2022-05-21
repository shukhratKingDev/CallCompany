package com.company.callcompany.entity;

import com.company.callcompany.entity.enums.PersonType;
import com.company.callcompany.entity.templates.CommonUniqueFields;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tariffs  extends CommonUniqueFields {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private PersonType personType;
    private double mb;
    private long minuteIn;
    private long minuteOut;
    private long sms;
    @Column(nullable = false)
    private boolean archived;
    @Column(nullable = false)
    private Integer duration;
    private Timestamp expirationDate;
    private double afterExpirePrice;
    @ManyToOne
    private Company company;
}



