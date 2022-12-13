package com.example.university.payload.response;

import com.example.university.domain.entity.Student;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateResponse {
    boolean status;
    Object object;
}
