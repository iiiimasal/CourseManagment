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
        departmentRepository.save(department);
    }

    public void DeleteDepartment(String departmentName) {
        departmentRepository.deleteById(departmentName);
    }

//    public void Add_Manager(String departmentName, Long manager) {
//        Optional<Department> department_previous_info = departmentRepository.findById(departmentName);
//
//        Optional<Professers> required_manager = professorsRepository.findById(manager);
//
//        Professers new_manager = required_manager.get();
//
//        if (department_previous_info.isPresent()) {
//            // Student found, so you can access it using student_previous_info.get()
//            Department department= department_previous_info.get();
//
//            if (department.getManager()==null) {
//
//                department.setManager(new_manager);
//            }
//            departmentRepository.save(department);
//
//        } else new IllegalStateException("Department does not exists.");
//    }
}
