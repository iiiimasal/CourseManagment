package com.example.CourseManagment.controller;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/courseMangment/Departments")
public class DepartmentController {
    final DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }

    @PostMapping
    public void registerNewDepartment(@RequestBody Department department){
        departmentService.CreateNewDepartment(department);
    }
    @DeleteMapping(path = "{department_name}")
    public void removeDepartment(@PathVariable String department_name){
        departmentService.DeleteDepartment(department_name);
    }

}
