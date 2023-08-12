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

        Lessons newLesson = required_lesson.get();


        if (student_previous_info.isPresent()) {

            Student student = student_previous_info.get();

            if (student.getLessons() != null) {

                student.getLessons().add(newLesson);
            }
            studentRepository.save(student);

        } else new IllegalStateException("Student does not exists.");

    }

@Transactional
    public void updateNameOfStudent(Long id, String newName, String newLastname) {
        Student student=studentRepository.findById(id).orElseThrow(()-> new IllegalStateException(
                "student with id "+id+"does not exist"
        ));
        if(newName!=null &&
                newName.length()>0 &&
                !Objects.equals(student.getFirstname(), newName)){ //if the name provided is not the same as the current one update
            student.setFirstname(newName);
        }
        if(newLastname!=null &&
                newLastname.length()>0 &&
                !Objects.equals(student.getLastname(), newLastname)){ //if the name provided is not the same as the current one update
            student.setLastname(newLastname);
        }
        studentRepository.save(student);
    }
}






