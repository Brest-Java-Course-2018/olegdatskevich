package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Implementation of DepartmentService class.
 */
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
    public final List<Department> serviceGetDepartments() {
        List<Department> departments = departmentDao.getDepartments();
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
     * @param departmentId - id of department.
     * @param departmentDescription - department's description.
     */
    @Override
    public final void serviceUpdateDepartmentDescription(
            final Integer departmentId, final String departmentDescription) {
        LOGGER.debug("serviceUpdateDepartmentDescription({}, {})",
                departmentId, departmentDescription);
        Department department = departmentDao.getDepartmentById(departmentId);
        department.setDepartmentDescription(departmentDescription);
        departmentDao.updateDepartment(department);
    }

    /**
     * Remove department from DB.
     * @param departmentId - department that need to remove.
     */
    @Override
    public final void serviceRemoveDepartmentById(final Integer departmentId) {
        departmentDao.removeDepartmentById(departmentId);
        LOGGER.debug("serviceRemoveDepartmentById {}", departmentId);
    }
}
