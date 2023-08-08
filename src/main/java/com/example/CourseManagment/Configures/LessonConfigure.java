package com.example.CourseManagment.Configures;

import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.LessonsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LessonConfigure {
    @Bean
   public CommandLineRunner commandLinerunner(LessonsRepository lessonsRepository){
        return args -> {
          Lessons math=new Lessons(
                  "Mathemathic",
                  3
                    );

            Lessons Algorithem=new Lessons(
                    "Algorithem",
                    3
            );
            Lessons Machine=new Lessons(
                    "Machine",
                    3
            );

          lessonsRepository.saveAll(List.of(math,Algorithem,Machine));

        };
    }
}
