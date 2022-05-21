package com.company.callcompany.repository;

import com.company.callcompany.entity.ServiceTypes;
import com.company.callcompany.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "serviceTypes",collectionResourceRel = "listOfCompanyServiceTypes")
public interface ServiceTypesRepository extends JpaRepository<Services,Integer> {
    Optional<ServiceTypes> findByName(String name);
}
