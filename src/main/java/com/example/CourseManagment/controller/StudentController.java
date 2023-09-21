package com.example.CourseManagment.controller;

import com.example.CourseManagment.DTO.StudentDTO;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.StudentRepository;
import com.example.CourseManagment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/courseMangment/student")
public class StudentController {

    // Injecting the StudentService dependency using constructor injection
    private final StudentService studentService;


    @Autowired
    public StudentController(@Lazy StudentService studentService) {
        this.studentService = studentService;

    }

    // Endpoint to get a list of all students
//    @GetMapping
//    public Page<Student> getStudents(
//            @RequestParam Optional<Integer> page,
//            @RequestParam Optional<String> sortBy
//    ) {
//        return studentRepository.findAll(
//                PageRequest.of(
//                        page.orElse(0),  //if there  weren't and page assigned start from zero including two records
//                        2,
//                        Sort.Direction.ASC,sortBy.orElse("id")
//
//                )
//
//        );
//    }
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }


    // Endpoint to register a new student
    @PostMapping
    public void registerNewStudent(@RequestBody StudentDTO studentDTO) {
        Student student=new Student(studentDTO.getFirstname(),studentDTO.getLastname(),
                studentDTO.getNationalNum(),studentDTO.getAddress());

        studentService.addNewStudent(student);
    }

    // Endpoint to define the department for a current student
    @PostMapping(path = "{id}")
    public void addDepartment(@PathVariable Long id,
                              @RequestParam(required = false) String department) {
        studentService.addDepartment(id, department);
    }

    // Endpoint to remove a student
    @DeleteMapping(path = "{id}")
    public void RemoveStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    // Endpoint to add a lesson to a student by its id
    @PostMapping(path = "{id}/lesson")
    public void addLessons(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String lessonName,
            @RequestParam(required = false) Long professorId
    ) {
        studentService.AddLesson(id, lessonName, professorId);
    }

    // Endpoint to add a grade for a lesson of a student
    @PutMapping(path = "{id}/grade")
    public void addGrade(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String lessonName,
            @RequestParam(required = false) Float grade
    ) {
        studentService.addGrade(id, lessonName, grade);
    }

    // Endpoint to update the changes (name and lastname) required for the current student
    @PutMapping(path = "{id}")
    public void updateNameOfStudent(
            @PathVariable("id") Long id,
            @RequestParam() String newName,
            @RequestParam() String newLastname,
            @RequestParam() Long newNationalNum,
            @RequestParam() String newAddress

    ) {
        studentService.updateNameOfStudent(id, newName, newLastname,newNationalNum,newAddress);
    }

    // Endpoint to calculate the average grade of a student
    @GetMapping(path = "{id}/average")
    public float averageGrade(
            @PathVariable("id") Long id
    ) {
        return studentService.getAverageGrade(id);
    }


}
