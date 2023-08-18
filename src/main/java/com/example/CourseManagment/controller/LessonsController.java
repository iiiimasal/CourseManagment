package com.example.CourseManagment.controller;

import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/courseMangment/Lessons")
public class LessonsController {
    final LessonService lessonService;
    @Autowired
    public LessonsController(LessonService lessonService) {
        this.lessonService = lessonService;
    }
    @GetMapping
    public List<Lessons> getLessons(){
         return lessonService.getLessons();
    }

    @PostMapping
    //adding new lesson by specifying it's name and credit
    public void  registerNewLesson(@RequestBody Lessons lesson){
        lessonService.createNewLesson(lesson);
    }
    @DeleteMapping(path = "{lessonName}")
    public void removeLesson(@PathVariable String lessonName) {

        lessonService.DeleteLesson(lessonName);
    }

    @PostMapping(path = "{lessonName}")
    public void addDepartment(
            @PathVariable  ("lessonName") String  lessonName,
            @RequestParam(required = false) String DepartmentName){
        lessonService.AddDepartment(lessonName,DepartmentName);
    }

    @GetMapping(path = "{lessonName}/Average")
    public float averageOfLessonScore(
            @PathVariable("lessonName")String lessonName
    ){
        return lessonService.averageOfLessonScore(lessonName);
    }

}
