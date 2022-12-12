package com.example.employee.controller;

import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees", params = "larger")
    public ResponseEntity<?> getAllEmpLargerThan(@RequestParam int age) {
        return new ResponseEntity<>(employeeService.fetchEmpAgeLargerThan(age), HttpStatus.OK);
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<?> getAllEmp() {
        return new ResponseEntity<>(employeeService.getAllEmp(), HttpStatus.OK);
    }
}
