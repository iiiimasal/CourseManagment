package com.example.CourseManagment.controller;

import com.example.CourseManagment.DTO.LessonDTO;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.repository.LessonsRepository;
import com.example.CourseManagment.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/courseMangment/Lessons")
public class LessonsController {

    // Injecting the LessonService dependency using constructor injection
   private final LessonService lessonService;
   private final LessonsRepository lessonsRepository;

    @Autowired
    public LessonsController(@Lazy LessonService lessonService,
                             LessonsRepository lessonsRepository) {
        this.lessonService = lessonService;
        this.lessonsRepository=lessonsRepository;
    }

    // Endpoint to get a list of all lessons
//    @GetMapping
//    public Page<Lessons> getLessons(
//            @RequestParam Optional<Integer> page,
//            @RequestParam Optional<String> sortBy
//    ) {
//        return lessonsRepository.findAll(
//                PageRequest.of(
//                        page.orElse(0),
//                        2,
//                        Sort.Direction.ASC,sortBy.orElse("lessonName")
//
//                )
//
//        );
//    }
    @GetMapping
    public List<Lessons> getLessons(){
       return lessonService.getLessons();
    }

    // Endpoint to register a new lesson
    @PostMapping
    // Adding a new lesson by specifying its name and credit
    public void registerNewLesson(@RequestBody LessonDTO lessonDTO) {
        Lessons lesson=new Lessons(lessonDTO.getLessonName(),lessonDTO.getCredit());
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
