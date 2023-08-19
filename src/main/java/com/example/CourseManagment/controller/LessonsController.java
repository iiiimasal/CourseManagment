package com.example.CourseManagment.controller;

import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/courseMangment/Lessons")
public class LessonsController {

    // Injecting the LessonService dependency using constructor injection
    final LessonService lessonService;

    @Autowired
    public LessonsController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    // Endpoint to get a list of all lessons
    @GetMapping
    public List<Lessons> getLessons() {
        return lessonService.getLessons();
    }

    // Endpoint to register a new lesson
    @PostMapping
    // Adding a new lesson by specifying its name and credit
    public void registerNewLesson(@RequestBody Lessons lesson) {
        lessonService.createNewLesson(lesson);
    }

    // Endpoint to remove a lesson
    @DeleteMapping(path = "{lessonName}")
    public void removeLesson(@PathVariable String lessonName) {
        lessonService.DeleteLesson(lessonName);
    }

    // Endpoint to add a department to a lesson
    @PostMapping(path = "{lessonName}")
    public void addDepartment(
            @PathVariable("lessonName") String lessonName,
            @RequestParam(required = false) String DepartmentName) {
        lessonService.AddDepartment(lessonName, DepartmentName);
    }

    // Endpoint to calculate the average score of a lesson
    @GetMapping(path = "{lessonName}/Average")
    public float averageOfLessonScore(
            @PathVariable("lessonName") String lessonName
    ) {
        return lessonService.averageOfLessonScore(lessonName);
    }


}
