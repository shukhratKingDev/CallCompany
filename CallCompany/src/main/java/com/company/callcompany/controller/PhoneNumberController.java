package com.company.callcompany.controller;

import com.company.callcompany.dto.PhoneNumberDto;
import com.company.callcompany.dto.Response;
import com.company.callcompany.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PhoneNumberController {
    private PhoneNumberService phoneNumberService;
@Autowired
    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @PostMapping("/addNumber")
    public ResponseEntity<Response> addNumber(PhoneNumberDto phoneNumberDto){
        Response response=phoneNumberService.addPhoneNumber(phoneNumberDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }
}
