package com.example.CourseManagment.Configures;

import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.repository.LessonsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LessonConfigure {
    @Bean
   public CommandLineRunner commandLinerunner(LessonsRepository lessonsRepository){
        return args -> {
            Lessons math = new Lessons("Mathematics", 3);
            Lessons algorithm = new Lessons("Algorithm", 3);
            Lessons machine = new Lessons("Machine", 2);

            List<Lessons> lessonsList = Arrays.asList(math, algorithm, machine);
            lessonsRepository.saveAll(lessonsList);
        };
    }
    }

