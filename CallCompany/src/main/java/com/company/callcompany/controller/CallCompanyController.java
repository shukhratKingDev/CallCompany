package com.company.callcompany.controller;

import com.company.callcompany.dto.CallDto;
import com.company.callcompany.dto.Response;
import com.company.callcompany.service.CallCompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CallCompanyController {
    private CallCompanyServices companyServices;
@Autowired
    public CallCompanyController(CallCompanyServices companyServices) {
        this.companyServices = companyServices;
    }
@PostMapping("/call")
    public ResponseEntity<Response> call(CallDto callDto){
    Response response=companyServices.call(callDto);
    return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
}
@PostMapping("/sendSms")
    public ResponseEntity<Response> sendSms(CallDto callDto){
        Response response=companyServices.sendSms(callDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }

    @PostMapping("/download")
    public ResponseEntity<Response> download(CallDto callDto){
        Response response=companyServices.download(callDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
}
