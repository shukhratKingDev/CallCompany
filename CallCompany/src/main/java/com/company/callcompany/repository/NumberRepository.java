package com.company.callcompany.repository;

import com.company.callcompany.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface NumberRepository extends JpaRepository<PhoneNumber, UUID> {
    Optional<PhoneNumber>findByPhoneNumber(String phoneNumber);
}
