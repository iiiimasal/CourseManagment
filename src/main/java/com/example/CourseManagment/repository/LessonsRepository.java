package com.example.CourseManagment.repository;

import com.example.CourseManagment.entity.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonsRepository extends JpaRepository<Lessons, String> {

    Optional<Lessons> findByLessonName(String s);
}
