package com.company.callcompany.service;

import com.company.callcompany.dto.RegisterClientDto;
import com.company.callcompany.dto.RegisterClientServiceDto;
import com.company.callcompany.dto.Response;
import com.company.callcompany.entity.*;
import com.company.callcompany.entity.enums.RoleTypes;
import com.company.callcompany.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class ClientServices {
    private NumberRepository numberRepository;
    private PacketRepository packetRepository;
    private DetailsRepository detailsRepository;
    private ClientRepository clientRepository;
    private TariffRepository tariffRepository;
    private ClientPhoneNumberRepository clientPhoneNumberRepository;
    private RoleRepository roleRepository;
    private UssdCodeRepository ussdCodeRepository;
    private ClientPacketRepository clientPacketRepository;
    private ClientServiceRepository  clientServiceRepository;
    private ServiceRepository serviceRepository;
@Autowired
    public ClientServices(NumberRepository numberRepository, PacketRepository packetRepository,
                          DetailsRepository detailsRepository, ClientRepository clientRepository,
                          TariffRepository tariffRepository,
                          ClientPhoneNumberRepository clientPhoneNumberRepository, RoleRepository roleRepository, UssdCodeRepository ussdCodeRepository, ClientPacketRepository clientPacketRepository, ClientServiceRepository clientServiceRepository, ServiceRepository serviceRepository) {
        this.numberRepository = numberRepository;
        this.packetRepository = packetRepository;
    this.detailsRepository = detailsRepository;
    this.clientRepository = clientRepository;
        this.tariffRepository = tariffRepository;
        this.clientPhoneNumberRepository = clientPhoneNumberRepository;
    this.roleRepository = roleRepository;
    this.ussdCodeRepository = ussdCodeRepository;
    this.clientPacketRepository = clientPacketRepository;
    this.clientServiceRepository = clientServiceRepository;
    this.serviceRepository = serviceRepository;
}

    public Response addPhoneNumberToClient(RegisterClientDto registerClientDto){
    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Employee employee=(Employee) authentication.getPrincipal();
        if (!employee.getRole().equals(roleRepository.findByRoleTypes(RoleTypes.WORKER))) {
            return new Response("You can not register clients. It is done by only company workers",false);
        }
       Optional<PhoneNumber> phoneNumber=numberRepository.findByPhoneNumber(registerClientDto.getPhoneNumber());
        if (!phoneNumber.isPresent()) {
            return new Response("This number currently not available in our system.",false);
        }
        if (phoneNumber.get().getActive()!=null) {
            return new Response("This number already taken.",false);
        }

        Optional<Tariffs>tariffs=tariffRepository.findByName(registerClientDto.getTariff());
        if (!tariffs.isPresent()) {
            return new Response("This tariff not found",false);
        }
        Tariffs tariffs1=tariffs.get();
        PhoneNumber number=phoneNumber.get();
        Client client=new Client();
        client.setFirstName(registerClientDto.getFirstName());
        client.setLastName(registerClientDto.getLastName());
        client.setAddress(registerClientDto.getAddress());
        client.setPassportNumber(registerClientDto.getPassportNumber());
        client.setGivenDate(registerClientDto.getGivenDate());
        client.setGivenPlace(registerClientDto.getGivenPlace());

        ClientPhoneNumbers clientPhoneNumbers=new ClientPhoneNumbers();
        clientPhoneNumbers.setClient(client);
        clientPhoneNumbers.setPhoneNumber(number);
        clientPhoneNumbers.setTariffs(tariffs1);
        clientPhoneNumberRepository.save(clientPhoneNumbers);
        DetailsForCompany details=new DetailsForCompany();
        details.setActionName("New client added");
        details.setWorker(employee);
        details.setPrice(tariffs.get().getPrice());
        details.setActionDate(Timestamp.valueOf(LocalDateTime.now()));
        details.setCompany_details(employee.getCompany());
        detailsRepository.save(details);
        return new Response("Client successfully registered",true);
    }

    public Response addPacketToClient(RegisterClientServiceDto registerClientServiceDto){
       Response response=check(registerClientServiceDto);
        if (!response.isSuccess()) {
          return new Response(response.getMessage(),false);
        }
       USSD_Codes ussd_codes=ussdCodeRepository.findByCode(registerClientServiceDto.getUssdCode()).get();
       PhoneNumber phoneNumber=numberRepository.findByPhoneNumber(registerClientServiceDto.getPhoneNumber()).get();
        Packets packets=packetRepository.findByUssdCodes( ussd_codes).get();
        if (packets==null) {
           return new Response("This packet not found",false) ;
        }
        Client client = clientPhoneNumberRepository.findByPhoneNumber(phoneNumber).get().getClient();
        ClientPackets clientPackets=new ClientPackets();
        clientPackets.setClient_packet(client);
        clientPackets.setPackets(packets);
        clientPacketRepository.save(clientPackets);
        DetailsForCompany details=new DetailsForCompany();
        details.setActionName("New packet bought");
        details.setWorker((Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        details.setPrice(packets.getPrice());
        details.setActionDate(Timestamp.valueOf(LocalDateTime.now()));
        details.setCompany_details(packets.getCompany());
        detailsRepository.save(details);
        return new Response("Packet successfully bought",true);
    }

    public Response addService(RegisterClientServiceDto serviceDto){
    Response response=check(serviceDto);
        if (!response.isSuccess()) {
           return new Response(response.getMessage(),false);
        }
        USSD_Codes ussd_codes=ussdCodeRepository.findByCode(serviceDto.getUssdCode()).get();
        PhoneNumber phoneNumber=numberRepository.findByPhoneNumber(serviceDto.getPhoneNumber()).get();
        Services services=serviceRepository.findByUssdCodes(ussd_codes).get();
        if (services==null) {
            return new Response("This service not found",false) ;
        }

        Client client = clientPhoneNumberRepository.findByPhoneNumber(phoneNumber).get().getClient();
        com.company.callcompany.entity.ClientServices clientServices=new com.company.callcompany.entity.ClientServices();
        clientServices.setClient_service(client);
        clientServices.setPhoneNumber(phoneNumber);
        clientServices.setServices(services);
        clientServiceRepository.save(clientServices);
        DetailsForCompany details=new DetailsForCompany();
        details.setActionName("New packet bought");
        details.setWorker((Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        details.setPrice(services.getPrice());
        details.setActionDate(Timestamp.valueOf(LocalDateTime.now()));
        details.setCompany_details(services.getCompany());
        detailsRepository.save(details);
        return new Response("Service is active",true);

    }
    public Response check(RegisterClientServiceDto clientServiceDto){
        if (!ussdCodeRepository.findByCode(clientServiceDto.getUssdCode()).isPresent()) {
            return new Response("This ussd_code not valid",false);
        }

        Optional<PhoneNumber> phoneNumber=numberRepository.findByPhoneNumber(clientServiceDto.getPhoneNumber());
        if (!phoneNumber.isPresent()) {
            return new Response("This number not found",false);
        }
        USSD_Codes ussd_codes=ussdCodeRepository.findByCode(clientServiceDto.getUssdCode()).get();

        if (!phoneNumber.get().getCompany().equals(ussd_codes.getCompany())) {
            return new Response("Incorrect ussd_code", false);

        }
        return new Response("success",true);
    }
}
