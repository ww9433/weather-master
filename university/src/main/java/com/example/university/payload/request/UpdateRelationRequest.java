package com.example.university.payload.request;


import com.example.university.domain.entity.Student;
import com.example.university.domain.entity.Teacher;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRelationRequest {
    String id;
    Student student;

    Teacher teacher;
}
