package com.company.callcompany.controller;

import com.company.callcompany.dto.Response;
import com.company.callcompany.dto.TariffDto;
import com.company.callcompany.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TariffController {
    private TariffService tariffService;
@Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @PostMapping("/addTariff")
    public ResponseEntity<Response>addTariff(TariffDto tariffDto){
        Response response=tariffService.addTariff(tariffDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }
}
