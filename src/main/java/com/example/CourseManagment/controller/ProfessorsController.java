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
    ProfessorsService professorsService;

    @Autowired
    public ProfessorsController(ProfessorsService professorsService) {
        this.professorsService = professorsService;
    }

    @Autowired


    @GetMapping
    public List<Professers> getProfessors() {
        return professorsService.getProfessors();
    }

    @PutMapping
    //Adding new professor
    public void registerNewProfessor(@RequestBody Professers professer) {
        professorsService.CreateNewProfessor(professer);

    }

    @DeleteMapping("{professor_id}")
    public void removeProfessor(@PathVariable Long professor_id) {
        professorsService.DeleteProfessor(professor_id);
    }

    @PostMapping(path = "{professor_id}/Department")
    public void Professor_department(
            @PathVariable  ("professor_id") Long  professor_id,
            @RequestParam(required = false) String DepartmentName){
        professorsService.AddProfessorDepartment(professor_id,DepartmentName);
    }
    @PostMapping(path = "{professor_id}/lesson")
    public void addProfessorLesson(
            @PathVariable("professor_id") Long professor_id,
            @RequestParam(required = false) String lesson) {
        professorsService.AddLesson(professor_id, lesson);
    }


    @PostMapping(path = "{professor_id}/manager")
    public void chooseManagerOfDepartment(
            @PathVariable("professor_id") Long professor_id,
            @RequestParam(required = false) String DepartmentName
    ){
        professorsService.chooseManagerOfDepartment(professor_id, DepartmentName);
    }

}
