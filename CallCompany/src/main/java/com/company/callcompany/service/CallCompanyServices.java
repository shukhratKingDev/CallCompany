package com.company.callcompany.service;

import com.company.callcompany.dto.CallDto;
import com.company.callcompany.dto.Response;
import com.company.callcompany.entity.Client;
import com.company.callcompany.entity.ClientPhoneNumbers;
import com.company.callcompany.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallCompanyServices {
    private ClientPhoneNumberRepository phoneNumberRepository;
    private ClientPacketRepository clientPacketRepository;
    private ClientServiceRepository clientServiceRepository;
    private ClientRepository clientRepository;
    private ClientTariffRepository clientTariffRepository;

    public CallCompanyServices(ClientPhoneNumberRepository phoneNumberRepository, ClientPacketRepository clientPacketRepository,
                               ClientServiceRepository clientServiceRepository,
                               ClientRepository clientRepository, ClientTariffRepository clientTariffRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.clientPacketRepository = clientPacketRepository;
        this.clientServiceRepository = clientServiceRepository;
        this.clientRepository = clientRepository;
        this.clientTariffRepository = clientTariffRepository;
    }

    public Response call(CallDto callDto){

        if (!phoneNumberRepository.findByPhoneNumber_PhoneNumber(callDto.getPhoneNumber()).isPresent()) {
            return new Response("Invalid phone number",false);
        }
        ClientPhoneNumbers phoneNumber=phoneNumberRepository.findByPhoneNumber_PhoneNumber(callDto.getPhoneNumber()).get();
//        Client client=clientRepository.findAllByClientPhoneNumbers(List.of(phoneNumber)).get();
        ClientPhoneNumbers phoneNumber1 = new ClientPhoneNumbers();
        for (ClientPhoneNumbers number : phoneNumber1.getClient().getClientPhoneNumbers()) {
            if (!number.isEnabled()) {
phoneNumber1=number;
break;
            }else{
                return new Response("You do not have enabled number to use",false);
            }
        }
        if (phoneNumber1.getTariffs().getMinuteOut()>0) {

            if (phoneNumber1.getTariffs().getMinuteOut()>callDto.getAmount()) {
                long amount=phoneNumber1.getTariffs().getMinuteOut();
                amount-=callDto.getAmount();
                phoneNumber1.getTariffs().setMinuteOut(amount);
                phoneNumberRepository.save(phoneNumber1);
                return new Response("Successfully called",true);
            }
            phoneNumber1.getTariffs().setMinuteOut(0);
            long remainAmount=callDto.getAmount()-phoneNumber1.getTariffs().getMinuteOut();
            if (remainAmount*10<phoneNumber1.getBalance()){
            phoneNumber1.setBalance(phoneNumber1.getBalance()-remainAmount*10);
            phoneNumberRepository.save(phoneNumber1);
            return new Response("Successfully called",true);
            }else{
                remainAmount= (long) (remainAmount-phoneNumber1.getBalance()*10);
                phoneNumber1.setBalance(0);
                phoneNumberRepository.save(phoneNumber1);
            }
            return new Response("You have called only for "+(callDto.getAmount()-remainAmount),false);
        }
        return new Response("You can not make call",false);
    }

    public Response sendSms(CallDto callDto){
        Optional<ClientPhoneNumbers> clientPhoneNumbers=phoneNumberRepository.findByPhoneNumber_PhoneNumber(callDto.getPhoneNumber());
        if (!(clientPhoneNumbers.isPresent()&& clientPhoneNumbers.get().getPhoneNumber().getActive())) {
           return new Response("Invalid phone number",false);
        }
        if (clientPhoneNumbers.get().getTariffs().getSms()>0) {
          clientPhoneNumbers.get().getTariffs().setSms(clientPhoneNumbers.get().getTariffs().getSms()-1);
          phoneNumberRepository.save(clientPhoneNumbers.get());
          return new Response("Sms successfully sent",true);
        }else
          if (clientPhoneNumbers.get().getBalance()>10) {
            clientPhoneNumbers.get().setBalance(clientPhoneNumbers.get().getBalance()-10);
              phoneNumberRepository.save(clientPhoneNumbers.get());
              return new Response("Sms successfully sent",true);
        }
          return new Response("You can not send sms because of your balance ",false);
    }
    public Response download(CallDto callDto){
        double remain=0;
        Optional<ClientPhoneNumbers> clientPhoneNumbers=phoneNumberRepository.findByPhoneNumber_PhoneNumber(callDto.getPhoneNumber());
        if (!(clientPhoneNumbers.isPresent()&&clientPhoneNumbers.get().isEnabled())){
            return new Response("Invalid phone number",false);
        }else
             if(clientPhoneNumbers.get().getTariffs().getMb()>0){
                 if (clientPhoneNumbers.get().getTariffs().getMb()>callDto.getAmount()) {
                     clientPhoneNumbers.get().getTariffs().setMb(clientPhoneNumbers.get().getTariffs().getMb()-callDto.getAmount());
                     phoneNumberRepository.save(clientPhoneNumbers.get());
                     return new Response("successfully downloaded",true);
                 }else
                 {
                     remain=callDto.getAmount()-clientPhoneNumbers.get().getTariffs().getMb();
                     clientPhoneNumbers.get().getTariffs().setMb(0);
                     if (clientPhoneNumbers.get().getBalance()>0) {
                         if (clientPhoneNumbers.get().getBalance()>remain*10) {
                             clientPhoneNumbers.get().setBalance(clientPhoneNumbers.get().getBalance()-remain*10);
                             phoneNumberRepository.save(clientPhoneNumbers.get());
                             return new Response("successfully downloaded",true);
                         }

                     }
                 }

             }
             return new Response("Not enough traffic you have",false);
    }
}
