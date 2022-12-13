package com.example.university.respository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class EmployeeCustomRepoImpl {//implements EmployeeCustomRepo {
    private final EntityManager entityManager;

    @Autowired
    public EmployeeCustomRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//    @Override
//    public Employee insert(Employee employee) {
//
//        entityManager.persist(employee);
//        return employee;
//    }

//    @Override
//    public List<Employee> empAgeGreaterThan(int target) {
//        Query query = entityManager.createQuery("select e from Employee e where e.age > ?1");
//        query.setParameter(1, target);
//        List<Employee> employeeList = query.getResultList();
//        return employeeList;
//    }


}
