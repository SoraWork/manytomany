package com.phongservice.manytomany.mapper;

import com.phongservice.manytomany.dto.request.SubjectRequest;
import com.phongservice.manytomany.dto.response.SubjectRespnse;
import com.phongservice.manytomany.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentSubjectMapper.class})
public interface SubjectMapper {

    @Mapping(source = "subjectId", target = "id")
    @Mapping(source = "name", target = "subjectName")
    @Mapping(source = "studentRequestList", target = "studentSubjects")
    Subject toEntity(SubjectRequest subjectRequest);

    @Mapping(source = "id", target = "subjectId")
    @Mapping(source = "subjectName", target = "subjectName")
    @Mapping(source = "studentSubjects", target = "studentRespnseList")
    SubjectRespnse toResponse(Subject subject);

}
