package com.example.CourseManagment.Configures;

import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.repository.LessonsRepository;
import com.example.CourseManagment.repository.ProfessorsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProfessorsConfigure {
    @Bean
    public CommandLineRunner commandLinerunnerP(ProfessorsRepository professorsRepository) {

        return args -> {
            Professers a = new Professers(
                    144444,
                    "Maryam",
                    "Khodabakhsh",
                    2121
            );

            List<Professers> professorsList = new ArrayList<>(); // Create a list
            professorsList.add(a); // Add the professor to the list

            professorsRepository.saveAll(professorsList); // Save the list of professors
        };
    }
}
