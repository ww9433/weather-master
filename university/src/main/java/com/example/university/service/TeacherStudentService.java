package com.example.university.service;

import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.TeacherStudent;
import org.springframework.stereotype.Service;

@Service
public interface TeacherStudentService {
    CommonResponse insert(String sId, String tId);

    CommonResponse findById(String id);

    CommonResponse findAll();

    TeacherStudent put(TeacherStudent teacherStudent);

    void deleteById(String id);
}
