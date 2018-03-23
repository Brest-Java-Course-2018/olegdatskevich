package com.epam.brest.course.rest;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Department REST controller.
 */
@RestController
public class DepartmentRestController {

    /**
     * Logger for department REST controller.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * department service.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     * GET departments.
     * @return - collection of department.
     */
    @GetMapping(value = "/departments")
    public final Collection<Department> departments() {
        LOGGER.debug("REST departments()");
        return departmentService.serviceGetDepartments();
    }

    /**
     * GET department by id.
     * @param id - department's id.
     * @return - department.
     */
    @GetMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Department departmentById(
            @PathVariable(value = "id") final Integer id) {
        LOGGER.debug("REST departmentById({})", id);
        return departmentService.serviceGetDepartmentById(id);
    }

    /**
     * Post department
     * @param department - department for posting.
     * @return posted department.
     */
    @PostMapping(value = "/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public final Department addDepartment(
            @RequestBody final Department department) {
        LOGGER.debug("REST addDepartment({})", department);
        return departmentService.serviceAddDepartment(department);
    }

    /**
     * Delete department from DB.
     * @param id - department's id for removing.
     */
    @DeleteMapping(value = "/departments/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final void deleteDepartment(
            @PathVariable(value = "id") final Integer id) {
        LOGGER.debug("REST deleteDepartment({})", id);
        departmentService.serviceRemoveDepartmentById(id);

    }
}
