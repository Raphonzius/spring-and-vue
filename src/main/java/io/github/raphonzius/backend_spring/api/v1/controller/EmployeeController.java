package io.github.raphonzius.backend_spring.api.v1.controller;

import io.github.raphonzius.backend_spring.core.model.entity.Employee;
import io.github.raphonzius.backend_spring.infrastructure.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("employees")
    public List<Employee> fetchEmployees(){
        return employeeRepository.findAll();
    }

}
