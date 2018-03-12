package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class DepartmentServiceImplMockTest {

    private static final int ID = 1;
    private static final String DEPARTMENT_DESCRIPTION = "Service department";
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
    public void serviceUpdateDepartmentDescriptionTest() {
        EasyMock.expect(mockDepartmentDao.getDepartmentById(EasyMock.anyInt())).
                andReturn(DEPARTMENT);
        Capture<Department> captureArgument = Capture.newInstance();
        mockDepartmentDao.updateDepartment(EasyMock.capture(captureArgument));
        EasyMock.expectLastCall();
        EasyMock.replay(mockDepartmentDao);

        departmentService.serviceUpdateDepartmentDescription(ID,
                DEPARTMENT_DESCRIPTION);

        Department department = captureArgument.getValue();
        Assert.assertEquals(DEPARTMENT_DESCRIPTION,
                department.getDepartmentDescription());
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
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void serviceAddDepartmentTest() {
        EasyMock.expect(mockDepartmentDao.addDepartment(DEPARTMENT)).
                andReturn(new Department());
        EasyMock.replay(mockDepartmentDao);
        departmentService.serviceAddDepartment(DEPARTMENT);
        EasyMock.verify(mockDepartmentDao);
    }

    @Test
    public void serviceRemoveDepartmentByIdTest() {
        mockDepartmentDao.removeDepartmentById(ID);
        EasyMock.expectLastCall();
        EasyMock.replay(mockDepartmentDao);
        departmentService.serviceRemoveDepartmentById(ID);
    }
}
