package com.example.CourseManagment.service;

import com.example.CourseManagment.Abstraction.StudentInterface;
import com.example.CourseManagment.entity.*;
import com.example.CourseManagment.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public   class StudentService extends  GenericService<Student,Long> implements StudentInterface {
    private   StudentRepository studentRepository;
    private LessonService lessonService;
    private ProfessorsService professorsService;
    private  DepartmentService departmentService;
    private GradeServices gradeServices;
    private final GenericService<Student, Long> genericService;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          LessonService lessonService,
                          ProfessorsRepository professorsRepository,
                          ProfessorsService professorsService,
                          GradeServices gradeServices,
                         @Lazy GenericService <Student, Long> genericService,
                          DepartmentService departmentService

    ) {
        this.studentRepository = studentRepository;
        this.lessonService=lessonService;
        this.professorsService=professorsService;
        this.gradeServices=gradeServices;
        this.genericService=genericService;
        this.departmentService=departmentService;
    }
    @Override
    public List<Student> getStudents() {
        return genericService.getAll(Student.class);

    }

//    public List<Student> getStudents() {
//        return studentRepository.findAll();
//
//    }
//
//    public void addNewStudent(Student student) {
//        if (studentRepository.existsById(student.getNationalNum())) {
//            throw new IllegalStateException("Already student exists");
//        }
//        studentRepository.save(student);
//
//    }
@Override
   public void addNewStudent(Student student) {
        genericService.create(Student.class,student);
}

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id " + id + "does not exists");

        }
        genericService.delete(Student.class,id);
    }
    @Override
    public void AddLesson(Long id, String lesson, Long professorId) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with id " + id + "does not exist"
        ));

       Lessons newLesson= lessonService.lessonRequired(lesson);

       Professers professor=professorsService.professorRequired(professorId);

        if (!(student.getDepartment() == newLesson.getDepartment() && professor.getprofessorLessons().contains(newLesson))) {
            throw new IllegalStateException("The professor is not for the lesson's department");
        } else {
            if (student.getLessons() != null) {

                student.getLessons().add(newLesson);
            }
            studentRepository.save(student);
        }
    }

    @Override
    @Transactional
    public void updateNameOfStudent(Long id, String newName, String newLastname,Long newNationalNum,String newAddress) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with id " + id + "does not exist"
        ));
        if (newName != null &&
                newName.length() > 0 &&
                !Objects.equals(student.getFirstname(), newName)) { //if the name provided is not the same as the current one update
            student.setFirstname(newName);
        }
        if (newLastname != null &&
                newLastname.length() > 0 &&
                !Objects.equals(student.getLastname(), newLastname)) { //if the name provided is not the same as the current one update
            student.setLastname(newLastname);
        }
        student.setNationalNum(newNationalNum);
        student.setAddress(newAddress);
        studentRepository.save(student);
    }
    @Override
    public void addDepartment(Long id, String departmentName) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with id " + id + "does not exist"
        ));
        Department department=departmentService.departmentRequired(departmentName);

        student.setDepartment(department);
        studentRepository.save(student);
    }
    @Override
    public void addGrade(Long id, String lessonName, Float grade) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with id " + id + "does not exist"
        ));
        Lessons lesson = lessonService.lessonRequired(lessonName);

        if (lesson.getStudents().contains(student)) {
            if (lesson.getGrades().stream().anyMatch(g -> g.getStudent().equals(student))) {
                throw new IllegalStateException("Student already assigned a grade for lesson " + lessonName);
            }

            Grade newGrade=gradeServices.addGradeToLessonOfStudent(lessonName,student,grade);

            lessonService.addGradeOfLesson(lesson,newGrade);
        } else {
            throw new IllegalStateException("Student is not associated with lesson " + lessonName);
        }
    }

    @Override
    public float getAverageGrade(Long id) {
        float sum = 0;
        int totall=0;
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with id " + id + "does not exist"
        ));
        if (student.getGrades().isEmpty()) {
            return 0; // Return 0 if the student has no grades
        }

        for (Grade grade : student.getGrades()) {
            totall+=grade.getLesson().getCredit();
            sum += grade.getGrade()*grade.getLesson().getCredit();
        }
        return sum / totall;
    }

}







