package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;

public interface DepartmentService {

    Department getDepartmentById(final Integer departmentId);

    void updateDepartmentDescription(final Integer departmentId, final String departmentDescription);
}