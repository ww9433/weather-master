package com.example.university.service;

import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Employee;
import com.example.university.domain.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpService {
   CommonResponse insertAll(List<Employee> empList);

   CommonResponse insert(Employee employee);

   CommonResponse findAll();

   CommonResponse empAgeGreaterThan(int target);

   CommonResponse findById(String id);

   CommonResponse updateById(String id, Employee employee);

   CommonResponse findAllByRestAPI();

   CommonResponse empAgeGreaterThanByRestAPI(int age);
}
