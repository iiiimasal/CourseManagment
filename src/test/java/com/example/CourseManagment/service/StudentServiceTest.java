package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
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
       //given
        Student testStudent = new Student(
                "Alex",
                "pit",
                144,
                "Canada"
        );
        underTest.addNewStudent(testStudent);
        ArgumentCaptor<Student> studentArgumentCaptor=ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(testStudent);
    }


        @Test
        void deleteStudent_StudentExists() {
            // Arrange
            Long studentId = 1L;

            // Mock behavior
            when(studentRepository.existsById(studentId)).thenReturn(true);

            // Act
            underTest.deletStudent(studentId);

            // Assert
            verify(studentRepository).deleteById(studentId);
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