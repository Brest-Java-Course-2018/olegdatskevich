package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getEmployees();

    Employee getEmployeeById(final Integer employeeId);

    Employee getEmployeeByName(final String employeeName);

    Employee addEmployee(final Employee employee);

    void updateEmployee(final Employee employee);

    void removeEmployee(final Integer employeeId);
}
