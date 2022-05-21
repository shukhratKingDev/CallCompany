package com.company.callcompany.entity;

import com.company.callcompany.entity.enums.RoleTypes;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoleTypes roleTypes;
    @Override
    public String getAuthority() {
        return roleTypes.name();
    }
}
