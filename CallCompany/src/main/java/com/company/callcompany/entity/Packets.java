package com.company.callcompany.entity;

import com.company.callcompany.entity.enums.ActionType;
import com.company.callcompany.entity.templates.CommonFields;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Packets extends CommonFields {
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private double price;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActionType actionType;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private long duration;
    private boolean available;
    @ManyToOne
    private Company company;
    @Column(updatable = false,nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
    @OneToOne
    private USSD_Codes ussdCodes;
}
