package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.repository.LessonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    LessonsRepository lessonsRepository;
    @Autowired
    LessonService(LessonsRepository lessonsRepository){
        this.lessonsRepository=lessonsRepository;
    }


    public List<Lessons> getLessons() {
        return  lessonsRepository.findAll();
    }

    public void createNewLesson(Lessons lesson) {
        lessonsRepository.save(lesson);
    }

    public void Delete_Lesson(String lesson_name) {
        boolean exists= lessonsRepository.existsById(lesson_name);
        if(!exists){
            throw new IllegalStateException("Lesson with name "+lesson_name+" does not exists");

        }
        lessonsRepository.deleteById(lesson_name);
    }

}

