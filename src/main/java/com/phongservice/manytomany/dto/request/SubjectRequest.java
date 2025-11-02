package com.phongservice.manytomany.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequest {
    private String subjectId;
    private String name;
    private List<StudentRequest> studentRequestList;
}
