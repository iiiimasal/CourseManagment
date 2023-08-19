package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Grade;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.LessonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    LessonsRepository lessonsRepository;
    DepartmentRepository departmentRepository;
    @Autowired
    LessonService(LessonsRepository lessonsRepository ,
                  DepartmentRepository departmentRepository){
        this.lessonsRepository=lessonsRepository;
        this.departmentRepository=departmentRepository;
    }


    public List<Lessons> getLessons() {
        return  lessonsRepository.findAll();
    }

    public void createNewLesson(Lessons lesson) {
        if (lessonsRepository.existsById(lesson.getlessonName())) {
            throw new IllegalStateException("Lesson with the same name already exists");
        }
        lessonsRepository.save(lesson);
    }

    public void DeleteLesson(String lessonName) {
        boolean exists= lessonsRepository.existsById(lessonName);
        if(!exists){
            throw new IllegalStateException("Lesson with name "+lessonName+" does not exists");

        }
        lessonsRepository.deleteById(lessonName);
    }

    public void AddDepartment(String lessonName, String departmentName) {
        Lessons lesson=lessonsRepository.findById(lessonName).orElseThrow(()-> new IllegalStateException(
                "Lesson "+lessonName+"does not exist"
        ));
        Department department=departmentRepository.findById(departmentName).orElseThrow(()-> new IllegalStateException(
                "Department "+departmentName+"does not exists"
        ));


        lesson.setDepartment(department);
        lessonsRepository.save(lesson);

    }

    public float averageOfLessonScore(String lessonName) {
        float sum=0;
        int total=0;
        Lessons lesson=lessonsRepository.findById(lessonName).orElseThrow(()-> new IllegalStateException(
                "Lesson "+lessonName+"does not exist"
        ));
        if (lesson.getGrades()==null){
            return 0;
        }
        for (Grade grade:lesson.getGrades()){

            sum += grade.getGrade();
        }
        return sum/lesson.getGrades().size();
    }
}

