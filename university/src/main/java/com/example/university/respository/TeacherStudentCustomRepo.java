package com.example.university.respository;


import com.example.university.domain.entity.TeacherStudent;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherStudentCustomRepo {
    TeacherStudent insert(TeacherStudent teacherStudent);
}
