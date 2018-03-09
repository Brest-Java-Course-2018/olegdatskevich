package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;

import java.util.List;

/**
 * DAO for Employee interface.
 */
public interface EmployeeDao {
    /**
     * Getting list of Employees from DB.
     * @return List of Employees.
     */
    List<Employee> getEmployees();

    /**
     * Getting one employee from DB.
     * @param employeeId - id of employee in DB.
     * @return Employee.
     */
    Employee getEmployeeById(final Integer employeeId);

    /**
     * Getting one employee from DB.
     * @param employeeName - name of employee in DB.
     * @return employee.
     */
    Employee getEmployeeByName(final String employeeName);

    /**
     * Adding the employee to the DB.
     * @param employee - prepared instance of the object Employee to added.
     * @return employee that was added to the DB.
     */
    Employee addEmployee(final Employee employee);

    /**
     * Updating information about employee.
     * @param employee - prepared instance of the object Employee to update.
     */
    void updateEmployee(final Employee employee);

    /**
     * Deleting the employee from DB.
     * @param employeeId - id of the employee that was deleted from DB.
     */
    void removeEmployee(final Integer employeeId);
}