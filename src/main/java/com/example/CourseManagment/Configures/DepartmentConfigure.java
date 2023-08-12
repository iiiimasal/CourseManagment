package com.example.CourseManagment.Configures;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DepartmentConfigure {
    @Bean
    public CommandLineRunner commandLineRunnerD(DepartmentRepository departmentRepository){
        return args -> {
            Department Physics=new Department(
                    "Physics"
            );
            Department Computer=new Department(
                    "Computer"
            );
            departmentRepository.saveAll(List.of(Physics,Computer));



        };

    }
}
