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
    @PutMapping
    public void  registerNewLesson(@RequestBody Lessons lesson){
        lessonService.createNewLesson(lesson);
    }
    @DeleteMapping(path = "{lesson_name}")
    public void removeLesson(@PathVariable String lesson_name) {

        lessonService.Delete_Lesson(lesson_name);
    }

}
