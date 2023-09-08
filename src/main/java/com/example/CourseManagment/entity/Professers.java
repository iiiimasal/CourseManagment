package com.example.CourseManagment.entity;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table
public class Professers {
    @Id
    @SequenceGenerator(name = "professor_sequence",
    sequenceName = "professor_sequence",
    allocationSize = 1,
    initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "professor_sequence")
    @Column(nullable = false,unique = true)
    private long professorId;
    private String firstnameProfessor;
    private String lastnameProfessor;
    private  long nationalNumProfessor;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "professor_lesson",
            joinColumns = @JoinColumn(name = "professorId"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private List<Lessons> professorLessons=new ArrayList<>();



    // One-to-one relationship with Department
    @OneToOne(mappedBy = "manager")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "professor-list", updatable = true)
    private Department departmentOfprofessor;

    public Professers(long professorId, String firstnameProfessor, String lastnameProfessor, long nationalNumProfessor) {
        this.professorId = professorId;
        this.firstnameProfessor = firstnameProfessor;
        this.lastnameProfessor = lastnameProfessor;
        this.nationalNumProfessor = nationalNumProfessor;
    }

    public Professers(String firstnameProfessor, String lastnameProfessor, long nationalNumProfessor) {
        this.firstnameProfessor = firstnameProfessor;
        this.lastnameProfessor = lastnameProfessor;
        this.nationalNumProfessor = nationalNumProfessor;
    }

    public Professers(long professorId, String firstnameProfessor, String lastnameProfessor, long nationalNumProfessor, Department departmentOfprofessor) {
        this.professorId = professorId;
        this.firstnameProfessor = firstnameProfessor;
        this.lastnameProfessor = lastnameProfessor;
        this.nationalNumProfessor = nationalNumProfessor;
        this.departmentOfprofessor = departmentOfprofessor;
    }

    public Professers() {
    }

    public long getprofessorId() {
        return professorId;
    }

    public void setprofessorId(long professorId) {
        this.professorId = professorId;
    }

    public String getfirstnameProfessor() {
        return firstnameProfessor;
    }

    public void setfirstnameProfessor(String firstnameProfessor) {
        this.firstnameProfessor = firstnameProfessor;
    }

    public String getlastnameProfessor() {
        return lastnameProfessor;
    }

    public void setlastnameProfessor(String lastnameProfessor) {
        this.lastnameProfessor = lastnameProfessor;
    }

    public long getnationalNumProfessor() {
        return nationalNumProfessor;
    }

    public void setnationalNumProfessor(long nationalNumProfessor) {
        this.nationalNumProfessor = nationalNumProfessor;
    }

    public List<Lessons> getprofessorLessons() {
        return professorLessons;
    }

    public void setprofessorLessons(List<Lessons> professorLessons) {
        this.professorLessons = professorLessons;
    }

    public Department getdepartmentOfprofessor() {
        return departmentOfprofessor;
    }

    public void setdepartmentOfprofessor(Department departmentOfprofessor) {
        this.departmentOfprofessor = departmentOfprofessor;
    }
}
