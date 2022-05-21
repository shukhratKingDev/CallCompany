package com.company.callcompany.repository;

import com.company.callcompany.entity.Client;
import com.company.callcompany.entity.ClientTariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientTariffRepository extends JpaRepository<ClientTariff,Integer> {
}
