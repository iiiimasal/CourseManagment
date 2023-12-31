package com.example.CourseManagment.entity;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Entity
//@Table
public class Department {
    @Id
    @Column(nullable = false,
            unique = true)
    @Size(min = 5, max = 48)
    private String departmentName;
    //@Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "MnagerId")
    private Professers manager;

    @OneToMany(mappedBy="departmentOfprofessor",
            fetch = FetchType.LAZY)
    private List<Professers> professersList=new ArrayList<>();


    @OneToMany(mappedBy="department")
    private List<Student>StudentListTotal;

    @OneToMany(mappedBy="department")
    private List<Lessons>LessonsListTotal;


    public Department(String DepartmentName,
                      Professers manager,
                      List<Professers> professersList,
                      List<Student> StudentListTotal,
                      List<Lessons> LessonsListTotal) {
        this.departmentName = DepartmentName;
        this.manager = manager;
        this.professersList = professersList;
        this.StudentListTotal = StudentListTotal;
        this.LessonsListTotal = LessonsListTotal;
    }

    public Department(String departmentName, Professers manager) {
        this.departmentName = departmentName;
        this.manager = manager;
    }

    public Department(String DepartmentName) {
        this.departmentName = DepartmentName;
    }

    public Department() {

    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String DepartmentName) {
        this.departmentName = DepartmentName;
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
