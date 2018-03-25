package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

public class EmployeeConsumerRest implements EmployeeService {

    private static final Logger LOGGER = LogManager.getLogger();

    private String url;
    private RestTemplate restTemplate;

    public EmployeeConsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<Employee> serviceGetEmployees() {
        LOGGER.debug("serviceGetEmployeesClientRest()");
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<Employee> employees = (List<Employee>)responseEntity.getBody();
        return employees;
    }

    @Override
    public Employee serviceGetEmployeeById(Integer employeeId) {
        LOGGER.debug("serviceGetEmployeeByIdClientRest({})", employeeId);
        ResponseEntity<Employee> responseEntity = restTemplate
                .getForEntity(url + "/" + employeeId, Employee.class);
        Employee employee = responseEntity.getBody();
        return employee;
    }

    @Override
    public Employee serviceAddEmployee(Employee employee) {
        LOGGER.debug("serviceAddEmployeeClientRest({})", employee);
        ResponseEntity responseEntity
                = restTemplate.postForEntity(url, employee, Employee.class);
        Employee result = (Employee)responseEntity.getBody();
        return result;
    }

    @Override
    public void serviceUpdateEmployee(Employee employee) {
        LOGGER.debug("serviceUpdateEmployeeClientRest({})", employee);
        restTemplate.put(url, employee);
    }

    @Override
    public void serviceRemoveEmployee(Integer employeeId) {
        LOGGER.debug("serviceRemoveEmployeeClientRest({})", employeeId);
        restTemplate.delete(url + "/" + employeeId);
    }
}
