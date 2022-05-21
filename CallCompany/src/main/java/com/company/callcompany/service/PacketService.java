package com.company.callcompany.service;

import com.company.callcompany.dto.PacketDto;
import com.company.callcompany.dto.Response;
import com.company.callcompany.entity.Employee;
import com.company.callcompany.entity.Packets;
import com.company.callcompany.entity.USSD_Codes;
import com.company.callcompany.entity.enums.ActionType;
import com.company.callcompany.entity.enums.RoleTypes;
import com.company.callcompany.repository.PacketRepository;
import com.company.callcompany.repository.RoleRepository;
import com.company.callcompany.repository.UssdCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PacketService {
    private PacketRepository packetRepository;
    private RoleRepository roleRepository;
    private UssdCodeRepository ussdCodeRepository;
   @Autowired
    public PacketService(PacketRepository packetRepository, RoleRepository roleRepository, UssdCodeRepository ussdCodeRepository) {
        this.packetRepository = packetRepository;
       this.roleRepository = roleRepository;
       this.ussdCodeRepository = ussdCodeRepository;
   }

    public Response addPacket(PacketDto packetDto){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Employee manager=(Employee)authentication.getPrincipal();
        if (!manager.getRole().equals(roleRepository.findByRoleTypes(RoleTypes.SERVICE_MANAGER))) {
            return new Response("Packets only added by SERVICE_MANAGERS",false);
        }
        if (packetRepository.findByNameAndCompany_Name(packetDto.getName(),manager.getCompany().getName()).isPresent()) {
            return new Response("Packet with this name is already exists",false);
        }
        USSD_Codes ussd_codes=new USSD_Codes();
        ussd_codes.setCompany(manager.getCompany());
        ussd_codes.setCode(packetDto.getUssdCode().getCode());
        ussd_codes.setDescription(packetDto.getUssdCode().getDescription());
        Packets packets=new Packets();
        packets.setName(packets.getName());
        packets.setAmount(packetDto.getAmount());
        packets.setDuration(packetDto.getDuration());
        packets.setPrice(packetDto.getPrice());
        packets.setCompany(manager.getCompany());
        packets.setAvailable(packetDto.isAvailable());
        packets.setUssdCodes(ussd_codes);
        switch (packetDto.getType().toLowerCase()) {
            case "mb" -> packets.setActionType(ActionType.MB);
            case "sms" -> packets.setActionType(ActionType.SMS);
            case "call" -> packets.setActionType(ActionType.CALL);
        }
        packetRepository.save(packets);
        return new Response("Packet saved successfully saved",false);
    }
}
