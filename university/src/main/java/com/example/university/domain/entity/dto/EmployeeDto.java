package com.example.university.domain.entity.dto;

import com.example.university.domain.entity.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private Employee[] empData;
}
