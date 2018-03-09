package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DepartmentServiceImpl implements DepartmentService{

    private static final Logger LOGGER = LogManager.getLogger();

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartmentDescription(Integer departmentId, String departmentDescription) {
        LOGGER.debug("updateDepartmentDescription({}, {})", departmentId, departmentDescription);
        Department department= departmentDao.getDepartmentById(departmentId);
        department.setDepartmentDescription(departmentDescription);
        departmentDao.updateDepartment(department);
    }
}