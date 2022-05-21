package com.company.callcompany.entity;

import com.company.callcompany.entity.templates.CommonUniqueFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends CommonUniqueFields implements UserDetails {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Role> role;
    private boolean enable=false;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Company company;

    private boolean accountNonExpired=true;
    private boolean accountCredentialsNonExpired=true;
    private boolean accountNonLocked=true;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return accountCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
