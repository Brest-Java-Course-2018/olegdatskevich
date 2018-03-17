package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentAvgSalary;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Interface fo Department service.
 */
@Service
public interface DepartmentService {

    /**
     * Getting all departments from DB.
     * @return - list of department.
     */
    Collection<Department> serviceGetDepartments();

    /**
     * Getting one department from DB.
     * @param departmentId - id of department.
     * @return department.
     */
    Department serviceGetDepartmentById(final Integer departmentId);

    Collection<DepartmentAvgSalary> serviceDepartmentAvgSalary();

    /**
     * Adding department in DB.
     * @param department - department that need to add.
     * @return department which was added.
     */
    Department serviceAddDepartment(final Department department);

    /**
     * Updating department.
     * @param department - department that need to update.
     */
    void serviceUpdateDepartment(final Department department);

    /**
     * Remove department from DB.
     * @param departmentId - department that need to remove.
     */
    void serviceRemoveDepartmentById(final Integer departmentId);
}
