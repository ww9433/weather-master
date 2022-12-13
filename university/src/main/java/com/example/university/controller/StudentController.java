package com.example.university.controller;

import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Student;
import com.example.university.domain.entity.Teacher;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.payload.request.UpdateRequest;
import com.example.university.payload.request.InsertRequest;
import com.example.university.payload.response.DeleteResponse;
import com.example.university.payload.response.UpdateResponse;
import com.example.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findStuById(@PathVariable String id) {
//        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
//    }


    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello World " + name;
    }

    @GetMapping("/hello1")
    public String hello1(@RequestParam String name) {
        return "Hello World " + name;
    }

//    @GetMapping(params = "all")
//    public ResponseEntity<CommonResponse> findAll() {
//        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
//    }

    //path: /student, method: post ,  create student
    @PostMapping()
    public ResponseEntity<?> insert(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.insert(student), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Student student) {
        Student newStu = studentService.put(id, student);

        if (newStu != null) {
            return new ResponseEntity<>(studentService.put(id, student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(studentService.put(id, student), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        return new ResponseEntity<>(studentService.deleteById(id), HttpStatus.OK);
    }

    //TODO: /{id} , method : put,  update student


//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<CommonResponse> handleNotFound() {
//        return new ResponseEntity<>(
//                    new CommonResponse(-1, new Date(), "resource not found"),
//                    HttpStatus.NOT_FOUND
//                );
//    }
}
