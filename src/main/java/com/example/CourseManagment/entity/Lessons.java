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
    private  String lesson_name;
    @Column(nullable = false)
    private Integer Credit;


    // Many-to-one relationship with Student
//    @ManyToOne
//    @JoinColumn(name = "id_Of_Student") // The name of the foreign key column in the Lessons table
//    private Student student;
//
//    @Column(nullable = true)
//    // One-to-many relationship with Student (reverse relationship)
//    @OneToMany(mappedBy = "lessons", cascade = CascadeType.ALL)
//    private List<Student> studentList;


    @ManyToMany(mappedBy = "lessons")
    private List<Student> students = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "lesson_list")
    private Department department;
    @ManyToMany(mappedBy = "professor_lessons")
    private List<Professers> professor;


    public Lessons(String lesson_name, Integer credit ) {
        this.lesson_name = lesson_name;
        this.Credit = credit;
        //this.professor=professor;
    }


    public Lessons() {

    }



    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public Integer getCredit() {
        return Credit;
    }

    public void setCredit(Integer credit) {
        Credit = credit;
    }


}
