package com.company.callcompany.entity;

import com.company.callcompany.entity.templates.CommonUniqueFields;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data

public class Branch extends CommonUniqueFields {
    @Column(nullable = false,unique = true)
    private String address;
    @ManyToOne(optional = false,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Company company_branch;
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Employee> employee;
}
