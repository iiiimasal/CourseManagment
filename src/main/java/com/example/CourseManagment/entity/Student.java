package com.example.CourseManagment.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
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
    private  long NationalNum;
    @Column(length = 128)
    private String address;

    // Many-to-one relationship with Lessons
//    @ManyToOne
//    @JoinColumn(name = "lessons_list") // The name of the foreign key column in the Student table
//   private Lessons lessons;
//    @Column(nullable = false)
//    // One-to-many relationship with Lessons
//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//    private List<Lessons>Lessonslist;
    @ManyToMany
    @JoinTable(
            name = "student_lesson",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private List<Lessons> lessons = new ArrayList<>();

    // Constructors, getters, setters, and other methods



@ManyToOne()
    @JoinColumn(name="Department", nullable=true)
    private Department department;



    public Student(long id, String firstname, String lastname, long nationalNum, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        NationalNum = nationalNum;
        this.address = address;
    }

    public Student(String firstname, String lastname, long nationalNum, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        NationalNum = nationalNum;
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
        return NationalNum;
    }

    public void setNationalNum(long nationalNum) {
        NationalNum = nationalNum;
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
}
