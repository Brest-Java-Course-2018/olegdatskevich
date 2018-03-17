package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentAvgSalary;
import com.epam.brest.course.service.DepartmentService;
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

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/departments")
    public String getDepartments(Model model) {
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
    public String updateDepartment(@PathVariable final Integer id,
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
    public String updateDepartment(/*@Valid*/ final Department department,
                                   final BindingResult result) {
        LOGGER.debug("PostUpdateDepartment ({}, {})", department, result);
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
    public String addDepartment(final Model model) {
        LOGGER.debug("GetAddDepartment({})", model);
        Department department = new Department();
        model.addAttribute("department", department);
        model.addAttribute("isNew", true);
        return "department";
    }

    /**
     *
     * @param department
     * @param result
     * @return
     */
    @PostMapping(value = "/department")
    public String addDepartment(/*@Valid*/ final Department department,
                                final BindingResult result) {
        //TODO validation
        //TODO localization все текста из html перенести в отдельные файлы
        //accept-language
        LOGGER.debug("PostAddDepartment({},{})", department, result);
        if (result.hasErrors()) {
            return "/department";
        } else {
            departmentService.serviceAddDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     * Delete department.
     *
     * @return view name
     */
    @GetMapping(value = "/department/{id}/delete")
    public final String deleteDepartmentById(@PathVariable final Integer id,
                                             final Model model) {
        LOGGER.debug("deleteDepartmentById({},{})", id, model);
        departmentService.serviceRemoveDepartmentById(id);
        return "redirect:/departments";
    }

}
