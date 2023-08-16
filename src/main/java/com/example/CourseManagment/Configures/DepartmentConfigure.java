package com.example.CourseManagment.Configures;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DepartmentConfigure {
    @Bean
    public CommandLineRunner commandLineRunnerD(DepartmentRepository departmentRepository){
        return args -> {
            Department physics = new Department("Physics");
            Department computer = new Department("Computer");

            List<Department> departments = Arrays.asList(physics, computer);
            departmentRepository.saveAll(departments);
        };

    }
}
