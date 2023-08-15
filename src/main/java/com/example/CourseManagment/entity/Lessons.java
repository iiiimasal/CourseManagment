package com.example.CourseManagment.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table

public class Lessons {
    @Id
    @Column(nullable = false,
            unique = true,
            length = 64)
    private  String lessonName;
    @Column(nullable = false)
    private Integer Credit;


    @ManyToMany(mappedBy = "lessons")
    private List<Student> students = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;
    @ManyToMany(mappedBy = "professorLessons")
    private List<Professers> professor;


    public Lessons(String lessonName, Integer credit ) {
        this.lessonName = lessonName;
        this.Credit = credit;
        //this.professor=professor;
    }

    public Lessons(String lessonName, Integer credit, Department department) {
        this.lessonName = lessonName;
        this.Credit = credit;

    }



    public Lessons() {

    }



    public String getlessonName() {
        return lessonName;
    }

    public void setlessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getCredit() {
        return Credit;
    }

    public void setCredit(Integer credit) {
        Credit = credit;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
