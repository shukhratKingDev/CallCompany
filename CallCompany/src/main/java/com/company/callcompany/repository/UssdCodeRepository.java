package com.company.callcompany.repository;

import com.company.callcompany.entity.USSD_Codes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "ussd_codes",collectionResourceRel ="listOfUssdCodes" )
public interface UssdCodeRepository extends JpaRepository<USSD_Codes,Integer> {
    Optional<USSD_Codes> findByCode(String code);
}
