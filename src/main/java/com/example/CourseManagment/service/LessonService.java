package com.example.CourseManagment.service;

import com.example.CourseManagment.Abstraction.LessonInterface;
import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Grade;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.LessonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService extends GenericService<Lessons,String> implements LessonInterface {

    private final LessonsRepository lessonsRepository;
   private  DepartmentRepository departmentRepository;
   private GenericService<Lessons, String>lessonGeneric;
    @Autowired
    LessonService(LessonsRepository lessonsRepository ,
                  DepartmentRepository departmentRepository,
                 @Lazy GenericService<Lessons, String>lessonGeneric){
        this.lessonsRepository=lessonsRepository;
        this.departmentRepository=departmentRepository;
        this.lessonGeneric=lessonGeneric;
    }

    public LessonService(LessonsRepository lessonsRepository) {
        this.lessonsRepository=lessonsRepository;
    }

    @Override
    public List<Lessons> getLessons() {
        return lessonGeneric.getAll(Lessons.class);
    }
    @Override
    public void createNewLesson(Lessons lesson) {
        lessonGeneric.create(Lessons.class,lesson);
    }
    @Override
    public void DeleteLesson(String lessonName) {
        lessonGeneric.delete(Lessons.class,lessonName);
    }
    @Override
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
    @Override
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

