package com.company.callcompany.repository;

import com.company.callcompany.entity.Tariffs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface TariffRepository extends JpaRepository<Tariffs , UUID> {
    Optional<Tariffs> findByNameAndCompany_Name(String name, String company_name);
    Optional<Tariffs> findByName(String name);
}
