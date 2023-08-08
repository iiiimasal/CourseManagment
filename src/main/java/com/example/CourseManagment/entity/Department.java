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
    private String department_name;
    //@Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "manager_id")
    private Professers manager;
    @Column()
    @OneToMany(mappedBy="department_of_professor",
            fetch = FetchType.LAZY)
    private List<Professers> professersList=new ArrayList<>();

    @Column()
    @OneToMany(mappedBy="department")
    private List<Student>studentList_total;
    @Column(nullable = false)
    @OneToMany(mappedBy="department")
    private List<Lessons>lessonsList_total;

    public Department(String department_name,
                      Professers manager,
                      List<Professers> professersList,
                      List<Student> studentList_total,
                      List<Lessons> lessonsList_total) {
        this.department_name = department_name;
        this.manager = manager;
        this.professersList = professersList;
        this.studentList_total = studentList_total;
        this.lessonsList_total = lessonsList_total;
    }

    public Department(String department_name) {
        this.department_name = department_name;
    }

    public Department() {

    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
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

    public List<Student> getStudentList_total() {
        return studentList_total;
    }

    public void setStudentList_total(List<Student> studentList_total) {
        this.studentList_total = studentList_total;
    }

    public List<Lessons> getLessonsList_total() {
        return lessonsList_total;
    }

    public void setLessonsList_total(List<Lessons> lessonsList_total) {
        this.lessonsList_total = lessonsList_total;
    }
}
