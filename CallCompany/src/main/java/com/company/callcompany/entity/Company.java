package com.company.callcompany.entity;

import com.company.callcompany.entity.templates.CommonUniqueFields;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company extends CommonUniqueFields {
    @Column(unique = true,nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String address;
    @OneToMany(mappedBy = "company_branch",cascade = CascadeType.ALL)
    private Set<Branch> branch;
    @OneToMany(mappedBy = "company_code",cascade = CascadeType.ALL)
    private Set<CompanyCode> companyCode;
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Employee> employee;
}
