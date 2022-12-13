package com.example.university.respository;

import com.example.university.domain.entity.TeacherStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherStudentRepository extends JpaRepository<TeacherStudent, String>, TeacherStudentCustomRepo {
}
