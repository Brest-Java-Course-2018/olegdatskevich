package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:test-db-spring.xml",
        "classpath:test-dao.xml",
        "classpath:dao.xml"})
@Rollback
@Transactional
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
        Department department = departmentDao.getDepartmentById(3);
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(3));
        Assert.assertTrue(department.getDepartmentName().equals("Accounting"));
        Assert.assertTrue(department.getDepartmentDescription().equals("Accountants Dep"));
    }

    @Test
    public void getDepartmentByName() {
        Department department = departmentDao.getDepartmentByName("Management");
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(2));
        Assert.assertTrue(department.getDepartmentName().equals("Management"));
        Assert.assertTrue(department.getDepartmentDescription().equals("Managers Dep"));
    }

    @Test
    public void addDepartment() {
        List<Department> departments = departmentDao.getDepartments();
        int sizeBefore = departments.size();
        Department testDepartment = new Department("TestAddDep", "TestAddDepDescription");
        Department newDepartment = departmentDao.addDepartment(testDepartment);
        Assert.assertNotNull(newDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentName().equals(testDepartment.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDepartmentDescription().equals(testDepartment.getDepartmentDescription()));
        Assert.assertTrue((sizeBefore + 1) == departmentDao.getDepartments().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addSameDepartment() {
        Department testDepartment = new Department("TestAddSameDep", "TestAddSameDepDescription");
        departmentDao.addDepartment(testDepartment);
        departmentDao.addDepartment(testDepartment);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void addSameDepartmentWithRule() {
        Department testDepartment = new Department("TestAddSameDepRule", "TestAddSameDepDescriptionRule");
        departmentDao.addDepartment(testDepartment);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Department with the same name already exists in DB.");
        departmentDao.addDepartment(testDepartment);
    }

    @Test
    public void updateDepartment() {
        Department testDepartment = new Department("TestUpdateDep", "TestUpdateDepDescription");
        Department newDepartment = departmentDao.addDepartment(testDepartment);
        newDepartment.setDepartmentName("NEWTestUpdateDep");
        newDepartment.setDepartmentDescription("NEWTestUpdateDepDescription");
        departmentDao.updateDepartment(newDepartment);
        Department updatedDepartment = departmentDao.getDepartmentById(newDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentId().equals(updatedDepartment.getDepartmentId()));
        Assert.assertTrue(newDepartment.getDepartmentName().equals(updatedDepartment.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDepartmentDescription().equals(updatedDepartment.getDepartmentDescription()));
    }

    @Test
    public void removeDepartmentById() {
        Department testDepartment = new Department("TestRemoveDep", "TestRemoveDepDescription");
        testDepartment = departmentDao.addDepartment(testDepartment);
        List<Department> departments = departmentDao.getDepartments();
        int sizeBefore = departments.size();
        departmentDao.removeDepartmentById(testDepartment.getDepartmentId());
        Assert.assertTrue((sizeBefore - 1) == departmentDao.getDepartments().size());
    }
}