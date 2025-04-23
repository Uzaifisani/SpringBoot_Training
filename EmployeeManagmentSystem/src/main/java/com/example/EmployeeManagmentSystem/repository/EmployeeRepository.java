package com.example.EmployeeManagmentSystem.repository;

import com.example.EmployeeManagmentSystem.entity.Employee;

import jakarta.persistence.EntityListeners;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EntityListeners(AuditingEntityListener.class)
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Page<Employee> findByIsDeletedFalse(Pageable pageable);
    List<Employee> findByDepartmentAndIsDeletedFalse(String department);
}
