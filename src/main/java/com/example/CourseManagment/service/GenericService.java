package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.LessonsRepository;
import com.example.CourseManagment.repository.ProfessorsRepository;
import com.example.CourseManagment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@Service
public abstract class GenericService<T, ID> {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfessorsRepository professorsRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private LessonsRepository lessonsRepository;

    Class<?> studentClass = Student.class;
    Class<?>professorClass= Professers.class;
    Class<?>departmentClass= Department.class;
    Class<?>lessonClass= Lessons.class;

    public List<T> getAll(Class<T> entityClass) {
        if (entityClass ==studentClass) {
            return (List<T>) studentRepository.findAll();
        }
      if (entityClass==professorClass) {
            return (List<T>) professorsRepository.findAll();
        }
      if(entityClass==departmentClass){
          return (List<T>) departmentRepository.findAll();
      }
        if(entityClass==lessonClass){
            return (List<T>) lessonsRepository.findAll();
        }
        return null;
    }
//
//    public Optional<T> getById(ID id) {
//        return repository.findById(id);
//    }
//
    public T create(Class<T> entityClass,T entity) {
        if (entityClass == studentClass) {
            Student student = (Student) entity;
            if (studentRepository.existsById(student.getNationalNum())) {
                throw new IllegalStateException("Already student exists");
            }
            studentRepository.save(student);
        }

        else if(entityClass==professorClass){
            Professers professer=(Professers) entity;
            if(professorsRepository.existsById(professer.getprofessorId())){
                throw new IllegalStateException("The professor is already existed");
            }
            professorsRepository.save(professer);
        }
        else if(entityClass==departmentClass){
            Department department=(Department)entity;
            if (departmentRepository.existsById(department.getDepartmentName())) {
                throw new IllegalStateException("Department with the same name already exists");
            } else {
                departmentRepository.save(department);
            }
        }
        else if(entityClass==lessonClass){
            Lessons lesson=(Lessons) entity;
            if (lessonsRepository.existsById(lesson.getLessonName())) {
                throw new IllegalStateException("Lesson with the same name already exists");
            }
            lessonsRepository.save(lesson);
        }
        return null;
    }

//
//    public T update(T entity) {
//        return repository.save(entity);
//    }
//
    public void delete(Class<T>entityClass,Object id) {
        System.out.println("Deleting entity of type: " + entityClass.getName() + " with ID: " + id);

        if (entityClass ==studentClass ) {
            System.out.println("in the first condition");
            studentRepository.deleteById( (Long)id);
        }
        else if (entityClass ==professorClass ) {
            System.out.println("in the second condition");
            professorsRepository.deleteById((Long)id);
        }
        else if (entityClass ==departmentClass ) {
            System.out.println("in the third condition");
           departmentRepository.deleteById((String) id);
        }
        else if(entityClass==lessonClass){
            boolean exists= lessonsRepository.existsById((String) id);
            if(!exists){
                throw new IllegalStateException("Lesson with name "+id+" does not exists");

            }
            lessonsRepository.deleteById((String) id);
        }
    }
}
