package com.phongservice.manytomany.mapper;


import com.phongservice.manytomany.dto.response.StudentResponse;
import com.phongservice.manytomany.dto.response.SubjectRespnse;
import com.phongservice.manytomany.entity.StudentSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentSubjectMapper {

    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "subject.subjectName", target = "subjectName")
    SubjectRespnse toSubjectResponse(StudentSubject entity);
    List<SubjectRespnse> toSubjectResponseList(List<StudentSubject> entities);

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "student.name", target = "name")
    StudentResponse toStudentResponse(StudentSubject entity);
    List<StudentResponse> toStudentResponseList(List<StudentSubject> entities);
}
