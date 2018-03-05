package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml", "classpath:test-dao.xml"})
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
    public void getDepartmentByName() {
        Department department = departmentDao.getDepartmentByName("Development");
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(1));
        Assert.assertTrue(department.getDepartmentName().equals("Development"));
        Assert.assertTrue(department.getDepartmentDescription().equals("Developers"));
    }

    @Test
    public void addDepartment() {
        Department department = new Department("TestDepartment",
                "TestDep");
        departmentDao.addDepartment(department);
        int i = 0;
        for (Department dep:departmentDao.getDepartments()) {
            if(dep.getDepartmentName().equals(department.getDepartmentName())){
                i = dep.getDepartmentId();
            }
        }
        Assert.assertEquals(department.getDepartmentName(),
                departmentDao.getDepartmentById(i).getDepartmentName());
    }

    @Test
    public void updateDepartment() {
        Department oldDepartment = departmentDao.getDepartmentById(1);
        Department newDepartment = new Department();
        newDepartment.setDepartmentId(oldDepartment.getDepartmentId());
        newDepartment.setDepartmentName(oldDepartment.getDepartmentName());
        newDepartment.setDepartmentDescription(oldDepartment.getDepartmentDescription()+ "_NEW");
        departmentDao.updateDepartment(newDepartment);
        Department testedDepartment = departmentDao.getDepartmentById(1);
        Assert.assertEquals(newDepartment.getDepartmentDescription(),
                testedDepartment.getDepartmentDescription());
    }

    @Test
    public void removeDepartmentById() {
        boolean test = false;
        Department delDepartment =
                new Department("DelName", "DelDescription");
        delDepartment = departmentDao.addDepartment(delDepartment);
        Assert.assertEquals(delDepartment,
                departmentDao.getDepartmentById(delDepartment.getDepartmentId()));
        departmentDao.removeDepartmentById(delDepartment.getDepartmentId());
        try {
            departmentDao.getDepartmentById(delDepartment.getDepartmentId());
        } catch (EmptyResultDataAccessException e) {
            test = true;
        }
        Assert.assertTrue(test);
    }
}