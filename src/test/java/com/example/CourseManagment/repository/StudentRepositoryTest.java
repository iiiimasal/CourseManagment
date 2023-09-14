package com.example.CourseManagment.repository;

import com.example.CourseManagment.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository underTest;
    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
    @Test
    public void testFindStudentById() {
        // Given
        //long id = 40010133;
        Student testStudent = new Student(

                "Alex",
                "pit",
                144,
                "Canada"
        );
        underTest.save(testStudent);

        //when
        boolean exists=underTest.existsById(testStudent.getId());

        //then
        assertThat(exists).isTrue();
    }



}