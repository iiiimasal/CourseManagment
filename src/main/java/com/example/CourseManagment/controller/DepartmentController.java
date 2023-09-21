package com.example.CourseManagment.controller;

import com.example.CourseManagment.DTO.DepartmentDTO;
import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/courseMangment/department")
public class DepartmentController {

    // Injecting the DepartmentService dependency using constructor injection
   private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController(@Lazy DepartmentService departmentService,
                                DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.departmentRepository=departmentRepository;
    }

    // Endpoint to get a list of all departments
//    @GetMapping
//    public Page<Department> getDepartment(
//            @RequestParam Optional<Integer> page,
//            @RequestParam Optional<String> sortBy
//    ) {
//        return departmentRepository.findAll(
//                PageRequest.of(
//                        page.orElse(0),
//                        2,
//                        Sort.Direction.ASC,sortBy.orElse("departmentName")
//
//                )
//
//        );
//    }
    @GetMapping
    public List<Department> getDepartment(){
       return departmentService.getDepartments();
    }


    // Endpoint to calculate the average score of lessons for a department
    @GetMapping(path = "{DepartmentName}/average-of-department")
    public float averageOfLessonScore(
            @PathVariable("DepartmentName") String DepartmentName
    ) {
        return departmentService.averageOfDepartmentScore(DepartmentName);
    }

    // Endpoint to register a new department
    @PostMapping("/adding-new-department")
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

