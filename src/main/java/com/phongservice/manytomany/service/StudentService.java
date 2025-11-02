package com.phongservice.manytomany.service;

import com.phongservice.manytomany.dto.request.StudentRequest;
import com.phongservice.manytomany.dto.request.SubjectRequest;
import com.phongservice.manytomany.dto.response.StudentResponse;
import com.phongservice.manytomany.entity.Student;
import com.phongservice.manytomany.entity.Subject;
import com.phongservice.manytomany.entity.StudentSubject;
import com.phongservice.manytomany.entity.StudentSubjectId;
import com.phongservice.manytomany.mapper.StudentMapper;
import com.phongservice.manytomany.repository.StudentRepository;
import com.phongservice.manytomany.repository.SubjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentMapper studentMapper;


    public List<StudentResponse> getAll() {
        return studentRepository.findAllWithSubjects()
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public StudentResponse getById(String id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + id));
        return studentMapper.toResponse(student);
    }

    public StudentResponse addStudent(StudentRequest studentRequest) {
        Student student = studentMapper.toEntity(studentRequest);

        List<StudentSubject> studentSubjects = new ArrayList<>();

        for (SubjectRequest subjectReq : studentRequest.getSubjectRequests()) {
            Subject subject = subjectRepository.findById(subjectReq.getSubjectId())
                    .orElseGet(() -> {
                        // 🔹 Nếu chưa có môn học -> tạo mới
                        Subject newSubject = new Subject();
                        newSubject.setId(subjectReq.getSubjectId());
                        newSubject.setSubjectName(subjectReq.getName());
                        return subjectRepository.save(newSubject);
                    });

            StudentSubject studentSubject = new StudentSubject();
            studentSubject.setId(new StudentSubjectId(student.getId(), subject.getId()));
            studentSubject.setStudent(student);
            studentSubject.setSubject(subject);
            studentSubject.setScore(0.0); // có thể set mặc định
            studentSubjects.add(studentSubject);
        }

        student.setStudentSubjects(studentSubjects);
        Student saved = studentRepository.save(student);
        return studentMapper.toResponse(saved);
    }
}






