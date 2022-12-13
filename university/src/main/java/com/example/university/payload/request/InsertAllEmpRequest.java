package com.example.university.payload.request;

import com.example.university.domain.entity.Employee;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsertAllEmpRequest {
    List<Employee> employeeList;

}
