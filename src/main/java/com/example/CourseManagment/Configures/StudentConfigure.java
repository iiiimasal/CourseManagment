package com.example.CourseManagment.Configures;

import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
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
                    "Asal",
                    "Salamni",
                    200010,
                    "Iran.Tehran");
            Student asal2 = new Student(
                    "Asal",
                    "Salamni",
                    200011,
                    "Iran.Tehran");

            List<Student> studentsList = new ArrayList<>(); // Create a list
            studentsList.add(asal); // Add the first student to the list
            studentsList.add(asal2); // Add the second student to the list

            studentRepository.saveAll(studentsList); // Save the list of students
        };

    }
}
