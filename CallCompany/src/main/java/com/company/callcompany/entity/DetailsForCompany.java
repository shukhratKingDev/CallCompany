package com.company.callcompany.entity;

import com.company.callcompany.entity.templates.CommonFields;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DetailsForCompany extends CommonFields {
    private String actionName;
    private double price;
    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp actionDate;
    @ManyToOne
    private Company company_details;
    @ManyToOne
    private Employee worker;
}
