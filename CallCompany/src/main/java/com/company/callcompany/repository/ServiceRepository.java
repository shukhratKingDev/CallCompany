package com.company.callcompany.repository;

import com.company.callcompany.entity.Services;
import com.company.callcompany.entity.USSD_Codes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.security.Provider;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Services,Integer> {
    Optional<Services> findByNameAndCompany_Name(String name, String company_name);
    Optional<Services> findByUssdCodes(USSD_Codes ussdCodes);
}
