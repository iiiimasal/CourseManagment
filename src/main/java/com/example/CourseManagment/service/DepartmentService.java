package com.example.CourseManagment.service;

import com.example.CourseManagment.entity.Department;
import com.example.CourseManagment.entity.Grade;
import com.example.CourseManagment.entity.Professers;
import com.example.CourseManagment.entity.Student;
import com.example.CourseManagment.repository.DepartmentRepository;
import com.example.CourseManagment.repository.GradeRepository;
import com.example.CourseManagment.repository.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DepartmentService  extends GenericService<Department,String>{
    DepartmentRepository departmentRepository;
    ProfessorsRepository professorsRepository;
//    GradeRepository gradeRepository;
    private GenericService<Department,String>departmentGeneric;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository  ,
                             ProfessorsRepository professorsRepository,
                             @Lazy GenericService<Department,String>departmentGeneric
                             ) {
        this.departmentRepository = departmentRepository;
        this.professorsRepository=professorsRepository;
        this.departmentGeneric=departmentGeneric;
        //this.gradeRepository=gradeRepository;
    }



    public List<Department> getDepartments() {
        return departmentGeneric.getAll(Department.class);
    }
    public  void createNewDepartment(Department department) {
       departmentGeneric.create(Department.class,department);
    }



    public void DeleteDepartment(String departmentName) {

        departmentGeneric.delete(Department.class,departmentName);
    }



 public float averageOfDepartmentScore(String departmentName){
        float sum=0;
        int total=0;
    float avgOflesson=0;
        Department department = departmentRepository.findById(departmentName)
                .orElseThrow(() -> new IllegalStateException("Department " + departmentName + " does not exist"));
        for (Student student: department.getStudentListTotal()){
            for (Grade grade : student.getGrades()) {
                total+=grade.getLesson().getCredit();
                sum += grade.getGrade()*grade.getLesson().getCredit();
            }
            avgOflesson += sum / total;
//            // Reset sum and total for the next lesson
            sum = 0;
            total = 0;
        }
         return avgOflesson / department.getStudentListTotal().size();

}
}
