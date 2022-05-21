package com.company.callcompany.repository;

import com.company.callcompany.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
@RepositoryRestResource(path = "company",collectionResourceRel = "ListOfCompanies")
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByNameAndAddress(String name, String address);
}
