package com.company.callcompany.repository;

import com.company.callcompany.entity.Role;
import com.company.callcompany.entity.enums.RoleTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleTypes(RoleTypes roleTypes);
}
