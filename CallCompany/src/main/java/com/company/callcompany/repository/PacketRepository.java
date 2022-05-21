package com.company.callcompany.repository;

import com.company.callcompany.entity.Packets;
import com.company.callcompany.entity.USSD_Codes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacketRepository extends JpaRepository<Packets,Integer> {
    Optional<Packets> findByNameAndCompany_Name(String name, String company_name);
    Optional<Packets> findByUssdCodes(USSD_Codes ussdCodes);
}
