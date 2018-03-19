package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentAvgSalary;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    /**
     * Logger for Department controller.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     *
     */
    @Autowired
    private DepartmentService departmentService;

    //@Autowired
    //private MessageSource messageSource;

    /**
     * Goto departments list page.
     * @param model
     * @return
     */
    @GetMapping(value = "/departments")
    public final String getDepartments(final Model model) {
        LOGGER.debug("GetDepartments({})", model);
        Collection<DepartmentAvgSalary> departments =
                departmentService.serviceDepartmentAvgSalary();
        model.addAttribute("departments", departments);
        return "departments";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/department/{id}")
    public final String updateDepartment(@PathVariable final Integer id,
                                   final Model model) {
        LOGGER.debug("GetUpdateDepartment({},{})", id, model);
        Department department = departmentService.serviceGetDepartmentById(id);
        model.addAttribute("department", department);
        model.addAttribute("isNew", false);
        return "department";
    }

    /**
     *
     * @param department
     * @param result
     * @return
     */
    @PostMapping(value = "/department/{id}")
    public final String updateDepartment(@Valid final Department department,
                                         final BindingResult result) {
        LOGGER.debug("PostUpdateDepartment ({}, {})", department, result);

        System.out.println("Has errors="+result.hasErrors()); // Output: Has errors=true
        for (FieldError err : result.getFieldErrors()){
            System.out.println(err.getDefaultMessage()); // Output: must be greater than or equal to 10
        }
//        if (result.hasErrors()){
//            return "/department";
//        }
//        return "redirect:/departments";
        if (result.hasErrors()) {
            return "/department";
        } else {
            this.departmentService.serviceUpdateDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/department")
    public final String addDepartment(final Model model) {
        LOGGER.debug("GetAddDepartment({})", model);
        Department department = new Department();
        model.addAttribute("department", department);
        model.addAttribute("isNew", true);
        return "department";
    }

    /**
     * Post method for department.
     * @param department - for adding.
     * @param result
     * @return
     */
    @PostMapping(value = "/department")
    public final String addDepartment(@Valid final Department department,
                                final BindingResult result) {
        LOGGER.debug("PostAddDepartment({},{})", department, result);
        System.out.println("Has errors="+result.hasErrors()); // Output: Has errors=true
        for (FieldError err : result.getFieldErrors()){
            System.out.println(err.getDefaultMessage()); // Output: must be greater than or equal to 10
        }
        if (result.hasErrors()) {
            return "/department";
        } else {
            departmentService.serviceAddDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     * Delete department.
     * @param id - department's id for deleting department.
     * @return
     */
    @GetMapping(value = "/department/{id}/delete")
    public final String deleteDepartmentById(@PathVariable final Integer id) {
        LOGGER.debug("deleteDepartmentById({})", id);
        departmentService.serviceRemoveDepartmentById(id);
        return "redirect:/departments";
    }
}
