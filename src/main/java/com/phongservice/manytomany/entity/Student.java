package com.phongservice.manytomany.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.MERGE, orphanRemoval = true,fetch =  FetchType.LAZY)
    private List<StudentSubject> studentSubjects = new ArrayList<>();
}



//C1 Many to many
//    @ManyToMany
//    @JoinTable(
//            name = "student_subject",
//            joinColumns = @JoinColumn(name = "student_id"),  // khóa ngoại tới Student
//            inverseJoinColumns = @JoinColumn(name = "subject_id") // khóa ngoại tới Subject
//    )
//    private List<Subject> subjects;

//C2
//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<StudentSubject> studentSubjects = new ArrayList<>();

//C3