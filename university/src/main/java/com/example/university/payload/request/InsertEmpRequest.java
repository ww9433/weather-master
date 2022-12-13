package com.example.university.payload.request;

import com.example.university.domain.entity.Employee;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsertEmpRequest {
    Employee employee;
}
