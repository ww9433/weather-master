package com.example.university.respository;

import com.example.university.domain.entity.Employee;
import com.example.university.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("select e from Employee e where e.age > ?1")
    List<Employee> empAgeGreaterThan(int target);
}
