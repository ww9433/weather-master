package com.example.university.respository.impl;

import com.example.university.domain.entity.Teacher;
import com.example.university.domain.entity.TeacherStudent;
import com.example.university.respository.TeacherStudentCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TeacherStudentCustomRepoImpl implements TeacherStudentCustomRepo {
    private final EntityManager entityManager;

    @Autowired
    public TeacherStudentCustomRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public TeacherStudent insert(TeacherStudent teacherStudent) {
        entityManager.persist(teacherStudent);
        return teacherStudent;
    }
}
