package com.example.university.service.impl;


import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Student;
import com.example.university.domain.entity.Teacher;
import com.example.university.domain.entity.TeacherStudent;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.respository.StudentRepository;
import com.example.university.respository.TeacherRepository;
import com.example.university.respository.TeacherStudentRepository;
import com.example.university.service.TeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherStudentServiceImpl implements TeacherStudentService {
    private final TeacherStudentRepository teacherStudentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public TeacherStudentServiceImpl(TeacherStudentRepository teacherStudentRepository,
                                     TeacherRepository teacherRepository,
                                     StudentRepository studentRepository) {
        this.teacherStudentRepository = teacherStudentRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public CommonResponse insert(String sId, String tId) {
        TeacherStudent newRelation = new TeacherStudent();
        Teacher teacher = teacherRepository.getOne(tId);
        Student student = studentRepository.getOne(sId);

        newRelation.setTeacher(teacher);
        newRelation.setStu(student);

        TeacherStudent relation = teacherStudentRepository.insert(newRelation);
        return new CommonResponse(0, new Date(), relation);
    }


    @Override
    public CommonResponse findById(String id) {
        Optional<TeacherStudent> relation =  teacherStudentRepository.findById(id);
        if(relation.isEmpty()) {
            //log
            throw new ResourceNotFoundException("...");
        }
        return new CommonResponse(0, new Date(), relation.get());
    }

    @Override
    public CommonResponse findAll() {
        List<TeacherStudent> relationList =  teacherStudentRepository.findAll();
        return new CommonResponse(0, new Date(), relationList);
    }

    @Override
    public TeacherStudent put(TeacherStudent teacherStudent) {
        Optional<TeacherStudent> preRelation = teacherStudentRepository.findById(teacherStudent.getId());

        if (preRelation.isPresent()) {
            preRelation.get().setTeacher(teacherStudent.getTeacher());
            preRelation.get().setStu(teacherStudent.getStu());

            teacherStudentRepository.saveAndFlush(preRelation.get());
            return preRelation.get();
        } else {
            teacherStudentRepository.save(teacherStudent);
            return teacherStudent;
        }
    }

    @Override
    public void deleteById(String id) {
        teacherStudentRepository.deleteById(id);
    }

}
