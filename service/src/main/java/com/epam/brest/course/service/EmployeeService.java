package com.epam.brest.course.service;

import com.epam.brest.course.model.Employee;

import java.util.Collection;

/**
 * Interface of employee service.
 */
public interface EmployeeService {
    /**
     * Getting list of Employees from DB.
     * @return - list of Employees.
     */
    Collection<Employee> serviceGetEmployees();

    /**
     * Getting one employee from DB.
     * @param employeeId - id of employee in DB.
     * @return Employee.
     */
    Employee serviceGetEmployeeById(final Integer employeeId);

    /**
     * Getting the list of employees by department id.
     * @param departmentId - department id.
     * @return - list of employees.
     */
    Collection<Employee> serviceGetEmployeesByDepartmentId(
            final Integer departmentId);

    /**
     * Getting employees from DB which have salary more than employeeSalary.
     * @param employeeSalary - value of salary.
     * @return employees.
     */
    Collection<Employee> serviceGetEmployeeBySalaryMore(
            final Integer employeeSalary);

    /**
     * Calculating average salary fo list of employees.
     * @param employees - list of employees.
     * @return - average salary fo list.
     */
    Integer calculateAverageSalary(final Collection<Employee> employees);

    /**
     * Adding the employee to the DB.
     * @param employee - prepared instance of the object Employee to added.
     * @return employee that was added to the DB.
     */
    Employee serviceAddEmployee(final Employee employee);

    /**
     * Updating information about employee.
     * @param employee - prepared instance of the object Employee to update.
     */
    void serviceUpdateEmployee(final Employee employee);

    /**
     * Deleting the employee from DB.
     * @param employeeId - id of the employee that was deleted from DB.
     */
    void serviceRemoveEmployee(final Integer employeeId);
}
