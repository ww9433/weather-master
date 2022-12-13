package com.example.university.payload.response;


import com.example.university.domain.entity.Student;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponse {
    boolean status;
    Object object;
}
