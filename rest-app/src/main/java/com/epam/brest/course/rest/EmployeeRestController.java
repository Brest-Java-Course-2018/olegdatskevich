package com.epam.brest.course.rest;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Employee REST controller.
 */
@RestController
public class EmployeeRestController {

    /**
     * Logger for employee REST controller.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Employee service.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * GET employees.
     * @return - collection of employee.
     */
    @GetMapping(value = "/employees")
    public final Collection<Employee> employees() {
        LOGGER.debug("REST employees()");
        return employeeService.serviceGetEmployees();
    }

    /**
     * GET employee by id.
     * @param id - employee's id.
     * @return - employee.
     */
    @GetMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final Employee employeeById(
            @PathVariable(value = "id") final Integer id) {
        LOGGER.debug("REST employeeById({})", id);
        return employeeService.serviceGetEmployeeById(id);
    }

    /**
     * Post employee
     * @param employee - employee for posting.
     * @return posted employee.
     */
    @GetMapping(value = "/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public final Employee addEmployee(@RequestBody final Employee employee) {
        LOGGER.debug("REST addEmployee({})", employee);
        return employeeService.serviceAddEmployee(employee);
    }

    /**
     * Delete employee from DB.
     * @param id - employee's id for removing.
     */
    @DeleteMapping(value = "/employees/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final void deleteEmployee(
            @PathVariable(value = "id") final Integer id) {
        LOGGER.debug("REST deleteEmployee({})", id);
        employeeService.serviceRemoveEmployee(id);
    }

}
