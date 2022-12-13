package com.example.university.payload.response;

import com.example.university.domain.entity.Employee;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeGetResponse {
    private List<Employee> employees;

}
