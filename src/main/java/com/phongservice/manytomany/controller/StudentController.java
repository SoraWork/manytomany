package com.phongservice.manytomany.controller;

import com.phongservice.manytomany.dto.request.StudentRequest;
import com.phongservice.manytomany.dto.response.StudentResponse;
import com.phongservice.manytomany.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> students = studentService.getAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable String id) {
        StudentResponse response = studentService.getById(id);
        System.out.println("smt change");
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentRequest request) {
        StudentResponse response = studentService.addStudent(request);
        return ResponseEntity.ok(response);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<StudentResponse> updateStudent(
//            @PathVariable String id,
//            @RequestBody StudentRequest request
//    ) {
//        StudentResponse response = studentService.updateStudent(id, request);
//        return ResponseEntity.ok(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
//        studentService.deleteStudent(id);
//        return ResponseEntity.noContent().build();
//    }
}
