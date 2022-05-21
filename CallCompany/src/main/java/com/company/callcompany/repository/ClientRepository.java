package com.company.callcompany.repository;

import com.company.callcompany.entity.Client;
import com.company.callcompany.entity.ClientPhoneNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClientRepository extends JpaRepository<Client , UUID> {
//    Optional<Client>findByClientPhoneNumbers(List<ClientPhoneNumbers> clientPhoneNumbers);
}
