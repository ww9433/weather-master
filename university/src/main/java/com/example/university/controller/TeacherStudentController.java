package com.example.university.controller;

import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Student;
import com.example.university.domain.entity.Teacher;
import com.example.university.domain.entity.TeacherStudent;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.payload.request.InsertRelationRequest;
import com.example.university.payload.request.InsertRequest;
import com.example.university.payload.request.UpdateRelationRequest;
import com.example.university.payload.request.UpdateRequest;
import com.example.university.payload.response.DeleteResponse;
import com.example.university.payload.response.UpdateResponse;
import com.example.university.service.TeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

//crud

@RestController
@RequestMapping("/relation")
public class TeacherStudentController {

    private final TeacherStudentService teacherStudentService;

    @Autowired
    public TeacherStudentController(TeacherStudentService teacherStudentService) {
        this.teacherStudentService = teacherStudentService;
    }

    @PostMapping("/insert")
    public ResponseEntity<CommonResponse> insert(@RequestBody InsertRelationRequest insertRelationRequest) {
        System.out.println(insertRelationRequest.getStuId());
        System.out.println(insertRelationRequest.getTId());
        return new ResponseEntity<>(teacherStudentService.insert(insertRelationRequest.getStuId(), insertRelationRequest.getTId()), HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CommonResponse> findStuById(@PathVariable String id) {
        return new ResponseEntity<>(teacherStudentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<CommonResponse> findAll() {
        return new ResponseEntity<>(teacherStudentService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/update")
    public UpdateResponse put(@RequestBody UpdateRelationRequest updateRelationRequest) {
        TeacherStudent relation = new TeacherStudent();
        Teacher teacher = updateRelationRequest.getTeacher();
        Student student = updateRelationRequest.getStudent();

        relation.setId(updateRelationRequest.getId());
        relation.setTeacher(teacher);
        relation.setStu(student);

        TeacherStudent newRelation = teacherStudentService.put(relation);

        if (newRelation != null) {
            return UpdateResponse.builder().status(true).object(newRelation).build();
        } else {
            return UpdateResponse.builder().status(false).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteById(@PathVariable String id) {
        if (!teacherStudentService.findById(id).equals(Optional.empty())) {
            TeacherStudent teacherStudent = (TeacherStudent) teacherStudentService.findById(id).getContent();
            teacherStudentService.deleteById(id);
            return DeleteResponse.builder().status(true).object(teacherStudent).build();
        } else {
            return DeleteResponse.builder().status(false).build();
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CommonResponse> handleNotFound() {
        return new ResponseEntity<>(
                new CommonResponse(-1, new Date(), "resource not found"),
                HttpStatus.NOT_FOUND
        );
    }


}
