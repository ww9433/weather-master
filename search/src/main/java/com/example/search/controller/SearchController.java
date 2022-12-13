package com.example.search.controller;

import com.example.search.service.EmployeeService;
import com.example.search.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    private final EmployeeService employeeService;
    private final StudentService studentService;

    public SearchController(EmployeeService employeeService, StudentService studentService) {
        this.employeeService = employeeService;
        this.studentService = studentService;
    }

    @GetMapping(value = "/search/employees", params = "larger")
    public ResponseEntity<?> getEmpLargerThan(@RequestParam List<Integer> ages) {
        return new ResponseEntity<>(employeeService.fetchAllEmployeesByAges(ages), HttpStatus.OK);
    }

    @GetMapping("/search/student")
    public ResponseEntity<?> getAllStudent() {
        return new ResponseEntity<>(studentService.getAllStud(), HttpStatus.OK);
    }

    @GetMapping("/search/employees")
    public ResponseEntity<?> getAllEmp() {
        return new ResponseEntity<>(employeeService.getAllEmp(), HttpStatus.OK);
    }

    @GetMapping(value = "/search/student", params = "ids")
    public ResponseEntity<?> getStudentByIds(@RequestParam List<Integer> ids) {
        return new ResponseEntity<>(studentService.findStudById(ids), HttpStatus.OK);
    }


    private List<?> list;
    @GetMapping("/search/studentsandemployees")
    public ResponseEntity<?> multithreadingSearch() throws Exception {

        Thread studentThread = new Thread(() -> {
            try {
                list = studentService.getAllStud();
            } catch (Exception e) {
                System.out.println("Exception caught here when running student service: " + e);
            }
        });

        Thread empThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                list = employeeService.getAllEmp();
            } catch (Exception e) {
                System.out.println("Exception caught here when running employee service: " + e);
            }
        });

        studentThread.start();
        empThread.start();

        studentThread.join();
        empThread.join();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
