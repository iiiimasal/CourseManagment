package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Student;
//import com.example.CourseManagment.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.example.CourseManagment.repository.LessonsRepository;
import com.example.CourseManagment.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;
    LessonsRepository lessonsRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, LessonsRepository lessonsRepository) {
        this.studentRepository = studentRepository;
        this.lessonsRepository=lessonsRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);

    }

    public void DeletStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id " + id + "does not exists");

        }
        studentRepository.deleteById(id);
    }

    public void AddLesson(Long id, String lesson) {

        Optional<Student> student_previous_info = studentRepository.findstudentByid(id);

        Optional<Lessons> required_lesson = lessonsRepository.findById(lesson);

        Lessons new_lesson = required_lesson.get();

        if (student_previous_info.isPresent()) {
            // Student found, so you can access it using student_previous_info.get()
            Student student = student_previous_info.get();

            if (student.getLessons() != null) {

                student.getLessons().add(new_lesson);
            }
            studentRepository.save(student);

        } else new IllegalStateException("Student does not exists.");

    }

//    public void AddLessons(Long id, List<String> lessonNames) {
//        Student student = studentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
//
//        // Create a new list to store the updated lessons
//        List<Lessons> updatedLessons = new ArrayList<>();
//
//        // Retrieve each lesson by name from the database and add it to the updatedLessons list
//        for (String lessonName : lessonNames) {
//            Lessons lesson = lessonsRepository.findById(lessonName)
//                    .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with name: " + lessonName));
//            updatedLessons.add(lesson);
//        }
//
//        // Set the updated lessons for the student and save the changes
//        student.setLessons(updatedLessons);
//        studentRepository.save(student);
//    }

    }






