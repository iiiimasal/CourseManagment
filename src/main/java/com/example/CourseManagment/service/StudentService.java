package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.entity.Student;
//import com.example.CourseManagment.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.LessonsRepository;
import com.example.CourseManagment.repository.ProfessorsRepository;
import com.example.CourseManagment.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;
    LessonsRepository lessonsRepository;
    ProfessorsRepository professorsRepository;
    DepartmentRepository departmentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, LessonsRepository lessonsRepository,
                          ProfessorsRepository professorsRepository,
                          DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.lessonsRepository=lessonsRepository;
        this.professorsRepository=professorsRepository;
        this.departmentRepository=departmentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        if (studentRepository.existsById(student.getNationalNum())){
            throw new IllegalStateException("Already student exists");
        }
        studentRepository.save(student);

    }

    public void DeletStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id " + id + "does not exists");

        }
        studentRepository.deleteById(id);
    }

    public void AddLesson(Long id, String lesson , Long professor) {

        Student student=studentRepository.findById(id).orElseThrow(()-> new IllegalStateException(
                "student with id "+id+"does not exist"
        ));

        Lessons newLesson=lessonsRepository.findById(lesson).orElseThrow(()-> new IllegalStateException(
                "Lesson "+lesson+"does not exist"
        ));

        Professers professoRequiredr=professorsRepository.findById(professor).orElseThrow(()-> new IllegalStateException(
                "professor with id "+professor+"does not exist"
        ));

            if(!(student.getDepartment()==newLesson.getDepartment()&& professoRequiredr.getprofessorLessons().contains(newLesson))) {
                throw new IllegalStateException("The professor is not for the lesson's department");
            }
            else {
                if (student.getLessons() != null) {

                    student.getLessons().add(newLesson);
                }
                studentRepository.save(student);
            }
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

    public void addDepartment(Long id, String departmentName) {
        Student student=studentRepository.findById(id).orElseThrow(()-> new IllegalStateException(
                "student with id "+id+"does not exist"
        ));
        Department department=departmentRepository.findById(departmentName).orElseThrow(()-> new IllegalStateException(
                "Department "+departmentName+"does not exists"
        ));
        student.setDepartment(department);
        studentRepository.save(student);
    }
}






