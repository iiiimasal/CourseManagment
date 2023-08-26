package com.example.CourseManagment.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(nullable = false, unique = true)
    private long id;
    @Column(length = 48) // Specify the column size to varchar(48)
    private String firstname;
    @Column(length = 48)
    private String lastname;
    @Column(nullable = false)
    private  long nationalNum;
    @Column(length = 128)
    private String address;




    @ManyToMany
    @JoinTable(
            name = "student_lesson",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private List<Lessons> lessons = new ArrayList<>();


    @OneToMany(mappedBy = "student")
    private List<Grade> grades = new ArrayList<>();


@ManyToOne()
    @JoinColumn(name="Department", nullable=true)
    private Department department;



    public Student(long id, String firstname, String lastname, long nationalNum, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalNum = nationalNum;
        this.address = address;

    }

    public Student(String firstname, String lastname, long nationalNum, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalNum = nationalNum;
        this.address = address;
    }

    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void setDepartment(Department department) {
        this.department=department;
    }

    public long getNationalNum() {
        return nationalNum;
    }

    public void setNationalNum(long nationalNum) {
        this.nationalNum = nationalNum;
    }

    public List<Lessons> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lessons> lessons) {
        this.lessons = lessons;
    }

    public Department getDepartment() {
        return department;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
