package com.example.CourseManagment.Configures;

import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class StudentConfigure {
    @Bean
//    public CommandLineRunner init(DepartmentRepository departmentRepository) {
//        return args -> {
//            Department department1 = new Department("computer");
//            Department department2 = new Department("mechanic");
//
//            departmentRepository.saveAll(List.of(department1, department2));
//        };
//    }
//
   public CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student asal = new Student(
                    "asa;",
                    "salamni",
                    0200010,
                    "Iran.Tehran");
            Student asal2 = new Student(
                    "asa;",
                    "salamni",
                    0200010,
                    "Iran.Tehran");

            studentRepository.saveAll(List.of(asal, asal2));

        };


    }
}
