package com.example.CourseManagment.repository;

import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfessorsRepository extends JpaRepository<Professers, Long> {
    @Query("SELECT d FROM Department d WHERE d.departmentName= ?1")
    Optional<Student> existsBydepartmentName(Long id);
}
