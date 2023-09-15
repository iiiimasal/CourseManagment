package com.example.CourseManagment;

import com.example.CourseManagment.entity.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
//@ComponentScan("com.example.CourseManagment.service")
public class CourseManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseManagmentApplication.class, args);

	}

}
