package com.example.CourseManagment.Abstraction;

import com.example.CourseManagment.entity.Professers;

import java.util.List;

public interface ProfessorInterface {
    public List<Professers> getProfessors();
    public void CreateNewProfessor(Professers professer);
    public void DeleteProfessor(Long professorId);
    public void AddLesson(Long professorId, String lesson);
    public void AddProfessorDepartment(Long professorId, String departmentName);
    public void chooseManagerOfDepartment(Long professorId, String departmentName);
}
