package com.company.callcompany.repository;

import com.company.callcompany.entity.ClientServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientServiceRepository  extends JpaRepository<ClientServices,Integer> {
}
