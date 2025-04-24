package com.example.EmployeeManagmentSystem.service;

import com.example.EmployeeManagmentSystem.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Page<Employee> getAllEmployees(int page, int size, String sortField, String sortDir);
    Employee getEmployeeByid(Long id);
    Employee updateEmployee(Long id, Employee updatedEmployee);
    void softDeleteEmployee(Long id);
    List<Employee> getEmployeeListByDepartment(String department);

    List<Employee> searchEmployeesByName(String name);
    List<Employee> getEmployeesWithSalaryGreaterThan(Double salary);
}
