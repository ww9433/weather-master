package com.example.university.controller;

import com.example.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniversityController {
    @Value("${server.port}")
    private int randomServerPort;


    private final StudentService studentService;



    @Autowired
    public UniversityController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/university/port")
    public ResponseEntity<?> checkPort() {
        return new ResponseEntity<>("university service + " + randomServerPort, HttpStatus.OK);
    }

    @GetMapping(value = "/university/student", params = "all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/university/student")
    public ResponseEntity<?> findById(@RequestParam String id){ return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK); }
}
