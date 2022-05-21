package com.company.callcompany.service;

import com.company.callcompany.dto.Response;
import com.company.callcompany.dto.TariffDto;
import com.company.callcompany.entity.Employee;
import com.company.callcompany.entity.Tariffs;
import com.company.callcompany.entity.enums.RoleTypes;
import com.company.callcompany.repository.TariffRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TariffService {
    private TariffRepository tariffRepository;
    public Response addTariff(TariffDto tariffDto){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Employee employee=(Employee) authentication.getPrincipal();
        if (!employee.getRole().equals(RoleTypes.SERVICE_MANAGER)) {
           return new Response("Tariffs only added by SERVICE_MANAGERs ",false);

        }

        if (tariffRepository.findByNameAndCompany_Name(tariffDto.getName(),employee.getCompany().getName()).isPresent()) {
            return new Response("This tariff already exists",false);
        }
        Tariffs tariffs=new Tariffs();
        tariffs.setName(tariffDto.getName());
        tariffs.setPrice(tariffDto.getPrice());
        tariffs.setCompany(employee.getCompany());
        tariffs.setDuration(tariffDto.getDuration());
        tariffs.setAfterExpirePrice(tariffDto.getAfterExpireDate());
        tariffs.setArchived(false);
        tariffs.setDescription(tariffDto.getDescription());
        tariffs.setMb(tariffDto.getSms());
        tariffs.setMinuteIn(tariffDto.getMinuteIn());
        tariffs.setMinuteOut(tariffDto.getMinuteOut());
        tariffs.setSms(tariffDto.getSms());

        tariffRepository.save(tariffs);
        return new Response("Tariff saved",true);

    }
}
