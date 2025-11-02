package com.phongservice.manytomany.controller;

import com.phongservice.manytomany.dto.request.SubjectRequest;
import com.phongservice.manytomany.dto.response.SubjectRespnse;
import com.phongservice.manytomany.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    @GetMapping
    public ResponseEntity<List<SubjectRespnse>> getAllSubjects() {
        List<SubjectRespnse> subjects = subjectService.getAll();
        return ResponseEntity.ok(subjects);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SubjectRespnse> getSubjectById(@PathVariable String id) {
        SubjectRespnse response = subjectService.getById(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<SubjectRespnse> addSubject(@RequestBody SubjectRequest request) {
        SubjectRespnse response = subjectService.addSubject(request);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SubjectRespnse> updateSubject(
            @PathVariable String id,
            @RequestBody SubjectRequest request
    ) {
        SubjectRespnse response = subjectService.updateSubject(id, request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
}
