package com.phongservice.manytomany.repository;

import com.phongservice.manytomany.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>, JpaSpecificationExecutor<Student> {
    @Query("SELECT DISTINCT s FROM Student s " +
            "LEFT JOIN FETCH s.studentSubjects ss " +
            "LEFT JOIN FETCH ss.subject")
    List<Student> findAllWithSubjects();
//
//    @Query("SELECT s from ")
}
