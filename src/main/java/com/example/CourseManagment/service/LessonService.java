package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.LessonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Lessons>lesson_previous_info=lessonsRepository.findById(lessonName);
        Optional<Department>department_required=departmentRepository.findById(departmentName);

        if(lesson_previous_info.isPresent()==false){
            throw new IllegalStateException("The lesson does not exists");
        }

        Department department=department_required.get();
        Lessons lesson=lesson_previous_info.get();

        lesson.setDepartment(department);
        lessonsRepository.save(lesson);

    }
}

