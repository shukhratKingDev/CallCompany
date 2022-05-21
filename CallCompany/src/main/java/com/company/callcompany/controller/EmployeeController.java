package com.company.callcompany.controller;

import com.company.callcompany.dto.LoginDto;
import com.company.callcompany.dto.RegisterDto;
import com.company.callcompany.dto.Response;
import com.company.callcompany.service.AuthService;
import com.company.callcompany.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AuthService authService;
    @PostMapping("/director")
    public ResponseEntity<Response> addDirector(@RequestBody RegisterDto director){
        Response response=employeeService.addDirector(director);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginDto loginDto){
        Response response=authService.login(loginDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(response);
    }

}
