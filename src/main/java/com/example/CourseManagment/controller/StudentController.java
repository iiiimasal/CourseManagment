package com.example.CourseManagment.controller;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.service.StudentService;
import jdk.jfr.Frequency;
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


    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        StudentService.addNewStudent(student);
    }


    ///define the department for current student
    @PostMapping(path = "{id}")
    public  void addDepartmnet(@PathVariable Long id,
                               @RequestParam(required = false) String department){
        StudentService.addDepartment(id , department);
    }
    @DeleteMapping(path = "{id}")
    public void RemoveStudent(@PathVariable Long id) {
        StudentService.DeletStudent(id);

    }

    //add lesson to the specified student by its id
    @PostMapping(path = "{id}/lesson")
    public void updatestudent(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String lessonName,
            @RequestParam(required = false) Long professor_id
            ) {
        StudentService.AddLesson(id, lessonName , professor_id);
    }
    //Update the changes required for the current student
    @PutMapping(path  ="{id}")
    public void updateNameOfStudent(
            @PathVariable("id") Long id,
            @RequestParam() String newName,
            @RequestParam() String newLastname){
        StudentService.updateNameOfStudent(id,newName,newLastname);
    }
}
