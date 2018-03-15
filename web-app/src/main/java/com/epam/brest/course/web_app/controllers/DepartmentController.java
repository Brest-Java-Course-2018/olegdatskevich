package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
//List to Collection
    //Empty Collection
    @GetMapping(value = "/departments")
    public String getDepartments(Model model) {
        List<Department> departments =
                departmentService.serviceGetDepartments();
        //Collections.emptyList();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping(value = "/add_department")
    public String addDepartment(Model model) {
        return "add_department";
    }

    @GetMapping(value = "/edit_department/{id}")
    public String editDepartment(@PathVariable Integer id, Model model) {
        Department department = departmentService.serviceGetDepartmentById(id);
        model.addAttribute("department", department);
        return "edit_department";
    }
}
