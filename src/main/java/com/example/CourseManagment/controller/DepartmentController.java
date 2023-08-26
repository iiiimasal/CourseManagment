package com.example.CourseManagment.controller;

import com.example.CourseManagment.DTO.DepartmentDTO;
import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/courseMangment/Departments")
public class DepartmentController {

    // Injecting the DepartmentService dependency using constructor injection
    final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Endpoint to get a list of all departments
    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    // Endpoint to calculate the average score of lessons for a department
    @GetMapping(path = "{DepartmentName}/Average")
    public float averageOfLessonScore(
            @PathVariable("DepartmentName") String DepartmentName
    ) {
        return departmentService.averageOfDepartmentScore(DepartmentName);
    }

    // Endpoint to register a new department
    @PostMapping("/departments")
    public void registerNewDepartment(@RequestBody DepartmentDTO departmentDTO) {
        // Convert the DepartmentDTO to a Department entity
        Department newDepartment = new Department(departmentDTO.getDepartmentName());
        departmentService.createNewDepartment(newDepartment);
    }
    // Endpoint to remove a department
    @DeleteMapping(path = "{DepartmentName}")
    public void removeDepartment(@PathVariable String DepartmentName) {
        departmentService.DeleteDepartment(DepartmentName);
    }


}

