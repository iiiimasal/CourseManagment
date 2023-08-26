package com.example.CourseManagment.DTO;



import java.util.List;

public class ProfessorDTO {

    private long professorId;
    private String firstnameProfessor;
    private String lastnameProfessor;
    private long nationalNumProfessor;


    // Getters and Setters

    public long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public String getFirstnameProfessor() {
        return firstnameProfessor;
    }

    public void setFirstnameProfessor(String firstnameProfessor) {
        this.firstnameProfessor = firstnameProfessor;
    }

    public String getLastnameProfessor() {
        return lastnameProfessor;
    }

    public void setLastnameProfessor(String lastnameProfessor) {
        this.lastnameProfessor = lastnameProfessor;
    }

    public long getnationalNumProfessor() {
        return nationalNumProfessor;
    }

    public void setnationalNumProfessor(long nationalNumProfessor) {
        this.nationalNumProfessor = nationalNumProfessor;
    }


}
