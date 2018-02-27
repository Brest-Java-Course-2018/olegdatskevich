package com.epam.brest.cource.dao;

import com.epam.brest.cource.model.Department;

import java.util.List;

/**
 * DAO for department Inteface
 */
public interface DepartmentDao {
    List<Department> getDepartments();
    Department getDepartmentById(Integer departmentId);
}
