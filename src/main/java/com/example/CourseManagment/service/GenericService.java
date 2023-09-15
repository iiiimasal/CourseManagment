package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.entity.Student;
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

    Class<?> studentClass = Student.class;
    Class<?>professorClass= Professers.class;

    public List<T> getAll(Class<T> entityClass) {
        if (entityClass ==studentClass) {
            return (List<T>) studentRepository.findAll();
        }
      if (entityClass==professorClass) {
            return (List<T>) professorsRepository.findAll();
        }
        return null;
    }
//
//    public Optional<T> getById(ID id) {
//        return repository.findById(id);
//    }
//
//    public T create(T entity) {
//        return repository.save(entity);
//    }
//
//    public T update(T entity) {
//        return repository.save(entity);
//    }
//
    public void delete(Class<T>entityClass,Long id) {
        System.out.println("Deleting entity of type: " + entityClass.getName() + " with ID: " + id);

        if (entityClass ==studentClass ) {
            System.out.println("in the first condition");
            studentRepository.deleteById( id);
        }
        else if (entityClass ==professorClass ) {
            System.out.println("in the second condition");
            professorsRepository.deleteById(id);
        }
    }
}
