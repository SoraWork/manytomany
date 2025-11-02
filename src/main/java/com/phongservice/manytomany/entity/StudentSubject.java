package com.phongservice.manytomany.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_subject")
public class StudentSubject {
    //C3
    @EmbeddedId
    private StudentSubjectId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId") // ánh xạ field studentId trong EmbeddedId
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subjectId") // ánh xạ field subjectId trong EmbeddedId
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private Double score;
    private LocalDateTime examDate;

}

//    //C2
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private String id;
//
//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;
//
//    @ManyToOne
//    @JoinColumn(name = "subject_id", nullable = false)
//    private Subject subject;
//
//    private Double score;
//    private LocalDateTime examDate;
