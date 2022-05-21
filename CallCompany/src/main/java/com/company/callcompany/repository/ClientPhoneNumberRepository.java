package com.company.callcompany.repository;

import com.company.callcompany.entity.ClientPhoneNumbers;
import com.company.callcompany.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientPhoneNumberRepository extends JpaRepository<ClientPhoneNumbers,Integer> {
    Optional<ClientPhoneNumbers> findByPhoneNumber(PhoneNumber phoneNumber);
    Optional<ClientPhoneNumbers> findByPhoneNumber_PhoneNumber(String phoneNumber_phoneNumber);
}
