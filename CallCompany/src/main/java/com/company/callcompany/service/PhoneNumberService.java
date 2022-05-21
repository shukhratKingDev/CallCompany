package com.company.callcompany.service;

import com.company.callcompany.dto.PhoneNumberDto;
import com.company.callcompany.dto.Response;
import com.company.callcompany.entity.CompanyCode;
import com.company.callcompany.entity.Employee;
import com.company.callcompany.entity.PhoneNumber;
import com.company.callcompany.entity.enums.PersonType;
import com.company.callcompany.entity.enums.RoleTypes;
import com.company.callcompany.repository.NumberRepository;
import com.company.callcompany.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PhoneNumberService {
    private RoleRepository roleRepository;
    private NumberRepository numberRepository;
@Autowired
    public PhoneNumberService(RoleRepository roleRepository, NumberRepository numberRepository) {
        this.roleRepository = roleRepository;
    this.numberRepository = numberRepository;
}

    public Response addPhoneNumber(PhoneNumberDto phoneNumberDto){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Employee employee=(Employee) authentication.getPrincipal();
        if (!employee.getRole().equals(roleRepository.findByRoleTypes(RoleTypes.NUMBERS_MANAGER))) {
            return new Response("Phone numbers added only by NUMBERS_MANAGERS ",false);
        }
        if (numberRepository.findByPhoneNumber(phoneNumberDto.getPhoneNumber()).isPresent()) {
            return new Response("This phone number already registered",false);
        }
        Set<CompanyCode> companyCodes=employee.getCompany().getCompanyCode();
        PhoneNumber phoneNumber=new PhoneNumber();
        int count=0;
        for (CompanyCode companyCode : companyCodes) {
            if (phoneNumberDto.getPhoneNumber().startsWith("+998"+companyCode)) {
               phoneNumber.setPhoneNumber(phoneNumberDto.getPhoneNumber());
               count++;
            }
        }
        if (count!=1) {
            return new Response("Phone numbers must start with correct company codes",false);
        }

        phoneNumber.setPhoneNumber(phoneNumberDto.getPhoneNumber());
        phoneNumber.setBalance(0);
        phoneNumber.setActive(null);
        phoneNumber.setCompany(employee.getCompany());
        if (phoneNumberDto.isJuridical()) {
            phoneNumber.setPersonType(PersonType.JURIDICAL);
        }else{
            phoneNumber.setPersonType(PersonType.PHYSICAL);
        }
        numberRepository.save(phoneNumber);
        return new Response("Phone number successfully saved",true);
    }
}
