package com.company.callcompany.service;

import com.company.callcompany.entity.Company;
import com.company.callcompany.entity.enums.RoleTypes;
import com.company.callcompany.repository.CompanyRepository;
import com.company.callcompany.repository.EmployeeRepository;
import com.company.callcompany.dto.RegisterDto;
import com.company.callcompany.dto.Response;
import com.company.callcompany.entity.Employee;
import com.company.callcompany.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private CompanyRepository companyRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository, CompanyRepository companyRepository,PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Response addDirector(RegisterDto registerDto){
        if (registerDto.getCompany()==null) {
            return new Response("Company field must not be empty",false);
        }
        Optional<Company> optionalCompany=companyRepository.findByNameAndAddress(registerDto.getCompany().getName(), registerDto.getCompany().getAddress());

        Optional<Employee> employeeOptional=employeeRepository.findByUsername(registerDto.getUsername());

        if (employeeOptional.isPresent()) {
            return new Response("This user is already exists",false);
        }
        if (optionalCompany.isPresent()) {

            byte count=0;
            for (Employee employee : optionalCompany.get().getEmployee()) {
                if (employee.getRole().equals(roleRepository.findByRoleTypes(RoleTypes.DIRECTOR))){
                  count++;
                }
            }
            if (count==0){

        Employee director=addFields(registerDto);
        director.setRole(Collections.singleton(roleRepository.findByRoleTypes(RoleTypes.DIRECTOR).get()));
        director.setCompany(optionalCompany.get());
        employeeRepository.save(director);
        return new Response("Director successfully saved",true);
        }
            return new Response("This company has their own director",false);
        }
        return new Response("This company does not exists",false);
    }

    public Response addManager(RegisterDto registerDto){
  return addRole(RoleTypes.MANAGER,registerDto);
    }

    public Response addHr_Manager(RegisterDto registerDto){
       return addRole(RoleTypes.HR_MANAGER,registerDto);
    }

    public Response addBranchManager(RegisterDto registerDto){
       return addRole(RoleTypes.BRANCH_MANAGER,registerDto);
    }

    public Response addDepartmentManager(RegisterDto registerDto){
        return addRole(RoleTypes.DEPARTMENT_MANAGER,registerDto);
    }

    public Response addWorker(RegisterDto registerDto){
        if (employeeRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            return new Response("This username already taken",false);
        }
        Employee employee=addFields(registerDto);
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Employee manager=(Employee) authentication.getPrincipal();
        manager.getRole().equals(roleRepository.findByRoleTypes(RoleTypes.HR_MANAGER));
        employee.setCompany(manager.getCompany());
        employee.setRole(Collections.singleton(roleRepository.findByRoleTypes(RoleTypes.WORKER).get()));
        employeeRepository.save(employee);
        return new Response("Employee successfully saved",true);
    }





    // extra functions for refactoring and clean code
    public Response addRole(RoleTypes roleTypes,RegisterDto registerDto) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Employee director=(Employee) authentication.getPrincipal();
        if (!director.getRole().equals(RoleTypes.DIRECTOR)) {
            return new Response("You can not add manager.Managers only add by Director",false);
        }
        if (employeeRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            return new Response("This username is already exists",false);
        }
        Employee manager=addFields(registerDto);
        manager.setRole(Collections.singleton(roleRepository.findByRoleTypes(roleTypes).get()));
        manager.setCompany(director.getCompany());
        employeeRepository.save(manager);
        return new Response("Manager successfully added",true);
    }


    public Employee addFields(RegisterDto registerDto){
        Employee employee=new Employee();
        employee.setFirstName(registerDto.getFirstName());
        employee.setLastName(registerDto.getLastName());
        employee.setUsername(registerDto.getUsername());
        employee.setEnable(true);
        employee.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return employee;
    }



}
