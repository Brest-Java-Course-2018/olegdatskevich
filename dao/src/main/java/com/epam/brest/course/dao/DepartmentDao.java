package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;

import java.util.List;

/**
 * DAO for department Interface.
 */
public interface DepartmentDao {

    List<Department> getDepartments();

    Department getDepartmentById(Integer departmentId);

    Department addDepartment(Department department);

    void updateDepartment(Department department);

    void removeDepartmentById(Integer id);
}
