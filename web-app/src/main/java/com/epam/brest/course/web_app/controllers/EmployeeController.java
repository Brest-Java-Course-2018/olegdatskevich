package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employees")
    public String employees(Model model) {
        Collection<Employee> employees = employeeService.serviceGetEmployees();
        //Collections.emptyList();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping(value = "/add_employee")
    public String addEmployee(Model model) {
        return "add_employee";
    }

    @GetMapping(value = "/edit_employee/{id}")
    public String editEmployee(@PathVariable Integer id, Model model) {
        Employee employee = employeeService.serviceGetEmployeeById(id);
        model.addAttribute("employee", employee);
        return "edit_employee";
    }
}
