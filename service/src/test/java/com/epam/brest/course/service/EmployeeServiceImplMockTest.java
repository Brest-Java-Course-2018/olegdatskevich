package com.epam.brest.course.service;

import com.epam.brest.course.dao.EmployeeDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class EmployeeServiceImplMockTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDao mockEmployeeDao;

    @Test
    public void serviceUpdateEmployee() {

    }

}
