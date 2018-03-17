package com.epam.brest.course.service;

import com.epam.brest.course.dao.EmployeeDao;
import com.epam.brest.course.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

/**
 * Interface of employee service.
 */
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * Logger for EmployeeServiceImpl.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * For working with DAO layer Departments.
     */
    private EmployeeDao employeeDao;

    /**
     * Constructor.
     * @param employeeDao - employee dao.
     */
    public EmployeeServiceImpl(final EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * Getting list of Employees from DB.
     * @return - list of Employees.
     */
    @Override
    public final Collection<Employee> serviceGetEmployees() {
        Collection<Employee> employees = employeeDao.getEmployees();
        LOGGER.debug("serviceGetEmployees {} pc.", employees.size());
        return employees;
    }

    /**
     * Getting one employee from DB.
     * @param employeeId - id of employee in DB.
     * @return Employee.
     */
    @Override
    public final Employee serviceGetEmployeeById(final Integer employeeId) {
        Employee employee = employeeDao.getEmployeeById(employeeId);
        LOGGER.debug("serviceGetEmployeeById {}", employeeId);
        return employee;
    }

    /**
     * Getting the list of employees by department id.
     * @param departmentId - department id.
     * @return - list of employees.
     */
    @Override
    public final Collection<Employee> serviceGetEmployeesByDepartmentId(
            final Integer departmentId) {
        LOGGER.debug("serviceGetEmployeesByDepartmentId {}", departmentId);
        Collection<Employee> employees = employeeDao.getEmployees();
        Collection<Employee> employeesByDepartmentId = null;
        for (Employee employee : employees) {
            if (employee.getDepartmentId().equals(departmentId)) {
                employeesByDepartmentId.add(employee);
            }
        }
        return employeesByDepartmentId;
    }

    /**
     * Calculating average salary fo list of employees.
     * @param employees - list of employees.
     * @return - average salary fo list.
     */
    @Override
    public final Integer calculateAverageSalary(final Collection<Employee> employees) {
        Integer averageSalary = 0;
        if (employees != null) {
            for (Employee employee: employees) {
                averageSalary += employee.getEmployeeSalary();
            }
            return averageSalary / employees.size();
        } else {
            return averageSalary;
        }
    }

    /**
     * Getting employees from DB which have salary more than employeeSalary.
     * @param employeeSalary - value of salary.
     * @return employees.
     */
    @Override
    public final Collection<Employee> serviceGetEmployeeBySalaryMore(
            final Integer employeeSalary) {
        LOGGER.debug("serviceGetEmployeeByName {}", employeeSalary);
        Collection<Employee> employees = employeeDao.getEmployees();
        Collection<Employee> employeesSalaryMore = null;
        for (Employee employee: employees) {
            if (employee.getEmployeeSalary() >= employeeSalary) {
                employeesSalaryMore.add(employee);
            }
        }
        return employeesSalaryMore;
    }

    /**
     * Adding the employee to the DB.
     * @param employee - prepared instance of the object Employee to added.
     * @return employee that was added to the DB.
     */
    @Override
    public final Employee serviceAddEmployee(final Employee employee) {
        Employee serviceEmployee = employeeDao.addEmployee(employee);
        LOGGER.debug("serviceAddEmployee {}", employee);
        return serviceEmployee;
    }

    /**
     * Updating information about employee.
     * @param employee - prepared instance of the object Employee to udate.
     */
    @Override
    public final void serviceUpdateEmployee(final Employee employee) {
        LOGGER.debug("serviceUpdateEmployee({})", employee);
        employeeDao.updateEmployee(employee);
    }

    /**
     * Deleting the employee from DB.
     * @param employeeId - id of the employee that was deleted from DB.
     */
    @Override
    public final void serviceRemoveEmployee(final Integer employeeId) {
        LOGGER.debug("serviceRemoveEmployee {}", employeeId);
        employeeDao.removeEmployee(employeeId);
    }
}
