package com.example.CourseManagment.service;

import com.example.CourseManagment.Abstraction.ProfessorInterface;
import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Lessons;
import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.LessonsRepository;
import com.example.CourseManagment.repository.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorsService extends GenericService<Professers,Long>  implements ProfessorInterface {
   private ProfessorsRepository professorsRepository;
   private LessonsRepository lessonsRepository;
   private DepartmentRepository departmentRepository;
   private final GenericService<Professers, Long> professorGeneric;

    @Autowired
    public ProfessorsService(ProfessorsRepository professorsRepository,
                             LessonsRepository lessonsRepository,
                             DepartmentRepository departmentRepository,
                          @Lazy GenericService<Professers, Long> professorGeneric) {
        this.professorsRepository = professorsRepository;
        this.lessonsRepository = lessonsRepository;
        this.departmentRepository = departmentRepository;
        this.professorGeneric=professorGeneric;
    }
    @Override
    public List<Professers> getProfessors() {
        return professorGeneric.getAll(Professers.class);
    }

//    public void CreateNewProfessor(Professers professer) {
//        if(professorsRepository.existsById(professer.getprofessorId())){
//            throw new IllegalStateException("The professor is already existed");
//        }
//        professorsRepository.save(professer);
//    }
@Override
public void CreateNewProfessor(Professers professer) {
        professorGeneric.create(Professers.class,professer);
}

    public void DeleteProfessor(Long professorId) {

        professorGeneric.delete(Professers.class,professorId);
    }

    @Override
    public void AddLesson(Long professorId, String lesson) {

        Professers professor=professorsRepository.findById(professorId).orElseThrow(()-> new IllegalStateException(
                "professor with id "+professorId+"does not exist"
        ));



        Lessons newLesson=lessonsRepository.findById(lesson).orElseThrow(()-> new IllegalStateException(
                "Lesson "+lesson+"does not exist"
        ));

            if (!(professor.getdepartmentOfprofessor()==newLesson.getDepartment())) {
                 throw new IllegalStateException("The lesson's department and professor does not match!");

            }
            professor.getprofessorLessons().add(newLesson);
            professorsRepository.save(professor);

        }
    @Override

    public void AddProfessorDepartment(Long professorId, String departmentName) {

        Professers professor=professorsRepository.findById(professorId).orElseThrow(()-> new IllegalStateException(
                "professor with id "+professorId+"does not exist"
        ));

        Department department=departmentRepository.findById(departmentName).orElseThrow(()-> new IllegalStateException(
                "Department "+departmentName+"does not exists"
        ));
            professor.setdepartmentOfprofessor(department);
            professorsRepository.save(professor);

    }
    @Override
    public void chooseManagerOfDepartment(Long professorId, String departmentName) {

        Professers professor=professorsRepository.findById(professorId).orElseThrow(()-> new IllegalStateException(
                "professor with id "+professorId+"does not exist"
        ));

        Department department=departmentRepository.findById(departmentName).orElseThrow(()-> new IllegalStateException(
                "Department "+departmentName+"does not exists"
        ));

            if (!professor.getdepartmentOfprofessor().equals(department)) {
                throw new IllegalStateException("The professor mentioned is not in the required department");
            }

                department.setManager(professor);
                departmentRepository.save(department);
    }
    public  Professers professorRequired(Long id){
        Professers professorRequired = professorsRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "professor with id " + id + "does not exist"
        ));
        return professorRequired;
    }
}



