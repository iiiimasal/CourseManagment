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
   private LessonService lessonService;
   private DepartmentService departmentService;
   private final GenericService<Professers, Long> professorGeneric;

    @Autowired
    public ProfessorsService(ProfessorsRepository professorsRepository,
                             LessonService lessonService,
                             DepartmentService departmentService,
                          @Lazy GenericService<Professers, Long> professorGeneric) {
        this.professorsRepository = professorsRepository;
        this.lessonService=lessonService;
        this.departmentService=departmentService;
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

        Lessons newLesson=lessonService.lessonRequired(lesson);

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
        Department department=departmentService.departmentRequired(departmentName);
        professor.setdepartmentOfprofessor(department);
        professorsRepository.save(professor);

    }
    @Override
    public void chooseManagerOfDepartment(Long professorId, String departmentName) {

        Professers professor=professorsRepository.findById(professorId).orElseThrow(()-> new IllegalStateException(
                "professor with id "+professorId+"does not exist"
        ));

        departmentService.chooseManger(departmentName,professor);




    }
    public  Professers professorRequired(Long id){
        Professers professorRequired = professorsRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "professor with id " + id + "does not exist"
        ));
        return professorRequired;
    }
}



