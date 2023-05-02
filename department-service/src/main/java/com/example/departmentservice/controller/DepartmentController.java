package com.example.departmentservice.controller;

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

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;

    }

    @PostMapping
    public Department addDepartment(@RequestBody Department department){
        LOGGER.info("Department add: {} ",department);
        return this.departmentRepository.addDepartment(department);
    }
    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable Long id){
        LOGGER.info("Department Id: {} ",id);

        return this.departmentRepository.findById(id);
    }

    @GetMapping
    public List<Department> getAllDepartments(){
        LOGGER.info("Department find");

        return this.departmentRepository.findAll();
    }

}
