package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Implementation of DepartmentDao class.
 * Realization CRUD methods of DAO layer.
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String GET_DEPARTMENTS_SQL = "SELECT departmentId, "
            + "departmentName, description FROM department";
    private final String GET_DEPARTMENT_BY_ID = "SELECT departmentId, "
            + "departmentName, description FROM department WHERE "
            + "departmentId = :departmentId";
    private final String GET_DEPARTMENT_BY_NAME = "SELECT departmentId, "
            + "departmentName, description FROM department WHERE "
            + "departmentName = :departmentName";
    private final String ADD_DEPARTMENT = "INSERT INTO department "
            + "(departmentName, description) VALUES (?, ?)";
    private final String UPDATE_DEPARTMENT = "UPDATE department SET "
            + "description = ? WHERE departmentId = ?";
    private final String REMOVE_DEPARTMENT = "DELETE FROM department WHERE "
            + "departmentId = ?";

    /**
     *
     * @param dataSource
     */
    public DepartmentDaoImpl(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Getting list of Departments from DB.
     * @return List of Departments.
     */
    @Override
    public final List<Department> getDepartments() throws DataAccessException {
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
    public final Department getDepartmentById(final Integer departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentId", departmentId);
        Department department =
                namedParameterJdbcTemplate.queryForObject(GET_DEPARTMENT_BY_ID,
                        namedParameters, new DepartmentRowMapper());
        return department;
    }

    /**
     * Getting one department from DB.
     * @param departmentName - name of department in DB.
     * @return Department
     */
    @Override
    public final Department getDepartmentByName(final String departmentName) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentName", departmentName);
        Department department =
                namedParameterJdbcTemplate.queryForObject(GET_DEPARTMENT_BY_NAME,
                        namedParameters, new DepartmentRowMapper());
        return department;
    }

    /**
     * Adding the department to the DB.
     * @param department - prepared instance of the object Department to update.
     * @return department that was added to the DB.
     */
    @Override
    public final Department addDepartment(final Department department) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement(ADD_DEPARTMENT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, department.getDepartmentName());
                ps.setString(2, department.getDepartmentDescription());
                return ps;
            }
        }, key);
        return getDepartmentById(key.getKey().intValue());
    }

    /**
     * Updating information about department.
     * @param department - prepared instance of the object Department to update.
     */
    @Override
    public final void updateDepartment(final Department department) {
        jdbcTemplate.update(UPDATE_DEPARTMENT, department.getDepartmentDescription(),
                department.getDepartmentId());
    }

    /**
     * Deleting the department from DB.
     * @param departmentId - id of the department that was deleted from DB.
     */
    @Override
    public final void removeDepartmentById(final Integer departmentId) {
        jdbcTemplate.update(REMOVE_DEPARTMENT, departmentId);
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
            department.setDepartmentId(resultSet.getInt(1));
            department.setDepartmentName(resultSet.getString(2));
            department.setDepartmentDescription(resultSet.getString(3));
            return department;
        }
    }
}
