package com.epam.brest.course.model;

import org.junit.Assert;
import org.junit.Test;

public class EmployeeTest {

    private static final Integer ID = 2;
    private static final String OLEG = "Oleg";
    private static final String EMAIL = "oleg@ex.com";
    private static final Integer SALARY = 1000;
    private static final Integer DEP_ID = 1;

    @Test
    public void getEmployeeId() {
        Employee employee = new Employee();
        employee.setEmployeeId(ID);
        Assert.assertTrue(employee.getEmployeeId().equals(ID));
        Assert.assertEquals("Id test.", employee.getEmployeeId(), ID);
    }

    @Test
    public void getEmployeeName() {
        Employee employee = new Employee();
        employee.setEmployeeName(OLEG);
        Assert.assertTrue(employee.getEmployeeName().equals(OLEG));
        Assert.assertEquals("Name test.", employee.getEmployeeName(), OLEG);
    }

    @Test
    public void getEmployeeSalary() {
        Employee employee = new Employee();
        employee.setEmployeeSalary(SALARY);
        Assert.assertTrue(employee.getEmployeeSalary().equals(SALARY));
        Assert.assertEquals("Salary test.", employee.getEmployeeSalary(), SALARY);
    }

    @Test
    public void getDepartmentId() {
        Employee employee = new Employee();
        employee.setDepartmentId(DEP_ID);
        Assert.assertTrue(employee.getDepartmentId().equals(DEP_ID));
        Assert.assertEquals("Department id test.", employee.getDepartmentId(), DEP_ID);
    }

    @Test
    public void employeeConstructorTest() {
        Employee employee = new Employee(OLEG, EMAIL, SALARY, DEP_ID);
        Assert.assertTrue(employee.equals(new Employee(OLEG, EMAIL, SALARY, DEP_ID)));
    }
}
