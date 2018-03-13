package com.epam.brest.course.model;

import org.junit.Assert;
import org.junit.Test;

public class DepartmentTest {

    private static final Integer DEP_ID = 1;
    private static final String DEP_NAME = "DepName";
    private static final String DEP_DESCR = "DepDescr";

    @Test
    public void getDepartmentId() {
        Department department = new Department();
        department.setDepartmentId(DEP_ID);
        Assert.assertTrue(department.getDepartmentId().equals(DEP_ID));
        Assert.assertEquals("Id test.", department.getDepartmentId(), DEP_ID);
    }

    @Test
    public void getDepartmentName() {
        Department department = new Department();
        department.setDepartmentName(DEP_NAME);
        Assert.assertTrue(department.getDepartmentName().equals(DEP_NAME));
        Assert.assertEquals("Name test.", department.getDepartmentName(), DEP_NAME);
    }

    @Test
    public void getDepartmentDescription() {
        Department department = new Department();
        department.setDepartmentDescription(DEP_DESCR);
        Assert.assertTrue(department.getDepartmentDescription().equals(DEP_DESCR));
        Assert.assertEquals("Description test.", department.getDepartmentDescription(), DEP_DESCR);
    }

    @Test
    public void departmentConstructorTest() {
        Department department = new Department(DEP_NAME, DEP_DESCR);
        Assert.assertTrue(department.equals(new Department(DEP_NAME, DEP_DESCR)));
    }
}
