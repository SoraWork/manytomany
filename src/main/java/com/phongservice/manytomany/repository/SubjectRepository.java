package com.phongservice.manytomany.repository;

import com.phongservice.manytomany.entity.Subject;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String>, JpaSpecificationExecutor<Subject> {
//    @Query("SELECT DISTINCT s FROM Subject s " +
//            "LEFT JOIN FETCH s.studentSubjects ss " +
//            "LEFT JOIN FETCH ss.student")
//    List<Subject> findAllWithStudents();

//    @EntityGraph(value = "OK", type = EntityGraph.EntityGraphType.LOAD)
    @EntityGraph(attributePaths = "studentSubjects", type = EntityGraph.EntityGraphType.FETCH)
    List<Subject> findAll();
}
