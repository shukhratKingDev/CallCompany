package com.company.callcompany.repository;

import com.company.callcompany.entity.ClientPackets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientPacketRepository extends JpaRepository<ClientPackets,Integer> {

}
