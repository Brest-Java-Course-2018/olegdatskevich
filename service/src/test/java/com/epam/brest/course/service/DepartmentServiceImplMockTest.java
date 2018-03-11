package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class DepartmentServiceImplMockTest {

    private static final int ID = 1;
    private static final String DEPARTMENT_DESCRIPTION = "Service department";
    private static final Department DEPARTMENT =
            new Department("Development", "Developers Dep");

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentDao mockDepartmentDao;

    @Test
    public void serviceUpdateDepartmentDescription() {
        EasyMock.expect(mockDepartmentDao.getDepartmentById(EasyMock.anyInt())).andReturn(DEPARTMENT);
        Capture<Department> captureArgument = Capture.newInstance();
        mockDepartmentDao.updateDepartment(EasyMock.capture(captureArgument));
        EasyMock.expectLastCall();
        EasyMock.replay(mockDepartmentDao);

        departmentService.serviceUpdateDepartmentDescription(ID, DEPARTMENT_DESCRIPTION);

        Department department = captureArgument.getValue();
        Assert.assertEquals(DEPARTMENT_DESCRIPTION, department.getDepartmentDescription());
    }
}
