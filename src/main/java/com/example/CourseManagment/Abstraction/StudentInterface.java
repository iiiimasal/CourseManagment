package com.example.CourseManagment.Abstraction;

import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.StudentRepository;

import java.util.List;

public interface StudentInterface  {
    StudentRepository studentRepository = null;
    public  List<Student> getStudents() ;
    public void addNewStudent(Student student);
    public void deleteStudent(Long id) ;
    public void AddLesson(Long id, String lesson, Long professor);
    public void updateNameOfStudent(Long id,
                                    String newName,
                                    String newLastname,
                                    Long newNationalNum,
                                    String newAddress);
    public void addDepartment(Long id, String departmentName);
    public void addGrade(Long id, String lessonName, Float grade);
    public float getAverageGrade(Long id);

}
