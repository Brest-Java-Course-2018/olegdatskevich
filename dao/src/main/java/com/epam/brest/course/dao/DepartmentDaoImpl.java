package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

/**
 * Implementation of DepartmentDao class.
 * Realization CRUD methods of DAO layer.
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private static final String DEPARTMENT_ID = "departmentId";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_DESCRIPTION = "departmentDescription";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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


    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Getting list of Departments from DB.
     * @return List of Departments.
     */
    @Override
    public final List<Department> getDepartments() throws DataAccessException {
        List<Department> departments =
                namedParameterJdbcTemplate.getJdbcOperations().query(departmentSelect, new DepartmentRowMapper());
        return departments;
    }

    /**
     * Getting one department from DB.
     * @param departmentId - id of department in DB.
     * @return Department
     */
    @Override
    /*public final Department getDepartmentById(final Integer departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department =
                namedParameterJdbcTemplate.queryForObject(departmentSelectById,
                        namedParameters, new DepartmentRowMapper());
        return department;
    }*/

    //Remove Raw Mapper
    public final Department getDepartmentById(final Integer departmentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department = namedParameterJdbcTemplate.queryForObject(departmentSelectById,
                namedParameters, BeanPropertyRowMapper.newInstance(Department.class));
        return department;
    }

    /**
     * Getting one department from DB.
     * @param departmentName - name of department in DB.
     * @return Department
     */
    @Override
    public final Department getDepartmentByName(final String departmentName) {
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
        MapSqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_NAME, department.getDepartmentName());
        Integer result = namedParameterJdbcTemplate.queryForObject(checkDepartment, namedParameters, Integer.class);

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
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(update, namedParameter);
    }

    /**
     * Deleting the department from DB.
     * @param departmentId - id of the department that was deleted from DB.
     */
    @Override
    public final void removeDepartmentById(final Integer departmentId) {
        namedParameterJdbcTemplate.getJdbcOperations().update(remove, departmentId);
    }

    private class DepartmentRowMapper implements RowMapper<Department> {
        /**
         * Method for writing data from DB in a object of Department class.
         * @param resultSet - set of data from DB in i-row.
         * @param i - raw from DB
         * @return - department from DB.
         * @throws SQLException
         */
        @Override
        public Department mapRow(final ResultSet resultSet, final int i)
                throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            department.setDepartmentDescription(resultSet.getString(DEPARTMENT_DESCRIPTION));
            return department;
        }
    }
}
