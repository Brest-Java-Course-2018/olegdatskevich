package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;

import java.util.List;

/**
 * DAO for department Interface.
 */
public interface DepartmentDao {
    /**
     * Getting list of Departments from DB.
     * @return List of Departments.
     */
    List<Department> getDepartments();

    /**
     * Getting one department from DB.
     * @param departmentId - id of department in DB.
     * @return Department
     */
    Department getDepartmentById(final Integer departmentId);

    /**
     * Getting one department from DB.
     * @param departmentName - name of department in DB.
     * @return Department
     */
    Department getDepartmentByName(final String departmentName);

    /**
     * Adding the department to the DB.
     * @param department - prepared instance of the object Department to added.
     * @return department that was added to the DB.
     */
    Department addDepartment(final Department department);

    /**
     * Updating information about department.
     * @param department - prepared instance of the object Department to update.
     */
    void updateDepartment(final Department department);

    /**
     * Deleting the department from DB.
     * @param departmentId - id of the department that was deleted from DB.
     */
    void removeDepartmentById(final Integer departmentId);
}