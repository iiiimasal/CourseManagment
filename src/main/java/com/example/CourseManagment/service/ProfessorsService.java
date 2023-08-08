package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.LessonsRepository;
import com.example.CourseManagment.repository.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorsService {
    ProfessorsRepository professorsRepository;
    LessonsRepository lessonsRepository;
    DepartmentRepository departmentRepository;

    @Autowired
    public ProfessorsService(ProfessorsRepository professorsRepository,
                             LessonsRepository lessonsRepository,
                             DepartmentRepository departmentRepository) {
        this.professorsRepository = professorsRepository;
        this.lessonsRepository = lessonsRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Professers> getProfessors() {
        return professorsRepository.findAll();
    }

    public void CreateNewProfessor(Professers professer) {
        professorsRepository.save(professer);
    }


    public void DeleteProfessor(Long professorId) {
        professorsRepository.deleteById(professorId);
    }


    public void AddLesson(Long professorId, String lesson) {

        Optional<Professers> professor_previous_info = professorsRepository.findById(professorId);

        Optional<Lessons> required_lesson = lessonsRepository.findById(lesson);

        Lessons new_lesson = required_lesson.get();

        if (professor_previous_info.isPresent()) {
            // Student found, so you can access it using student_previous_info.get()
            Professers professor = professor_previous_info.get();

            if (professor.getProfessor_lessons() != null) {

                professor.getProfessor_lessons().add(new_lesson);
            }
            professorsRepository.save(professor);

        } else new IllegalStateException("Professor does not exists.");
    }

    public void Add_Professor_Department(Long professorId, String departmentName) {

        Optional<Professers> professor_previous_info = professorsRepository.findById(professorId);

        Optional<Department> department_required = departmentRepository.findById(departmentName);

        Department department = department_required.get();

        if (professor_previous_info.isPresent()) {

            Professers professor = professor_previous_info.get();

           professor.setDepartment_of_professor(department);
           professorsRepository.save(professor);


            } else new IllegalStateException("Professor does not exists.");
        }
    }



