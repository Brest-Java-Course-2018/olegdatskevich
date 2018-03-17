package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class DepartmentServiceImplMockTest {

    private static final int ID = 1;
    private static final Department DEPARTMENT = new Department(
            "Development", "Developers Dep");

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentDao mockDepartmentDao;

    @Before
    public void setUp() {
        EasyMock.reset(mockDepartmentDao);
    }

    @After
    public void tearDown() {
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void serviceGetDepartmentsTest() {
        List<Department> departments = new ArrayList<>();
        EasyMock.expect(mockDepartmentDao.getDepartments()).
                andReturn(departments);
        EasyMock.replay(mockDepartmentDao);
        departmentService.serviceGetDepartments();
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void serviceGetDepartmentByIdTest() {
        EasyMock.expect(mockDepartmentDao.getDepartmentById(ID)).
                andReturn(new Department());
        EasyMock.replay(mockDepartmentDao);
        departmentService.serviceGetDepartmentById(ID);
    }

    @Test
    public void serviceAddDepartmentTest() {
        EasyMock.expect(mockDepartmentDao.addDepartment(DEPARTMENT)).
                andReturn(new Department());
        EasyMock.replay(mockDepartmentDao);
        departmentService.serviceAddDepartment(DEPARTMENT);
    }

    @Test
    public void serviceUpdateDepartmentTest() {
        mockDepartmentDao.updateDepartment(DEPARTMENT);
        EasyMock.expectLastCall();
        EasyMock.replay(mockDepartmentDao);
        departmentService.serviceUpdateDepartment(DEPARTMENT);
    }

    @Test
    public void serviceRemoveDepartmentByIdTest() {
        mockDepartmentDao.removeDepartmentById(ID);
        EasyMock.expectLastCall();
        EasyMock.replay(mockDepartmentDao);
        departmentService.serviceRemoveDepartmentById(ID);
    }
}
