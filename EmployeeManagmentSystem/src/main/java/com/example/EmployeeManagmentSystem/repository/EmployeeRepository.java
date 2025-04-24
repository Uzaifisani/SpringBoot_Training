package com.example.EmployeeManagmentSystem.repository;

import com.example.EmployeeManagmentSystem.entity.Employee;

import jakarta.persistence.EntityListeners;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EntityListeners(AuditingEntityListener.class)
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Page<Employee> findByIsDeletedFalse(Pageable pageable);
    List<Employee> findByDepartmentAndIsDeletedFalse(String department);

    @Query("SELECT e FROM Employee e WHERE e.isDeleted = false AND e.name LIKE %:name%")
    List<Employee> searchByName(@Param("name") String name);

    @Query("SELECT e FROM Employee e WHERE e.isDeleted = false AND e.salary > :salary")
    List<Employee> findBySalaryGreaterThan(@Param("salary") Double salary);
}
