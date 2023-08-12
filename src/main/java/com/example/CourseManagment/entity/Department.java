package com.example.CourseManagment.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table
public class Department {
    @Id
    @Column(nullable = false,
            unique = true)
    @Size(min = 5, max = 48)
    private String DepartmentName;
    //@Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "MnagerId")
    private Professers manager;
    @Column()
    @OneToMany(mappedBy="departmentOfprofessor",
            fetch = FetchType.LAZY)
    private List<Professers> professersList=new ArrayList<>();

    @Column()
    @OneToMany(mappedBy="department")
    private List<Student>StudentListTotal;
    @Column(nullable = false)
    @OneToMany(mappedBy="department")
    private List<Lessons>LessonsListTotal;

    public Department(String DepartmentName,
                      Professers manager,
                      List<Professers> professersList,
                      List<Student> StudentListTotal,
                      List<Lessons> LessonsListTotal) {
        this.DepartmentName = DepartmentName;
        this.manager = manager;
        this.professersList = professersList;
        this.StudentListTotal = StudentListTotal;
        this.LessonsListTotal = LessonsListTotal;
    }

    public Department(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }

    public Department() {

    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }

    public Professers getManager() {
        return manager;
    }

    public void setManager(Professers manager) {
        this.manager = manager;
    }

    public List<Professers> getProfessersList() {
        return professersList;
    }

    public void setProfessersList(List<Professers> professersList) {
        this.professersList = professersList;
    }

    public List<Student> getStudentListTotal() {
        return StudentListTotal;
    }

    public void setStudentListTotal(List<Student> StudentListTotal) {
        this.StudentListTotal = StudentListTotal;
    }

    public List<Lessons> getLessonsListTotal() {
        return LessonsListTotal;
    }

    public void setLessonsListTotal(List<Lessons> LessonsListTotal) {
        this.LessonsListTotal = LessonsListTotal;
    }
}
