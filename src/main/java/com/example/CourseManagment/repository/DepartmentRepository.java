package com.example.CourseManagment.repository;

import com.example.CourseManagment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,String> {


    @Query("SELECT d FROM Department d WHERE d.departmentName = ?1")
    Optional<Department> findByDepartmentName(String DepartmentName);

}
