package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:test-db-spring.xml",
        "classpath:dao.xml",
        "classpath:service-test.xml"})
public class DepartmentServiceImplTest {

    private static final int ID = 1;
    private static final String DEPARTMENT_DESCRIPTION = "Service department";

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void getDepartmentById() {
        departmentService.updateDepartmentDescription(ID, DEPARTMENT_DESCRIPTION);
        Department department = departmentService.getDepartmentById(ID);
        Assert.assertEquals(DEPARTMENT_DESCRIPTION, department.getDepartmentDescription());
    }

    @Test
    public void updateDepartmentDescription() {
    }
}