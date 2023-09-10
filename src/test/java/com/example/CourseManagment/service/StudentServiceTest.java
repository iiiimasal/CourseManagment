package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.LessonsRepository;
import com.example.CourseManagment.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@DataJpaTest
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
class StudentServiceTest {
    @InjectMocks
    private StudentService underTest;
    @InjectMocks
    private LessonService underTestLesson;
    @Mock private StudentRepository studentRepository;
    @Mock private LessonsRepository lessonsRepository;
    @Mock private DepartmentRepository departmentRepository;
    @Captor private ArgumentCaptor<Student> studentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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
//        String name =testStudent.getFirstname();
//        when(studentRepository. existsByFirstname((testStudent.getFirstname())).thenReturn(true);
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
    void deleteStudent_StudentDoesNotExist() {
        // Arrange
        Long studentId = 1L;

        // Mock behavior
        when(studentRepository.existsById(studentId)).thenReturn(false);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> underTest.deletStudent(studentId));

        // Verify that deleteById is never called
        verify(studentRepository, never()).deleteById(studentId);
    }

    @Test
    void deleteStudent_CreateAndThenDelete() {
        // Arrange
        //given
        Student testStudent = new Student(
                "Alex",
                "pit",
                144,
                "Canada"
        );

        // Create a student (you may need to create a student object here)

        // Mock behavior
        when(studentRepository.existsById(testStudent.getId())).thenReturn(true);

        // Act
        underTest.deletStudent(testStudent.getId());

        // Assert
        verify(studentRepository).deleteById(testStudent.getId());
    }


    @Test
    void updateOfStudent_SuccessfullyUpdateFullNameAndAdditionalFields() {
        // Arrange
        //Long studentId = 1L;
        String newName = "Alice";
        String newLastname = "Johnson";
        long newNationalNum = 45131;
        String newAddress = "123 Main St";

        Student existingStudent = new Student("Bob", "Smith", 55455555, "456 Elm St");

        when(studentRepository.findById(existingStudent.getId())).thenReturn(Optional.of(existingStudent));

        // Act
        underTest.updateNameOfStudent(existingStudent.getId(), newName, newLastname, newNationalNum, newAddress);

        // Assert
        assertEquals(newName, existingStudent.getFirstname());
        assertEquals(newLastname, existingStudent.getLastname());
        assertEquals(newNationalNum, existingStudent.getNationalNum());
        assertEquals(newAddress, existingStudent.getAddress());
        verify(studentRepository).save(existingStudent);
    }
    @Test
    void updateOfStudent_StudentNotFound() {
        // Arrange
        Long studentId = 1L;
        String newName = "Alice";
        String newLastname = "Johnson";
        long newNationalNum = 1234567890;
        String newAddress = "123 Main St";

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalStateException.class, () ->
                underTest.updateNameOfStudent(studentId, newName, newLastname, newNationalNum, newAddress));
        verify(studentRepository, never()).save(any());
    }
    @Test
    void addDepartment_StudentAndDepartmentExist() {
        String departmentName = "Computer Science";
        Student student = new Student(
                1L,
                "Alex",
                "pit",
                1444,
                "Canada"
        );

        Department department = new Department(departmentName);

//the rest of the test

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(departmentRepository.findById(department.getDepartmentName())).thenReturn(Optional.of(department));
        ArgumentCaptor<Student> studentArgumentCaptor=ArgumentCaptor.forClass(Student.class);
// Act
        underTest.addDepartment(student.getId(), departmentName);

// Assert
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertEquals(department, capturedStudent.getDepartment());
    }


    @Test
    void addGrade() {


    }

    @Test
    void getAverageGrade() {
    }
}