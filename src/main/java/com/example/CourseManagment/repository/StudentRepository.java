package com.example.CourseManagment.repository;

import com.example.CourseManagment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student> findstudentByid(Long id);




    Student findByFirstnameContaining(String firstname);



    boolean existsByFirstname(String firstName);
}
