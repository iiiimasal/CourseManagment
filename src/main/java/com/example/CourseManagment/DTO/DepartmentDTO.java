package com.example.CourseManagment.DTO;

import com.example.CourseManagment.entity.Professers;

public class DepartmentDTO {

    private String departmentName;
    private String manager;

    public DepartmentDTO(String departmentName, String manager) {
        this.departmentName = departmentName;
        this.manager = manager;
    }

    public DepartmentDTO() {
    }
    // Getters and setters

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getManager() {
        return manager;
    }

    public void String(String manager) {
        this.manager = manager;
    }
}
