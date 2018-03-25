package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.EmployeeService;
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
public class EmployeeServiceRestMockTest {
    private static Employee employee1;
    private static Employee employee2;

    @Autowired
    EmployeeService mockEmployeeService;

    @Autowired
    private RestTemplate mockRestTemplate;

    @Before
    public void init() {
        employee1 = new Employee("user1", "user1@mail.com", 1000, 1);
        employee1.setEmployeeId(1);
        employee2 = new Employee("user2", "user2@mail.com", 1000, 1);
        employee2.setEmployeeId(2);
    }

    @After
    public void tearDown() {
        EasyMock.verify(mockRestTemplate);
        EasyMock.reset(mockRestTemplate);
    }

    @Test
    public void serviceGetEmployeesClientRestTest() {
        List employees = Arrays.asList(employee1, employee2);
        ResponseEntity entity = new ResponseEntity<>(employees, HttpStatus.OK);
        EasyMock.expect(mockRestTemplate.getForEntity(EasyMock.anyString(), EasyMock.anyObject()))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Collection<Employee> results
                = mockEmployeeService.serviceGetEmployees();
        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
    }

    @Test
    public void getEmployeeByIdClientRestTest() {
        ResponseEntity entity = new ResponseEntity<>(employee1, HttpStatus.FOUND);
        EasyMock.expect(mockRestTemplate.getForEntity(EasyMock.anyString(), EasyMock.anyObject()))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Employee result = mockEmployeeService.serviceGetEmployeeById(1);
        Assert.assertNotNull(result);
        Assert.assertEquals("user1", result.getEmployeeName());
    }

    @Test
    public void serviceAddEmployeeClientRestTest() {
        ResponseEntity entity = new ResponseEntity<>(employee1, HttpStatus.OK);
        EasyMock.expect(mockRestTemplate.postForEntity(EasyMock.anyString(),
                EasyMock.anyObject(), EasyMock.anyObject()))
                .andReturn(entity);
        EasyMock.replay(mockRestTemplate);

        Employee result = mockEmployeeService.serviceAddEmployee(employee1);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.getEmployeeId().intValue());
    }

    @Test
    public void serviceUpdateEmployeeClientRestTest() {
        mockRestTemplate.put("http://localhost:8088/employees", employee1);
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);
        mockEmployeeService.serviceUpdateEmployee(employee1);
    }

    @Test
    public void serviceRemoveEmployeeClientRestTest() {
        mockRestTemplate.delete("http://localhost:8088/employees/1");
        EasyMock.expectLastCall();
        EasyMock.replay(mockRestTemplate);
        mockEmployeeService.serviceRemoveEmployee(1);
    }
}
