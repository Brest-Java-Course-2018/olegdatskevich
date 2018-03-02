package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:test-dao.xml"})
public class DepartmentDaoImplTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void getDepartments() {
        List<Department> departments = departmentDao.getDepartments();
        Assert.assertFalse(departments.isEmpty());
    }

    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(1);
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(1));
        Assert.assertTrue(department.getDepartmentName().equals("Development"));
        Assert.assertTrue(department.getDepartmentDescription().equals("Developers"));
    }

    @Test
    public void addDepartment() {
        Department department = departmentDao.addDepartment(
                new Department(2,"Management", "Managers"));
        Assert.assertNotNull(department);
        Assert.assertEquals("Management", department.getDepartmentName());
    }

    @Test
    public void updateDepartment() {
        Department departmentUpdated = departmentDao.getDepartmentById(1);
        Department departmentNotUpdated = departmentDao.getDepartmentById(1);
        Assert.assertNotNull(departmentUpdated);
        Assert.assertNotNull(departmentNotUpdated);
        departmentUpdated.setDepartmentDescription("DEV");
        Assert.assertTrue(departmentUpdated.getDepartmentId().equals(departmentNotUpdated.getDepartmentId()));
        Assert.assertTrue(departmentUpdated.getDepartmentName().equals(departmentNotUpdated.getDepartmentName()));
        Assert.assertFalse(departmentUpdated.getDepartmentDescription().equals(departmentNotUpdated.getDepartmentDescription()));
    }

    @Test
    public void removeDepartmentById() {
    }
}