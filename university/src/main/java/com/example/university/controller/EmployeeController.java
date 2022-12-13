package com.example.university.controller;

import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Employee;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.payload.request.InsertAllEmpRequest;
import com.example.university.payload.request.InsertEmpRequest;
import com.example.university.payload.request.InsertRequest;
import com.example.university.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmpService empService;

    @Autowired
    public EmployeeController(EmpService empService) {
        this.empService = empService;
    }

//    @PostMapping()
//    @RequestMapping(params = "all")
//    public ResponseEntity<CommonResponse> insertAll(@RequestBody InsertAllEmpRequest insertAllEmpRequest) {
//        return new ResponseEntity<>(empService.insertAll(insertAllEmpRequest.getEmployeeList()), HttpStatus.CREATED);
//    }
//
//    @PostMapping()
//    public ResponseEntity<CommonResponse> insert(@RequestBody InsertEmpRequest insertEmpRequest) {
//        return new ResponseEntity<>(empService.insert(insertEmpRequest.getEmployee()), HttpStatus.CREATED);
//    }

    @GetMapping(params = "all")
    public ResponseEntity<CommonResponse> findAll() {
        return new ResponseEntity<>(empService.findAll(), HttpStatus.OK);
    }

    @GetMapping(params = {"age"})
    public ResponseEntity<CommonResponse> empAgeGreaterThanByRestAPI(@RequestParam int age) {
        return new ResponseEntity<>(empService.empAgeGreaterThanByRestAPI(age), HttpStatus.OK);
    }

    @GetMapping(params = {"age", "all"})
    public ResponseEntity<CommonResponse> empAgeGreaterThan(@RequestParam int age) {
        return new ResponseEntity<>(empService.empAgeGreaterThan(age), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> findEmpById(@PathVariable String id) {
        return new ResponseEntity<>(empService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> updateEmpById(@PathVariable String id, @RequestBody Employee employee) {
        return new ResponseEntity<>(empService.updateById(id, employee), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<CommonResponse> findAllByRestAPI() {
        return new ResponseEntity<>(empService.findAllByRestAPI(), HttpStatus.OK);
    }



//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<CommonResponse> handleNotFound() {
//        return new ResponseEntity<>(
//                new CommonResponse(-1, new Date(), "resource not found"),
//                HttpStatus.NOT_FOUND
//        );
//    }
}
