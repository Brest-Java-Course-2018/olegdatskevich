package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentAvgSalary;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

public class DepartmentConsumerRest implements DepartmentService {

    private static final Logger LOGGER = LogManager.getLogger();

    private String url;
    private RestTemplate restTemplate;

    public DepartmentConsumerRest(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<Department> serviceGetDepartments() {
        return null;
    }

    @Override
    public Department serviceGetDepartmentById(Integer departmentId) {
        LOGGER.debug("serviceGetDepartmentByIdClient({})", departmentId);
        ResponseEntity<Department> responseEntity = restTemplate
                .getForEntity(url + "/" + departmentId, Department.class);
        Department department = responseEntity.getBody();
        return department;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<DepartmentAvgSalary> serviceDepartmentAvgSalary() {
        LOGGER.debug("serviceDepartmentAvgSalaryClient({})");
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        List<DepartmentAvgSalary> departmentAvgSalaries
                = (List<DepartmentAvgSalary>)responseEntity.getBody();
        return departmentAvgSalaries;
    }

    @Override
    public Department serviceAddDepartment(Department department) {
        LOGGER.debug("serviceAddDepartmentClient({})", department);
        ResponseEntity responseEntity
                = restTemplate.postForEntity(url, department, Department.class);
        Department result = (Department)responseEntity.getBody();
        return result;
    }

    @Override
    public void serviceUpdateDepartment(Department department) {
        LOGGER.debug("serviceUpdateDepartment({})", department);
        restTemplate.put(url, department);
    }

    @Override
    public void serviceRemoveDepartmentById(Integer departmentId) {
        LOGGER.debug("serviceRemoveDepartmentById({})", departmentId);
        restTemplate.delete(url + "/" + departmentId);
    }
}
