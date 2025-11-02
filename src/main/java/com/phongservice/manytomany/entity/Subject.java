package com.phongservice.manytomany.entity;

import jakarta.persistence.*;
import lombok.*;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject")
//@NamedEntityGraph(
//        name = "OK",
//        attributeNodes = @NamedAttributeNode("studentSubjects")
//)
@NamedEntityGraph(
        name = "OK",
        attributeNodes = @NamedAttributeNode(value = "studentSubjects", subgraph = "students"),
        subgraphs = @NamedSubgraph(
                name = "students",
                attributeNodes = @NamedAttributeNode("student")
        )
)
public class Subject {
    @Id
    private String id;
    private String subjectName;

    //C3
    @OneToMany(mappedBy = "subject", cascade = CascadeType.MERGE, orphanRemoval = true,fetch =  FetchType.LAZY)
    private List<StudentSubject> studentSubjects = new ArrayList<>();
}



//C1
//    @ManyToMany(mappedBy = "subjects")
//    private List<Student> students;


//C2
//    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<StudentSubject> studentSubjects = new ArrayList<>();
