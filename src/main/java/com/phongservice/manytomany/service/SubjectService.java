package com.phongservice.manytomany.service;

import com.phongservice.manytomany.dto.request.StudentRequest;
import com.phongservice.manytomany.dto.request.SubjectRequest;
import com.phongservice.manytomany.dto.response.SubjectRespnse;
import com.phongservice.manytomany.entity.Student;
import com.phongservice.manytomany.entity.StudentSubject;
import com.phongservice.manytomany.entity.StudentSubjectId;
import com.phongservice.manytomany.entity.Subject;
import com.phongservice.manytomany.mapper.SubjectMapper;
import com.phongservice.manytomany.repository.StudentRepository;
import com.phongservice.manytomany.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectMapper subjectMapper;

    public List<SubjectRespnse> getAll() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SubjectRespnse getById(String id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found: " + id));
        return subjectMapper.toResponse(subject);
    }

    public SubjectRespnse addSubject(SubjectRequest request) {
        Subject subject = subjectMapper.toEntity(request);

        List<StudentSubject> studentSubjects = new ArrayList<>();

        for (StudentRequest studentRequest : request.getStudentRequestList()){
            Student student = studentRepository.findById(studentRequest.getStudendtId())
                    .orElseGet( () -> {
                        Student newStudent = new Student();
                        newStudent.setId(studentRequest.getStudendtId());
                        newStudent.setName(studentRequest.getName());
                        return studentRepository.save(newStudent);
                    });
            StudentSubject studentSubject = new StudentSubject();
            studentSubject.setId(new StudentSubjectId(student.getId(), subject.getId()));
            studentSubject.setSubject(subject);
            studentSubject.setStudent(student);
            studentSubjects.add(studentSubject);
        }
        subject.setStudentSubjects(studentSubjects);
        Subject save =  subjectRepository.save(subject);
        return subjectMapper.toResponse(save);
    }

    public SubjectRespnse updateSubject(String id, SubjectRequest request) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found: " + id));
        subject.setSubjectName(request.getName());
        Subject updated = subjectRepository.save(subject);
        return subjectMapper.toResponse(updated);
    }

    public void deleteSubject(String id) {
        if (!subjectRepository.existsById(id)) {
            throw new EntityNotFoundException("Subject not found: " + id);
        }
        subjectRepository.deleteById(id);
    }
}
