package com.example.CourseManagment.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Professers {
    @Id
//    @SequenceGenerator(name = "professsor_sequence",
//            sequenceName = "professsor_sequence",
//            allocationSize = 1
//
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "professsor_sequence"
//    )
    @Column(nullable = false,unique = true)
    private long professor_id;
    private String firstname_professor;
    private String lastname_professor;
    private  long nationalNum_professor;
    @ManyToMany()
    @JoinTable(
            name = "professor_lesson",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private List<Lessons> professor_lessons=new ArrayList<>();



    // One-to-one relationship with Department
    @OneToOne(mappedBy = "manager")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "professor-list", updatable = true)
    private Department department_of_professor;

    public Professers(long professor_id, String firstname_professor, String lastname_professor, long nationalNum_professor) {
        this.professor_id = professor_id;
        this.firstname_professor = firstname_professor;
        this.lastname_professor = lastname_professor;
        this.nationalNum_professor = nationalNum_professor;
    }

    public Professers(long professor_id, String firstname_professor, String lastname_professor, long nationalNum_professor, Department department_of_professor) {
        this.professor_id = professor_id;
        this.firstname_professor = firstname_professor;
        this.lastname_professor = lastname_professor;
        this.nationalNum_professor = nationalNum_professor;
        this.department_of_professor = department_of_professor;
    }

    public Professers() {
    }

    public long getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(long professor_id) {
        this.professor_id = professor_id;
    }

    public String getFirstname_professor() {
        return firstname_professor;
    }

    public void setFirstname_professor(String firstname_professor) {
        this.firstname_professor = firstname_professor;
    }

    public String getLastname_professor() {
        return lastname_professor;
    }

    public void setLastname_professor(String lastname_professor) {
        this.lastname_professor = lastname_professor;
    }

    public long getNationalNum_professor() {
        return nationalNum_professor;
    }

    public void setNationalNum_professor(long nationalNum_professor) {
        this.nationalNum_professor = nationalNum_professor;
    }

    public List<Lessons> getProfessor_lessons() {
        return professor_lessons;
    }

    public void setProfessor_lessons(List<Lessons> professor_lessons) {
        this.professor_lessons = professor_lessons;
    }

    public Department getDepartment_of_professor() {
        return department_of_professor;
    }

    public void setDepartment_of_professor(Department department_of_professor) {
        this.department_of_professor = department_of_professor;
    }
}
