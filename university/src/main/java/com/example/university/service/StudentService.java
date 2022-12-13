package com.example.university.service;

import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    CommonResponse findById(String id);
    CommonResponse findAll();
    CommonResponse insert(Student student);

    Student put(String id, Student student);

    CommonResponse deleteById(String id);
}
