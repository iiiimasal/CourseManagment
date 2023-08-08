package com.example.CourseManagment.repository;

import com.example.CourseManagment.entity.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonsRepository extends JpaRepository<Lessons, String> {
}
