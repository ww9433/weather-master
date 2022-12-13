package com.example.university.service;


import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Student;
import com.example.university.domain.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface TeacherService {
    CommonResponse findById(String id);
    CommonResponse findAll();
    CommonResponse insert(Teacher teacher);

    Teacher put(String id, Teacher teacher);

    CommonResponse deleteById(String id);
}
