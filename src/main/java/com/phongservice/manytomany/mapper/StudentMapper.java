package com.phongservice.manytomany.dto.mapper;



import com.phongservice.manytomany.dto.request.StudentRequest;
import com.phongservice.manytomany.dto.response.StudentResponse;
import com.phongservice.manytomany.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "subjectRequests",target = "studentSubjects")
    Student toEntity(StudentRequest request);

    @Mapping(source = "id", target = "studentId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "studentSubjects", target = "subjectRespnse")
    StudentResponse toResponse(Student entity);

}
