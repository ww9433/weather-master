package com.example.university.service.impl;

import com.example.university.domain.CommonResponse;
import com.example.university.domain.entity.Student;
import com.example.university.domain.entity.Teacher;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.respository.StudentRepository;
import com.example.university.respository.TeacherRepository;
import com.example.university.service.StudentService;
import com.example.university.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public CommonResponse findById(String id) {
        Optional<Teacher> tea =  teacherRepository.findById(id);
        if(tea.isEmpty()) {
            //log
            throw new ResourceNotFoundException("...");
        }
        return new CommonResponse(0, new Date(), tea.get());
    }

    @Override
    public CommonResponse findAll() {
        List<Teacher> stuList =  teacherRepository.findAll();
        return new CommonResponse(0, new Date(), stuList);
    }

    @Override
    @Transactional
    public CommonResponse insert(Teacher teacher) {
        teacherRepository.save(teacher);
        return new CommonResponse(0, new Date(), teacher.getId());
    }

    @Override
    public Teacher put(String id, Teacher teacher) {
        Optional<Teacher> preTeach = teacherRepository.findById(id);

        if (preTeach.isPresent()) {
            preTeach.get().setName(teacher.getName());

            teacherRepository.saveAndFlush(preTeach.get());
            return preTeach.get();
        } else {
            teacherRepository.save(teacher);
            return teacher;
        }
    }

    @Override
    public CommonResponse deleteById(String id) {
        Teacher teacher = (Teacher) teacherRepository.findById(id).get();
        if (teacher.equals(Optional.empty())) {
            teacherRepository.deleteById(id);
        }
        return new CommonResponse(0, new Date(), teacher);
    }
}
