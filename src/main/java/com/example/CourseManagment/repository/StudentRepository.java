package com.example.CourseManagment.repository;

import com.example.CourseManagment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student> findstudentByid(Long id);

}
