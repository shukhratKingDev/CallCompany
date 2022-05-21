package com.company.callcompany.controller;

import com.company.callcompany.dto.Response;
import com.company.callcompany.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentController {
    private PaymentService paymentService;
@Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
@PostMapping("/pay")
    public ResponseEntity<Response>pay(@RequestBody String  phoneNumber, @RequestBody double sum ){
        Response response=paymentService.pay(phoneNumber,sum);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
}
