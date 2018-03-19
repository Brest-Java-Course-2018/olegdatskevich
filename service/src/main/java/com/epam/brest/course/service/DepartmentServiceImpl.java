package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentAvgSalary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implementation of DepartmentService class.
 */
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    /**
     * Logger for DepartmentServiceImpl .
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * For working with DAO layer Departments.
     */
    private DepartmentDao departmentDao;

    /**
     * Constructor for departmentDao.
     * @param departmentDao - instance of DepartmentDao class.
     */
    public DepartmentServiceImpl(final DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    /**
     * Getting all departments from DB.
     * @return - list of department.
     */
    @Override
    public final Collection<Department> serviceGetDepartments() {
        Collection<Department> departments = departmentDao.getDepartments();
        LOGGER.debug("serviceGetDepartments {} pc.", departments.size());
        return departments;
    }

    /**
     * Getting one department from DB.
     * @param departmentId - id of department.
     * @return department.
     */
    @Override
    public final Department serviceGetDepartmentById(final Integer departmentId) {
        Department department = departmentDao.getDepartmentById(departmentId);
        LOGGER.debug("serviceGetDepartmentById {}", departmentId);
        return department;
    }

    @Override
    public Collection<DepartmentAvgSalary> serviceDepartmentAvgSalary() {
        LOGGER.debug("serviceDepartmentAvgSalary()");
        Collection<DepartmentAvgSalary> departmentAvgSalaries = departmentDao.departmentAvgSalary();
        return departmentAvgSalaries;
    }

    /**
     * Adding department in DB.
     * @param department - department that need to add.
     * @return department which was added.
     */
    @Override
    public final Department serviceAddDepartment(final Department department) {
        Department serviceDepartment = departmentDao.addDepartment(department);
        LOGGER.debug("serviceAddDepartment {}", department);
        return serviceDepartment;
    }

    /**
     * Updating department.
     * @param department - department that need to update.
     */
    @Override
    public final void serviceUpdateDepartment(final Department department) {
        LOGGER.debug("serviceUpdateDepartment({})", department);
        departmentDao.updateDepartment(department);
    }

    /**
     * Remove department from DB.
     * @param departmentId - department that need to remove.
     */
    @Override
    public final void serviceRemoveDepartmentById(final Integer departmentId) {
        LOGGER.debug("serviceRemoveDepartmentById {}", departmentId);
        departmentDao.removeDepartmentById(departmentId);
    }
}
