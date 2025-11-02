package com.phongservice.manytomany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRespnse {
    private String subjectId;
    private String subjectName;
    private List<StudentResponse> studentRespnseList;
}
