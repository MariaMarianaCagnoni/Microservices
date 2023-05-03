package com.example.departmentservice.controller;

import com.example.departmentservice.client.EmployeeClient;
import com.example.departmentservice.model.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {


    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController(EmployeeClient employeeClient, DepartmentRepository departmentRepository) {
        this.employeeClient = employeeClient;
        this.departmentRepository = departmentRepository;
    }

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private DepartmentRepository departmentRepository;


    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        LOGGER.info("Department add: {} ", department);
        return this.departmentRepository.addDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable Long id) {
        LOGGER.info("Department Id: {} ", id);

        return this.departmentRepository.findById(id);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        LOGGER.info("Department find");

        return this.departmentRepository.findAll();
    }

    @GetMapping("/with-employees")
    public List<Department> getAllDepartmentsWithEmployees() {
        LOGGER.info("Department find");

        List<Department> departments = departmentRepository.findAll();
        departments
                .forEach(department -> department.setEmployees(employeeClient
                        .findByDepartment(department.getId())));
        return departments;
    }

}
