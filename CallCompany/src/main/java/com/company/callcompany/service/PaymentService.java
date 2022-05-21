package com.company.callcompany.service;

import com.company.callcompany.dto.Response;
import com.company.callcompany.entity.ClientPhoneNumbers;
import com.company.callcompany.entity.PhoneNumber;
import com.company.callcompany.repository.ClientPhoneNumberRepository;
import com.company.callcompany.repository.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {
    private ClientPhoneNumberRepository clientPhoneNumberRepository;
    private NumberRepository numberRepository;
@Autowired
    public PaymentService(ClientPhoneNumberRepository clientPhoneNumberRepository, NumberRepository numberRepository) {
        this.clientPhoneNumberRepository = clientPhoneNumberRepository;
    this.numberRepository = numberRepository;
}

    public Response pay(String phoneNumber, double sum){
    Optional<PhoneNumber> number=numberRepository.findByPhoneNumber(phoneNumber);
        if (!number.isPresent()) {
            return new Response("Incorrect number",false);
        }
        Optional<ClientPhoneNumbers> clientPhoneNumbers=clientPhoneNumberRepository.findByPhoneNumber(number.get());

        if (clientPhoneNumbers.isPresent()) {
            return new Response("Not found",false);
        }
        ClientPhoneNumbers phoneNumbers=clientPhoneNumbers.get();
        double balance=phoneNumbers.getBalance();
        balance+=sum;
        phoneNumbers.setBalance(balance);
        clientPhoneNumberRepository.save(phoneNumbers);
        return new Response("Balance filled",true);
    }
}
