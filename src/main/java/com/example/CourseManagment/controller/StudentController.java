package com.example.CourseManagment.controller;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/courseMangment/STUDENT")
public class StudentController {
    private final StudentService StudentService;
    private DepartmentRepository DepartmentRepository;

    @Autowired
    public StudentController(StudentService StudentService) {
        this.StudentService = StudentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return StudentService.getStudents();
    }

    //    @PostMapping
//    public void registerNewStudent(@RequestBody Student student, @RequestParam String department_name) {
//        Department department = DepartmentRepository.findBydepartment_name(department_name)
//                .orElseThrow(() -> new IllegalArgumentException("Department with name '" + department_name + "' not found."));
//
//        student.setDepartment(department); // Set the associated Department for the Student
//        StudentService.addNewStudent(student);
//    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        StudentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{id}")
    public void RemoveStudent(@PathVariable Long id) {
        StudentService.DeletStudent(id);

    }

    @PutMapping(path = "{id}")
    public void updatestudent(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String lesson) {
        StudentService.AddLesson(id, lesson);
    }

//    @PutMapping("{id}")
//    public void updateStudent(@PathVariable Long id, @RequestBody List<String> lessonNames) {
//        StudentService.AddLessons(id, lessonNames);
//    }
}
