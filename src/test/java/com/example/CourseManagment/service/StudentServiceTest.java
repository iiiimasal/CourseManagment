package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.LessonsRepository;
import com.example.CourseManagment.repository.ProfessorsRepository;
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
import java.util.List;
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
    @InjectMocks
    private  ProfessorsService underTestProffessor;
    @Mock private StudentRepository studentRepository;
    @Mock private LessonsRepository lessonsRepository;
    @Mock private DepartmentRepository departmentRepository;
    @Mock private ProfessorsRepository professorsRepository;
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
        Student student = new Student(
                "Alex",
                "pit",
                144,
                "Canada"
        );
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        verify(studentRepository).save(studentArgumentCaptor.capture());
        // Act
        underTest.deletStudent(student.getId());

        // Capture the saved student after the deletion
        //verify(studentRepository).save(studentArgumentCaptor.capture());

        // Assert
        Student capturedStudent = studentArgumentCaptor.getValue();
        verify(studentRepository).deleteById(capturedStudent.getId());
    }


    @Test
    void updateOfStudent_SuccessfullyUpdateFullNameAndAdditionalFields() {
        // Arrange
        Student student = new Student(
                1L,
                "Alex",
                "pit",
                1444,
                "Canada"
        );

        String newName = "Alice";
        String newLastname = "Johnson";
        long newNationalNum = 45131;
        String newAddress = "123 Main St";
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        ArgumentCaptor<Student> studentArgumentCaptor=ArgumentCaptor.forClass(Student.class);
// Act


        // Act
        underTest.updateNameOfStudent(student.getId(), newName, newLastname, newNationalNum, newAddress);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        // Assert
        assertEquals(newName, capturedStudent.getFirstname());
        assertEquals(newLastname, capturedStudent.getLastname());
        assertEquals(newNationalNum, capturedStudent.getNationalNum());
        assertEquals(newAddress, capturedStudent.getAddress());
        //verify(studentRepository).save(student);
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
    private Student createStudentAndAddDepartment(String departmentName) {
        Student student = new Student(
                1L,
                "Alex",
                "pit",
                1444,
                "Canada"
        );

        Department department = new Department(departmentName);

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(departmentRepository.findById(department.getDepartmentName())).thenReturn(Optional.of(department));

        student.setDepartment(department);

        return student;
    }

    @Test
    void addDepartment_StudentAndDepartmentExist() {
        String departmentName = "Computer Science";
        Student student = createStudentAndAddDepartment(departmentName);

        underTest.addDepartment(student.getId(), departmentName);

        verify(studentRepository).save(studentCaptor.capture());
        Student capturedStudent = studentCaptor.getValue();
        assertEquals(student.getDepartment(), capturedStudent.getDepartment());
    }


    @Test
    public void addLesson() {
        String departmentName = "Computer Science";
        Student student = createStudentAndAddDepartment(departmentName);

        String lessonName = "experimentTest";
        Lessons lesson = new Lessons(lessonName, 1);
        Professers professor = new Professers(
                11111,
                "George",
                "Dan",
                12235
        );

        // Configure mock repository behaviors
        when(lessonsRepository.findById(lesson.getLessonName())).thenReturn(Optional.of(lesson));
        when(professorsRepository.findById(professor.getprofessorId())).thenReturn(Optional.of(professor));

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        // Act
        underTestProffessor.AddProfessorDepartment(professor.getprofessorId(), departmentName);
        underTestLesson.AddDepartment(lessonName, departmentName);
        underTestProffessor.AddLesson(professor.getprofessorId(), lessonName);

        underTest.AddLesson(student.getId(), lessonName, professor.getprofessorId());

        // Verify that specific saves were called
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();

        List<Lessons> studentLessons = capturedStudent.getLessons();
        assertEquals(1, studentLessons.size());
        Lessons capturedLesson = studentLessons.get(0);

        assertEquals(lesson, capturedLesson);
    }



    @Test
    void addGrade() {
        String departmentName = "Computer Science";
        Student student = createStudentAndAddDepartment(departmentName);

        String lessonName = "experimentTest";
        Lessons lesson = new Lessons(lessonName, 1);
        Professers professor = new Professers(
                11111,
                "George",
                "Dan",
                12235
        );

        // Configure mock repository behaviors
        when(lessonsRepository.findById(lesson.getLessonName())).thenReturn(Optional.of(lesson));
        when(professorsRepository.findById(professor.getprofessorId())).thenReturn(Optional.of(professor));

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        // Act
        underTestProffessor.AddProfessorDepartment(professor.getprofessorId(), departmentName);
        underTestLesson.AddDepartment(lessonName, departmentName);
        underTestProffessor.AddLesson(professor.getprofessorId(), lessonName);

        underTest.AddLesson(student.getId(), lessonName, professor.getprofessorId());
        verify(studentRepository).save(studentArgumentCaptor.capture());

        //underTest.addGrade(student.getId(),lessonName,12f);



    }

    @Test
    void getAverageGrade() {
    }
}