package com.example.CourseManagment.Abstraction;

import com.example.CourseManagment.entity.Department;

import java.util.List;

public interface DepartmentInterface {
    public List<Department> getDepartments();
    public void DeleteDepartment(String departmentName);
    public  void createNewDepartment(Department department);

    public float averageOfDepartmentScore(String departmentName);
}
