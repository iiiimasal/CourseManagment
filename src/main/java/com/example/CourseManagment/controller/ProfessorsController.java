package com.example.CourseManagment.controller;

import com.example.CourseManagment.DTO.ProfessorDTO;
import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.service.ProfessorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/courseMangment/Professors")
public class ProfessorsController {

    // Injecting the ProfessorsService dependency using constructor injection
    ProfessorsService professorsService;

    @Autowired
    public ProfessorsController(ProfessorsService professorsService) {
        this.professorsService = professorsService;
    }

    // Endpoint to get a list of all professors
    @GetMapping
    public List<Professers> getProfessors() {
        return professorsService.getProfessors();
    }

    // Endpoint to register a new professor
    @PostMapping
    // Adding a new professor
    public void registerNewProfessor(@RequestBody ProfessorDTO professerDTO) {
        Professers professor=new Professers(professerDTO.getProfessorId(),professerDTO.getFirstnameProfessor(),
                professerDTO.getLastnameProfessor(),professerDTO.getnationalNumProfessor());
        professorsService.CreateNewProfessor(professor);
    }

    // Endpoint to remove a professor
    @DeleteMapping("{professorId}")
    public void removeProfessor(@PathVariable Long professorId) {
        professorsService.DeleteProfessor(professorId);
    }

    // Endpoint to add a department to a professor
    @PostMapping(path = "{professorId}/Department")
    public void Professor_department(
            @PathVariable("professorId") Long professorId,
            @RequestParam(required = false) String DepartmentName) {
        professorsService.AddProfessorDepartment(professorId, DepartmentName);
    }

    // Endpoint to add a lesson to a professor
    @PostMapping(path = "{professorId}/lesson")
    public void addProfessorLesson(
            @PathVariable("professorId") Long professorId,
            @RequestParam(required = false) String lesson) {
        professorsService.AddLesson(professorId, lesson);
    }

    // Endpoint to choose a manager for a department
    @PostMapping(path = "{professorId}/manager")
    public void chooseManagerOfDepartment(
            @PathVariable("professorId") Long professorId,
            @RequestParam(required = false) String DepartmentName) {
        professorsService.chooseManagerOfDepartment(professorId, DepartmentName);
    }


}
