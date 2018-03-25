package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentAvgSalary;
import com.epam.brest.course.service.DepartmentService;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context-test.xml"})
public class DepartmentServiceRestMockTest {

    private static DepartmentAvgSalary departmentAvgSalary1;
    private static DepartmentAvgSalary departmentAvgSalary2;
    private static Department department;

    @Autowired
    private DepartmentService mockDepartmentService;

    @Autowired
    private RestTemplate mockRestTemplate;

    @Before
    public void init() {
        departmentAvgSalary1 = new DepartmentAvgSalary();
        departmentAvgSalary1.setDepartmentId(1);
        departmentAvgSalary1.setDepartmentName("name1");

        departmentAvgSalary2 = new DepartmentAvgSalary();
        departmentAvgSalary2.setDepartmentId(2);
        departmentAvgSalary2.setDepartmentName("name2");

        department = new Department("name", "descr");
        department.setDepartmentId(3);
    }

    @After
    public void tearDown() {
        EasyMock.verify(mockRestTemplate);
        EasyMock.reset(mockRestTemplate);
    }

    @Test
    public void getAllDepartments() {
        List departments = Arrays.asList(departmentAvgSalary1, departmentAvgSalary2);
        ResponseEntity entity = new ResponseEntity<>(departments, HttpStatus.OK);
        EasyMock.expect(mockRestTemplate.getForEntity(EasyMock.anyString(), EasyMock.anyObject()))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Collection<DepartmentAvgSalary> results
                = mockDepartmentService.serviceDepartmentAvgSalary();
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
    }

    @Test
    public void getDepartmentById() {
        ResponseEntity entity = new ResponseEntity<>(department, HttpStatus.FOUND);
        EasyMock.expect(mockRestTemplate.getForEntity(EasyMock.anyString(), EasyMock.anyObject()))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Department result = mockDepartmentService.serviceGetDepartmentById(3);
        Assert.assertNotNull(result);
        Assert.assertEquals("name", result.getDepartmentName());
    }

    @Test
    public void addDepartment() {
        ResponseEntity entity = new ResponseEntity<>(department, HttpStatus.OK);
        EasyMock.expect(mockRestTemplate.postForEntity(EasyMock.anyString(),
                EasyMock.anyObject(), EasyMock.anyObject()))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Department result = mockDepartmentService.serviceAddDepartment(department);
        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.getDepartmentId().intValue());
    }

    @Test
    public void updateDepartment() {
        mockRestTemplate.put("http://localhost:8088/departments", department);
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);
        mockDepartmentService.serviceUpdateDepartment(department);
    }

    @Test
    public void removeDepartment() {
        mockRestTemplate.delete("http://localhost:8088/departments/1");
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);
        mockDepartmentService.serviceRemoveDepartmentById(1);
    }
}
