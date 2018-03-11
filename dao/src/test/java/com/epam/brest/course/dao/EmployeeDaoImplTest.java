package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
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
public class EmployeeDaoImplTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        Assert.assertFalse(employees.isEmpty());
    }

    @Test
    public void getEmployeeById() {
        Employee employee = employeeDao.getEmployeeById(1);
        Assert.assertNotNull(employee);
        Assert.assertTrue(employee.getEmployeeId().equals(1));
        Assert.assertTrue(employee.getEmployeeName().equals("Ivan Ivanov"));
        Assert.assertTrue(employee.getEmployeeSalary().equals(1000));
        Assert.assertTrue(employee.getDepartmentId().equals(1));
    }

    @Test
    public void getEmployeeByName() {
        Employee employee = employeeDao.getEmployeeByName("Petr Petrov");
        Assert.assertNotNull(employee);
        Assert.assertTrue(employee.getEmployeeId().equals(2));
        Assert.assertTrue(employee.getEmployeeName().equals("Petr Petrov"));
        Assert.assertTrue(employee.getEmployeeSalary().equals(1500));
        Assert.assertTrue(employee.getDepartmentId().equals(2));
    }

    @Test
    public void addEmployee() {
        List<Employee> employees = employeeDao.getEmployees();
        int sizeBefore = employees.size();
        Employee testEmployee = new Employee("Test Testov", 500, 1);
        Employee newEmployee = employeeDao.addEmployee(testEmployee);
        Assert.assertNotNull(newEmployee.getEmployeeId());
        Assert.assertTrue(newEmployee.getEmployeeName().equals(testEmployee.getEmployeeName()));
        Assert.assertTrue(newEmployee.getEmployeeSalary().equals(testEmployee.getEmployeeSalary()));
        Assert.assertTrue(newEmployee.getDepartmentId().equals(testEmployee.getDepartmentId()));
        Assert.assertTrue(sizeBefore + 1 == employeeDao.getEmployees().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public  void  addSameEmployee() {
        Employee testEmployee = new Employee("TestAddSameEmployee", 100, 1);
        employeeDao.addEmployee(testEmployee);
        employeeDao.addEmployee(testEmployee);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void addSameEmployeeWithRule() {
        Employee testEmployee = new Employee("TestAddSameEmployeeRule", 100, 1);
        employeeDao.addEmployee(testEmployee);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Employee with the same name already exists in DB.");
        employeeDao.addEmployee(testEmployee);
    }

    @Test
    public void updateEmployee() {
        Employee testEmployee = new Employee("TestUpdateEmployee", 100, 1);
        Employee newEmployee = employeeDao.addEmployee(testEmployee);
        newEmployee.setEmployeeName("NEWTestUpdateEmployee");
        newEmployee.setEmployeeSalary(200);
        newEmployee.setDepartmentId(2);
        employeeDao.updateEmployee(newEmployee);
        Employee updatedEmployee = employeeDao.getEmployeeById(newEmployee.getEmployeeId());
        Assert.assertTrue(newEmployee.getEmployeeId().equals(updatedEmployee.getEmployeeId()));
        Assert.assertTrue(newEmployee.getEmployeeName().equals(updatedEmployee.getEmployeeName()));
        Assert.assertTrue(newEmployee.getEmployeeSalary().equals(updatedEmployee.getEmployeeSalary()));
        Assert.assertTrue(newEmployee.getDepartmentId().equals(updatedEmployee.getDepartmentId()));
    }

    @Test
    public void removeEmployee() {
        Employee testEmployee = new Employee("TestRemoveEmployee", 300, 2);
        testEmployee = employeeDao.addEmployee(testEmployee);
        List<Employee> employees = employeeDao.getEmployees();
        int sizeBefore = employees.size();
        employeeDao.removeEmployee(testEmployee.getEmployeeId());
        Assert.assertTrue(sizeBefore - 1 == employeeDao.getEmployees().size());
    }
}
