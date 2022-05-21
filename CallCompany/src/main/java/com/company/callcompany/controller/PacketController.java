package com.company.callcompany.controller;

import com.company.callcompany.dto.PacketDto;
import com.company.callcompany.dto.Response;
import com.company.callcompany.service.PacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PacketController {
    private PacketService packetService;
@Autowired
    public PacketController(PacketService packetService) {
        this.packetService = packetService;
    }

    @PostMapping("/addPacket")
    public ResponseEntity<Response> addPacket(PacketDto packetDto){
        Response response=packetService.addPacket(packetDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }
}
