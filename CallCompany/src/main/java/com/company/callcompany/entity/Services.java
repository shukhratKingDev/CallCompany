package com.company.callcompany.entity;

import com.company.callcompany.entity.templates.CommonFields;
import com.company.callcompany.entity.templates.CommonUniqueFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Services extends CommonFields {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String descriptionAboutService;
    @ManyToMany
    private Set<ServiceTypes> serviceTypes;
    @Column(nullable = false)
    private double price;
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
