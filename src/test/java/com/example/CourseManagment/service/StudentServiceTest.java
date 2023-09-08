package com.example.CourseManagment.service;

import com.example.CourseManagment.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class StudentServiceTest {
@Mock StudentRepository studentRepository;
private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }
    @Test
    void getStudents() {
        underTest.getStudents();

        // Verify that the findAll method of the repository was called
        verify(studentRepository).findAll();
    }

    @Test
    void addNewStudent() {
    }

    @Test
    void deletStudent() {
    }

    @Test
    void addLesson() {
    }

    @Test
    void updateNameOfStudent() {
    }

    @Test
    void addDepartment() {
    }

    @Test
    void addGrade() {
    }

    @Test
    void getAverageGrade() {
    }
}