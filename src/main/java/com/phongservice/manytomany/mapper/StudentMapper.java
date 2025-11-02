package com.phongservice.manytomany.mapper;



import com.phongservice.manytomany.dto.request.StudentRequest;
import com.phongservice.manytomany.dto.response.StudentResponse;
import com.phongservice.manytomany.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentSubjectMapper.class})
public interface StudentMapper {

    @Mapping(source = "studendtId", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "subjectRequests",target = "studentSubjects")
    Student toEntity(StudentRequest request);

    @Mapping(source = "id", target = "studentId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "studentSubjects", target = "subjects")
    StudentResponse toResponse(Student entity);

}
