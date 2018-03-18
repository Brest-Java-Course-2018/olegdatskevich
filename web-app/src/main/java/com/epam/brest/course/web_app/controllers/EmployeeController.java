package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.DepartmentService;
import com.epam.brest.course.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Logger for Employee controller.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/employees")
    public String getEmployees(final Model model) {
        LOGGER.debug("GetEmployees({})", model);
        Collection<Employee> employees = employeeService.serviceGetEmployees();
        Collection<Department> departments = departmentService.serviceGetDepartments();
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        return "employees";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/employee/{id}")
    public String updateEmployee(@PathVariable final Integer id,
                                 final Model model) {
        LOGGER.debug("GetUpdateEmployee({},{})", id, model);
        Employee employee = employeeService.serviceGetEmployeeById(id);
        Collection<Department> departments = departmentService.serviceGetDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("employee", employee);
        model.addAttribute("isNew", false);
        return "employee";
    }

    /**
     *
     * @param employee
     * @param result
     * @return
     */
    @PostMapping(value = "/employee/{id}")
    public String updateEmployee(/*@Valid*/ final Employee employee,
                                              final BindingResult result) {
        LOGGER.debug("PostUpdateEmployee({}, {})", employee, result);
        if (result.hasErrors()) {
            return "/employee";
        } else {
            this.employeeService.serviceUpdateEmployee(employee);
            return "redirect:/employees";
        }
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/employee")
    public String addEmployee(final Model model) {
        LOGGER.debug("GetAddEmployee({})", model);
        Employee employee = new Employee();
        Collection<Department> departments = departmentService.serviceGetDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("employee", employee);
        model.addAttribute("isNew", true);
        return "employee";
    }

    /**
     *
     * @param employee
     * @param result
     * @return
     */
    @PostMapping(value = "/employee")
    public String addEmployee(/*@Valid*/ final Employee employee,
                                         final BindingResult result) {
        //TODO validation
        //TODO localization все текста из html перенести в отдельные файлы
        //accept-language
        LOGGER.debug("PostAddEmployee({},{})", employee, result);
        if (result.hasErrors()) {
            return "/employee";
        } else {
            employeeService.serviceAddEmployee(employee);
            return "redirect:/employees";
        }
    }

    /**
     * Delete employee.
     *
     * @return view name
     */
    @GetMapping(value = "/employee/{id}/delete")
    public final String deleteEmployeeById(@PathVariable final Integer id,
                                           final Model model) {
        LOGGER.debug("deleteEmployeeById({},{})", id, model);
        employeeService.serviceRemoveEmployee(id);
        return "redirect:/employees";
    }
}
