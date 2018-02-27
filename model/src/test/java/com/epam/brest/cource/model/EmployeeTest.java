package com.epam.brest.cource.model;

import com.epam.brest.cource.model.Employee;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeTest {

    public static final String OLEG = "Oleg";

    @Test
    public void getEmployeeName() {
        Employee employee = new Employee();
        employee.setEmployeeName(OLEG);
        Assert.assertTrue(employee.getEmployeeName().equals(OLEG));
        Assert.assertEquals("Name", employee.getEmployeeName(), OLEG);
    }
}