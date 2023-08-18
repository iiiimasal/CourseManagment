package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Grade;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.GradeRepository;
import com.example.CourseManagment.repository.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DepartmentService {
    DepartmentRepository departmentRepository;
    ProfessorsRepository professorsRepository;
    GradeRepository gradeRepository;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository  ,
                             ProfessorsRepository professorsRepository,
                             GradeRepository gradeRepository) {
        this.departmentRepository = departmentRepository;
        this.professorsRepository=professorsRepository;
        this.gradeRepository=gradeRepository;
    }



    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }
    public  void CreateNewDepartment(Department department) {
        if (departmentRepository.existsById(department.getDepartmentName())) {
            throw new IllegalStateException("Department with the same name already exists");
        } else {
            departmentRepository.save(department);
        }
    }

    public void DeleteDepartment(String departmentName) {
        departmentRepository.deleteById(departmentName);
    }


    public float averageOfDepartmentScore(String departmentName) {
        float sum = 0;
        int total = 0;
        float avgOflesson = 0;

        Department department = departmentRepository.findById(departmentName)
                .orElseThrow(() -> new IllegalStateException("Department " + departmentName + " does not exist"));

        if (department.getGrades() == null) {
            return 0;
        }

        for (Lessons lesson: department.getLessonsListTotal()) {

            for (Grade grade:lesson.getGrades()){
                total+=grade.getLesson().getCredit();
                sum += grade.getGrade()*(grade.getLesson().getCredit());
            }
            // Calculate average for each lesson and add it to avgOflesson
            avgOflesson += sum / total;
            // Reset sum and total for the next lesson
            sum = 0;
            total = 0;
        }

        return avgOflesson / department.getLessonsListTotal().size();
    }

}
