package com.example.university.controller;


import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Student;
import com.example.university.domain.entity.Teacher;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.payload.request.InsertRequest;
import com.example.university.payload.request.UpdateRequest;
import com.example.university.payload.response.DeleteResponse;
import com.example.university.payload.response.UpdateResponse;
import com.example.university.service.StudentService;
import com.example.university.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> findStuById(@PathVariable String id) {
        return new ResponseEntity<>(teacherService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello World " + name;
    }

    @GetMapping("/hello1")
    public String hello1(@RequestParam String name) {
        return "Hello World " + name;
    }

    @GetMapping(params = "all")
    public ResponseEntity<CommonResponse> findAll() {
        return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK);
    }

    //path: /student, method: post ,  create student
    @PostMapping("")
    public ResponseEntity<CommonResponse> insert(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(teacherService.insert(teacher), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Teacher teacher) {

        Teacher newTeach = teacherService.put(id, teacher);

        return new ResponseEntity<>(teacherService.put(id, teacher), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        return new ResponseEntity<>(teacherService.deleteById(id), HttpStatus.OK);
    }



    //TODO: /{id} , method : put,  update student


//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<CommonResponse> handleNotFound() {
//        return new ResponseEntity<>(
//                new CommonResponse(-1, new Date(), "resource not found"),
//                HttpStatus.NOT_FOUND
//        );
//    }
}
