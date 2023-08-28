package com.example.CourseManagment.entity;

//import jakarta.persistence.*;
import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
@Entity
//@Table

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
    @OneToMany(mappedBy = "lesson")
    private List<Grade> grades = new ArrayList<>();


    //Constructor used for requesting for adding lesson with name and credit of that
    public Lessons(String lessonName, Integer credit ) {
        this.lessonName = lessonName;
        this.Credit = credit;

    }

    public Lessons(String lessonName, Integer credit, Department department) {
        this.lessonName = lessonName;
        this.Credit = credit;
        this.department=department;

    }



    public Lessons() {

    }



    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
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

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }



    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Professers> getProfessor() {
        return professor;
    }

    public void setProfessor(List<Professers> professor) {
        this.professor = professor;
    }
}
