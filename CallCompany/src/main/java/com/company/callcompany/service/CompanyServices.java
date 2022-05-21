package com.company.callcompany.service;

import com.company.callcompany.dto.Response;
import com.company.callcompany.dto.ServiceDto;
import com.company.callcompany.entity.DetailsForCompany;
import com.company.callcompany.entity.Employee;
import com.company.callcompany.entity.Services;
import com.company.callcompany.entity.USSD_Codes;
import com.company.callcompany.entity.enums.RoleTypes;
import com.company.callcompany.entity.enums.ServiceType;
import com.company.callcompany.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CompanyServices {
    private RoleRepository roleRepository;
    private ServiceRepository serviceRepository;
    private ServiceTypesRepository serviceTypesRepository;


@Autowired
    public CompanyServices(RoleRepository roleRepository, ServiceRepository serviceRepository, ServiceTypesRepository serviceTypesRepository) {
        this.roleRepository = roleRepository;
        this.serviceRepository = serviceRepository;
        this.serviceTypesRepository = serviceTypesRepository;
}

    public Response addService(ServiceDto serviceDto){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Employee employee=(Employee) authentication.getPrincipal();
        if (!employee.getRole().equals(roleRepository.findByRoleTypes(RoleTypes.SERVICE_MANAGER))) {
            return new Response("Services only added by SERVICE_MANAGERS",false);
        }
        if (serviceRepository.findByNameAndCompany_Name(serviceDto.getName(),employee.getCompany().getName()).isPresent()) {
            return new Response("This service already exists in this company",false);
        }
        Services service=new Services();
        service.setName(serviceDto.getName());
        service.setPrice(serviceDto.getPrice());
        service.setCompany(employee.getCompany());
        switch (serviceDto.getType().toLowerCase()){
            case "weather_service": service.setServiceTypes(Collections.singleton(serviceTypesRepository.findByName(ServiceType.WEATHER_SERVICE.name()).get()));
            case "mb": service.setServiceTypes(Collections.singleton(serviceTypesRepository.findByName(ServiceType.MOVIES.name()).get()));
            case "sms": service.setServiceTypes(Collections.singleton(serviceTypesRepository.findByName(ServiceType.BOOKS.name()).get()));
            case "call": service.setServiceTypes(Collections.singleton(serviceTypesRepository.findByName(ServiceType.MUSICS.name()).get()));
        }

        service.setDescriptionAboutService(serviceDto.getDescription());
        USSD_Codes ussd_codes=new USSD_Codes();
        ussd_codes.setCode(service.getUssdCodes().getCode());
        ussd_codes.setCompany(employee.getCompany());
        ussd_codes.setDescription(serviceDto.getUssdCodes().getDescription());
        service.setUssdCodes(ussd_codes);
        serviceRepository.save(service);
        return new Response("Service saved successfully ",true);

    }

}
