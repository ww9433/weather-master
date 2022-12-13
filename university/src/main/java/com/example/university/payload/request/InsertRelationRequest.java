package com.example.university.payload.request;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsertRelationRequest {
    String stuId;
    String tId;
}
