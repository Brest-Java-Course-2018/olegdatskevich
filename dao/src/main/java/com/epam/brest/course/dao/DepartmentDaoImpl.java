package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of DepartmentDao class.
 * Realization CRUD methods of DAO layer.
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String GET_DEPARTMENTS_SQL = "SELECT departmentId, " +
            "departmentName, description FROM department";
    private final String GET_DEPARTMENT_BY_ID = "SELECT departmentId, " +
            "departmentName, description FROM department WHERE " +
            "departmentId = :departmentId";

    /**
     * Constructor
     * @param dataSource
     */
    public DepartmentDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Getting list of Departments from DB.
     * @return List of Departments.
     */
    @Override
    public List<Department> getDepartments() {
        List<Department> departments = jdbcTemplate.query(GET_DEPARTMENTS_SQL,
                new DepartmentRowMapper());
        return departments;
    }

    /**
     * Getting one department from DB.
     * @param departmentId - id of department in DB.
     * @return Department
     */
    @Override
    public Department getDepartmentById(Integer departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId", departmentId);
        Department department =
                namedParameterJdbcTemplate.queryForObject(GET_DEPARTMENT_BY_ID,
                        namedParameters, new DepartmentRowMapper());
        return department;
    }

    /**
     * Adding the department to the DB.
     * @param department
     * @return department that was added to the DB.
     */
    @Override
    public Department addDepartment(Department department) {
        return null;
    }

    /**
     * Updating information about department.
     * @param department
     */
    @Override
    public void updateDepartment(Department department) {

    }

    /**
     * Deleting the department from DB.
     * @param id - department that was deleted from DB.
     */
    @Override
    public void removeDepartmentById(Integer id) {

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
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(1));
            department.setDepartmentName(resultSet.getString(2));
            department.setDepartmentDescription(resultSet.getString(3));
            return department;
        }
    }

}