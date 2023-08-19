package com.example.CourseManagment.controller;

import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.repository.ProfessorsRepository;
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
    @PutMapping
    // Adding a new professor
    public void registerNewProfessor(@RequestBody Professers professer) {
        professorsService.CreateNewProfessor(professer);
    }

    // Endpoint to remove a professor
    @DeleteMapping("{professor_id}")
    public void removeProfessor(@PathVariable Long professor_id) {
        professorsService.DeleteProfessor(professor_id);
    }

    // Endpoint to add a department to a professor
    @PostMapping(path = "{professor_id}/Department")
    public void Professor_department(
            @PathVariable("professor_id") Long professor_id,
            @RequestParam(required = false) String DepartmentName) {
        professorsService.AddProfessorDepartment(professor_id, DepartmentName);
    }

    // Endpoint to add a lesson to a professor
    @PostMapping(path = "{professor_id}/lesson")
    public void addProfessorLesson(
            @PathVariable("professor_id") Long professor_id,
            @RequestParam(required = false) String lesson) {
        professorsService.AddLesson(professor_id, lesson);
    }

    // Endpoint to choose a manager for a department
    @PostMapping(path = "{professor_id}/manager")
    public void chooseManagerOfDepartment(
            @PathVariable("professor_id") Long professor_id,
            @RequestParam(required = false) String DepartmentName) {
        professorsService.chooseManagerOfDepartment(professor_id, DepartmentName);
    }


}
