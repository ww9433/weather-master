package com.example.university.service.impl;

import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Employee;
import com.example.university.domain.entity.Student;
import com.example.university.domain.entity.dto.EmployeeDto;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.payload.response.EmployeeGetResponse;
import com.example.university.respository.EmployeeRepository;
import com.example.university.service.EmpService;
import com.mysql.cj.xdevapi.JsonArray;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpServiceImpl implements EmpService {
    private final RestTemplate restTemplate;
    private final EmployeeRepository employeeRepository;

//    private String url = "https://dummy.restapiexample.com/api/v1/employees";
    @Value("${url}")
    private String url;

    @Autowired
    public EmpServiceImpl(RestTemplate restTemplate, EmployeeRepository employeeRepository) {
//        restTemplate.getForObject()
        this.restTemplate = restTemplate;
        this.employeeRepository = employeeRepository;
    }


    @Override
    @Transactional
    public CommonResponse insertAll(List<Employee> empList) {;
        for(Employee employee : empList) {
            employeeRepository.save(employee);
        }
//        return restTemplate.postForObject("http://localhost:8080/employee/insert", empList, CommonResponse.class);
        return new CommonResponse(0, new Date(), empList);
    }

    @Override
    @Transactional
    public CommonResponse insert(Employee employee) {
        employeeRepository.save(employee);
        return new CommonResponse(0, new Date(), employee.getId());
    }

    @Override
    public CommonResponse findAll() {
        List<Employee> empList  =  employeeRepository.findAll();

        return new CommonResponse(0, new Date(), empList);
    }

    @Override
    public CommonResponse empAgeGreaterThan(int target) {
        List<Employee> employeeList = employeeRepository.empAgeGreaterThan(target);
        return new CommonResponse(0, new Date(), employeeList);
    }

    @Override
    public CommonResponse findById(String id) {
        Optional<Employee> emp =  employeeRepository.findById(id);

        if(emp.isEmpty()) {
            //log
            throw new ResourceNotFoundException("...");
        }
        return new CommonResponse(0, new Date(), emp.get());
    }

    @Override
    public CommonResponse updateById(String id, Employee employee) {
        Optional<Employee> preEmployee = employeeRepository.findById(id);

        if (preEmployee.isPresent()) {
            preEmployee.get().setName(employee.getName());
            preEmployee.get().setAge(employee.getAge());
            preEmployee.get().setSalary(employee.getSalary());
            preEmployee.get().setImage(employee.getImage());
            employeeRepository.saveAndFlush(preEmployee.get());

            return new CommonResponse(0, new Date(), preEmployee.get());
        } else {
            employeeRepository.save(employee);
            return new CommonResponse(0, new Date(), employee);
        }
    }

    @Override
    public CommonResponse findAllByRestAPI() {
        EmployeeDto data = restTemplate.getForObject(url, EmployeeDto.class);
        return new CommonResponse(0, new Date(), data.getEmpData());
    }

    @Override
    public CommonResponse empAgeGreaterThanByRestAPI(int age) {
        EmployeeDto data = restTemplate.getForObject(url, EmployeeDto.class);

        List<Employee> res = Arrays.stream(data.getEmpData()).filter(e -> e.getAge() > age).collect(Collectors.toList());
        return new CommonResponse(0, new Date(), res);
    }


}
