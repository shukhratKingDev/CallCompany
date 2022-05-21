package com.company.callcompany.repository;

import com.company.callcompany.entity.CompanyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(path = "companyCode",collectionResourceRel = "companyCodes")
public interface CompanyCodesRepository extends JpaRepository<CompanyCode, Integer> {
}
