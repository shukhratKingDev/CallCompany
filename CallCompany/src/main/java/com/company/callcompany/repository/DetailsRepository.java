package com.company.callcompany.repository;

import com.company.callcompany.entity.DetailsForCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<DetailsForCompany ,Integer> {
}
