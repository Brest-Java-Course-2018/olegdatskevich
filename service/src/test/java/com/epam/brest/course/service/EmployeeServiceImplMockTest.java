package com.epam.brest.course.service;

import com.epam.brest.course.dao.EmployeeDao;
import com.epam.brest.course.model.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class EmployeeServiceImplMockTest {

    public static final int EMPLOYEE_ID = 1;
    public static final Employee EMPLOYEE = new Employee("John Doe", 1500, 1);


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDao mockEmployeeDao;

    @Before
    public void setUp() {
        reset(mockEmployeeDao);
    }

    @After
    public void tearDown() {
        verify(mockEmployeeDao);
    }

    @Test
    public void serviceGetEmployeesTest() {
        List<Employee> employees = new ArrayList<>();
        expect(mockEmployeeDao.getEmployees()).
                andReturn(employees);
        replay(mockEmployeeDao);
        employeeService.serviceGetEmployees();
        verify(mockEmployeeDao);
    }

    @Test
    public void serviceGetEmployeeByIdTest() {
        expect(mockEmployeeDao.getEmployeeById(EMPLOYEE_ID)).
                andReturn(new Employee());
        replay(mockEmployeeDao);
        employeeService.serviceGetEmployeeById(EMPLOYEE_ID);
        verify(mockEmployeeDao);
    }

//    @Test
//    public void serviceGetEmployeesByDepartmentId() {
//    }
//
//    @Test
//    public void calculateAverageSalary() {
//    }
//
//    @Test
//    public void serviceGetEmployeeBySalaryMore() {
//    }
//
//    @Test
//    public void serviceAddEmployee() {
//    }
//
//    @Test
//    public void serviceUpdateEmployeeSalary() {
//    }
//
//    @Test
//    public void serviceRemoveEmployee() {
//    }
}
