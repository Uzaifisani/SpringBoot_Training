package com.example.EmployeeManagmentSystem.service;

import com.example.EmployeeManagmentSystem.entity.Employee;
import com.example.EmployeeManagmentSystem.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Page<Employee> getAllEmployees(int page, int size, String sortField, String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return employeeRepository.findByIsDeletedFalse(pageable);
    }

    @Override
    public Employee getEmployeeByid(Long id) {
         return employeeRepository.findById(id)
                 .filter(e->!e.getIsDeleted())
                 .orElseThrow(()->new RuntimeException("Employee not Found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = getEmployeeByid(id);
        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setSalary(updatedEmployee.getSalary());
        return employeeRepository.save(employee);
    }

    @Override
    public void softDeleteEmployee(Long id) {
        Employee employee = getEmployeeByid(id);
        employee.setIsDeleted(true);
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeeListByDepartment(String department) {
        return  employeeRepository.findByDepartmentAndIsDeletedFalse(department);
    }

    @Override
    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.searchByName(name);
    }

    @Override
    public List<Employee> getEmployeesWithSalaryGreaterThan(Double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }
}
