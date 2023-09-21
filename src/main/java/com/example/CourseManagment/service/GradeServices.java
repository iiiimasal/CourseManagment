package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Grade;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.GradeRepository;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServices {
    private GradeRepository gradeRepository;
    private LessonService lessonService;
    @Autowired
    GradeServices(GradeRepository gradeRepository,
                  LessonService lessonService){
        this.gradeRepository=gradeRepository;
        this.lessonService=lessonService;

    }
    public Grade addGradeToLessonOfStudent(String lessonName, Student student, float grade){
        Lessons lesson=lessonService.lessonRequired(lessonName);
        Grade newGrade = new Grade();
        newGrade.setLesson(lesson);
        newGrade.setStudent(student);
        newGrade.setGrade(grade);
        //newGrade.setDepartmentOfGrade(lesson.getDepartment());
        gradeRepository.save(newGrade);
        return  newGrade;

    }
}
