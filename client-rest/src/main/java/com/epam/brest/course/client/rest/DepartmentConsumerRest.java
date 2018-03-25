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
        LOGGER.debug("serviceGetDepartmentsClientRest()");
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<Department> departments
                = (List<Department>)responseEntity.getBody();
        return departments;
    }

    @Override
    public Department serviceGetDepartmentById(Integer departmentId) {
        LOGGER.debug("serviceGetDepartmentByIdClientRest({})", departmentId);
        ResponseEntity<Department> responseEntity = restTemplate
                .getForEntity(url + "/" + departmentId, Department.class);
        Department department = responseEntity.getBody();
        return department;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<DepartmentAvgSalary> serviceDepartmentAvgSalary() {
        LOGGER.debug("serviceDepartmentAvgSalaryClientRest()");
        ResponseEntity<List> responseEntity
                = restTemplate.getForEntity(url, List.class);
        List<DepartmentAvgSalary> departmentAvgSalaries
                = (List<DepartmentAvgSalary>)responseEntity.getBody();
        return departmentAvgSalaries;
    }

    @Override
    public Department serviceAddDepartment(Department department) {
        LOGGER.debug("serviceAddDepartmentClientRest({})", department);
        ResponseEntity responseEntity
                = restTemplate.postForEntity(url, department, Department.class);
        Department result = (Department)responseEntity.getBody();
        return result;
    }

    @Override
    public void serviceUpdateDepartment(Department department) {
        LOGGER.debug("serviceUpdateDepartmentClientRest({})", department);
        restTemplate.put(url, department);
    }

    @Override
    public void serviceRemoveDepartmentById(Integer departmentId) {
        LOGGER.debug("serviceRemoveDepartmentByIdClientRest({})", departmentId);
        restTemplate.delete(url + "/" + departmentId);
    }
}
