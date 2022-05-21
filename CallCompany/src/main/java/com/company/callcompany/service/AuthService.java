package com.company.callcompany.service;

import com.company.callcompany.dto.LoginDto;
import com.company.callcompany.dto.Response;
import com.company.callcompany.entity.Employee;
import com.company.callcompany.repository.EmployeeRepository;
import com.company.callcompany.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    private  EmployeeRepository employeeRepository;
    private  JwtProvider jwtProvider;
    private  AuthenticationManager authenticationManager;

@Autowired
    public AuthService( EmployeeRepository employeeRepository, JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.employeeRepository = employeeRepository;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    public Response login(LoginDto loginDto){
    Optional<Employee> employeeOptional=employeeRepository.findByUsername(loginDto.getUsername());
    String password=employeeOptional.get().getPassword();
    String username=employeeOptional.get().getUsername();

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            Employee user = (Employee) authentication.getPrincipal();
            String token = jwtProvider.generateJwt(user.getUsername(),user.getRole());
            return new Response("You successfully logged in",true,token);
        }catch (Exception e){
            System.out.println(e);
            return new Response("Username or password incorrect",false);

        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("This username not found"));
    }
}
