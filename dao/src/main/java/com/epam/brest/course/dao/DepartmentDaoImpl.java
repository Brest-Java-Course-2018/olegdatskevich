package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

/**
 * Implementation of DepartmentDao class.
 * Realization CRUD methods of DAO layer.
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = LogManager.getLogger(DepartmentDaoImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String DEPARTMENT_ID = "departmentId";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_DESCRIPTION = "departmentDescription";

    @Value("${department.select}")
    private String departmentSelect;
    @Value("${department.selectById}")
    private String departmentSelectById;
    @Value("${department.selectByName}")
    private String departmentSelectByName;
    @Value("${department.checkDepartment}")
    private String checkDepartment;
    @Value("${department.insert}")
    private String insert;
    @Value("${department.update}")
    private String update;
    @Value("${department.remove}")
    private String remove;

    public DepartmentDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Getting list of Departments from DB.
     * @return List of Departments.
     */
    @Override
    public final List<Department> getDepartments() throws DataAccessException {
        LOGGER.debug("getDepartments()");
        List<Department> departments = namedParameterJdbcTemplate.getJdbcOperations().
                query(departmentSelect, BeanPropertyRowMapper.newInstance(Department.class));
        return departments;
    }

    /**
     * Getting one department from DB.
     * @param departmentId - id of department in DB.
     * @return Department.
     */
    @Override
    public final Department getDepartmentById(final Integer departmentId) {
        LOGGER.debug("getDepartmentsById({})", departmentId);
        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department = namedParameterJdbcTemplate.queryForObject(departmentSelectById,
                namedParameters, BeanPropertyRowMapper.newInstance(Department.class));
        return department;
    }

    /**
     * Getting one department from DB.
     * @param departmentName - name of department in DB.
     * @return Department.
     */
    @Override
    public final Department getDepartmentByName(final String departmentName) {
        LOGGER.debug("getDepartmentsByName({})", departmentName);
        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_NAME, departmentName);
        Department department = namedParameterJdbcTemplate.queryForObject(departmentSelectByName,
                        namedParameters, BeanPropertyRowMapper.newInstance(Department.class));
        return department;
    }

    /**
     * Adding the department to the DB.
     * @param department - prepared instance of the object Department to update.
     * @return department that was added to the DB.
     */
    @Override
    public final Department addDepartment(final Department department) {
        LOGGER.debug("addDepartments({})", department);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_NAME, department.getDepartmentName());
        Integer result = namedParameterJdbcTemplate.queryForObject(checkDepartment, namedParameters, Integer.class);
        LOGGER.debug("result({})", result);
        if(result == 0) {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(DEPARTMENT_NAME, department.getDepartmentName());
            namedParameters.addValue(DEPARTMENT_DESCRIPTION, department.getDepartmentDescription());
            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(insert, namedParameters, generatedKeyHolder);
            department.setDepartmentId(generatedKeyHolder.getKey().intValue());
        } else {
            throw new IllegalArgumentException("Department with the same name already exists in DB.");
        }
        return department;
    }

    /**
     * Updating information about department.
     * @param department - prepared instance of the object Department to update.
     */
    @Override
    public final void updateDepartment(final Department department) {
        LOGGER.debug("updateDepartment({})", department);
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(update, namedParameter);
    }

    /**
     * Deleting the department from DB.
     * @param departmentId - id of the department that was deleted from DB.
     */
    @Override
    public final void removeDepartmentById(final Integer departmentId) {
        LOGGER.debug("removeDepartmentById({})", departmentId);
        namedParameterJdbcTemplate.getJdbcOperations().update(remove, departmentId);
    }
}