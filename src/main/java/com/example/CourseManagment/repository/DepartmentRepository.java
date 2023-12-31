package com.example.CourseManagment.repository;

import com.example.CourseManagment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
public interface DepartmentRepository extends JpaRepository<Department,String> {


    boolean existsByDepartmentName(String departmentName);
    Optional<Department> findByDepartmentName(String departmentName);
}
