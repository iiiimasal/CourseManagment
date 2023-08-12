package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    DepartmentRepository departmentRepository;
    ProfessorsRepository professorsRepository;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository  ,ProfessorsRepository professorsRepository) {
        this.departmentRepository = departmentRepository;
        this.professorsRepository=professorsRepository;
    }



    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }
    public  void CreateNewDepartment(Department department) {
        if (departmentRepository.existsById(department.getDepartmentName())) {
            throw new RuntimeException("Department with the same name already exists");
        }
        departmentRepository.save(department);
    }

    public void DeleteDepartment(String departmentName) {
        departmentRepository.deleteById(departmentName);
    }


}
