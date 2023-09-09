package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.*;
import com.example.CourseManagment.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public   class StudentService {
    StudentRepository studentRepository;
    LessonsRepository lessonsRepository;
    ProfessorsRepository professorsRepository;
    DepartmentRepository departmentRepository;

    GradeRepository gradeRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          LessonsRepository lessonsRepository,
                          ProfessorsRepository professorsRepository,
                          DepartmentRepository departmentRepository,
                          GradeRepository gradeRepository

    ) {
        this.studentRepository = studentRepository;
        this.lessonsRepository = lessonsRepository;
        this.professorsRepository = professorsRepository;
        this.departmentRepository = departmentRepository;
        this.gradeRepository = gradeRepository;
    }
public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
}

    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        if (studentRepository.existsById(student.getNationalNum())) {
            throw new IllegalStateException("Already student exists");
        }
        studentRepository.save(student);

    }

    public void deletStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id " + id + "does not exists");

        }
        studentRepository.deleteById(id);
    }

    public void AddLesson(Long id, String lesson, Long professor) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with id " + id + "does not exist"
        ));

        Lessons newLesson = lessonsRepository.findById(lesson).orElseThrow(() -> new IllegalStateException(
                "Lesson " + lesson + "does not exist"
        ));

        Professers professoRequiredr = professorsRepository.findById(professor).orElseThrow(() -> new IllegalStateException(
                "professor with id " + professor + "does not exist"
        ));

        if (!(student.getDepartment() == newLesson.getDepartment() && professoRequiredr.getprofessorLessons().contains(newLesson))) {
            throw new IllegalStateException("The professor is not for the lesson's department");
        } else {
            if (student.getLessons() != null) {

                student.getLessons().add(newLesson);
            }
            studentRepository.save(student);
        }
    }


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

    public void addDepartment(Long id, String departmentName) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with id " + id + "does not exist"
        ));
        Department department = departmentRepository.findById(departmentName).orElseThrow(() -> new IllegalStateException(
                "Department " + departmentName + "does not exists"
        ));
        student.setDepartment(department);
        studentRepository.save(student);
    }

    public void addGrade(Long id, String lessonName, Float grade) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with id " + id + "does not exist"
        ));
        Lessons lesson = lessonsRepository.findById(lessonName).orElseThrow(() -> new IllegalStateException(
                "Lesson " + lessonName + "does not exist"
        ));

        if (lesson.getStudents().contains(student)) {
            if (lesson.getGrades().stream().anyMatch(g -> g.getStudent().equals(student))) {
                throw new IllegalStateException("Student already assigned a grade for lesson " + lessonName);
            }

            Grade newGrade = new Grade();
            newGrade.setLesson(lesson);
            newGrade.setStudent(student);
            newGrade.setGrade(grade);
            //newGrade.setDepartmentOfGrade(lesson.getDepartment());
            gradeRepository.save(newGrade);

            lesson.getGrades().add(newGrade);
            lessonsRepository.save(lesson);
        } else {
            throw new IllegalStateException("Student is not associated with lesson " + lessonName);
        }
    }


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







