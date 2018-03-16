package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collection;

/**
 * Implementation of EmployeeDao class.
 * Realization CRUD methods of DAO layer.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    /**
     * Logger for EmployeeDaoImpl.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(EmployeeDaoImpl.class);

    /**
     * For naming queries.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Group of constant vars.
     */
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String EMPLOYEE_NAME = "employeeName";
    private static final String EMPLOYEE_EMAIL = "employeeEmail";
    private static final String EMPLOYEE_SALARY = "employeeSalary";
    private static final String DEPARTMENT_ID = "departmentId";

    /**
     * SQL queries located in classpath/sql.properties.
     */
    @Value("${employee.selectAll}")
    private String employeeSelectAll;
    @Value("${employee.selectById}")
    private String employeeSelectById;
    @Value("${employee.selectByName}")
    private String employeeSelectByName;
    @Value("${employee.check}")
    private String checkEmployee;
    @Value("${employee.insert}")
    private String employeeInsert;
    @Value("${employee.update}")
    private String employeeUpdate;
    @Value("${employee.remove}")
    private String employeeRemove;

    /**
     * Constructor for namedParameterJdbcTemplate.
     * @param namedParameterJdbcTemplate - for named queries.
     */
    public EmployeeDaoImpl(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Setter for namedParameterJdbcTemplate.
     * @param namedParameterJdbcTemplate - for named queries.
     */
    public final void setNamedParameterJdbcTemplate(
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Getting list of Employees from DB.
     * @return List of Employees.
     */
    @Override
    public final Collection<Employee> getEmployees() {
        LOGGER.debug("getEmployees()");
        Collection<Employee> employees =
                namedParameterJdbcTemplate.getJdbcOperations().query(
                        employeeSelectAll,
                        BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    /**
     * Getting one employee from DB.
     * @param employeeId - id of employee in DB.
     * @return Employee.
     */
    @Override
    public final Employee getEmployeeById(final Integer employeeId) {
        LOGGER.debug("getEmployeeById({})", employeeId);
        SqlParameterSource namedParameters = new MapSqlParameterSource(
                EMPLOYEE_ID, employeeId);
        Employee employee = namedParameterJdbcTemplate.queryForObject(
                employeeSelectById,
                namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    /**
     * Getting one employee from DB.
     * @param employeeName - name of employee in DB.
     * @return employee.
     */
    @Override
    public final Employee getEmployeeByName(final String employeeName) {
        LOGGER.debug("getEmployeeByName({})", employeeName);
        SqlParameterSource namedParameters = new MapSqlParameterSource(
                EMPLOYEE_NAME, employeeName);
        Employee employee = namedParameterJdbcTemplate.queryForObject(
                employeeSelectByName,
                namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    /**
     * Adding the employee to the DB.
     * @param employee - prepared instance of the object Employee to added.
     * @return employee that was added to the DB.
     */
    @Override
    public final Employee addEmployee(final Employee employee) {
        LOGGER.debug("addEmployee({})", employee);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource(
                EMPLOYEE_NAME, employee.getEmployeeName());
        Integer result = namedParameterJdbcTemplate.queryForObject(
                checkEmployee, namedParameters, Integer.class);
        LOGGER.debug("result({})", result);
        if (result == 0) {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(EMPLOYEE_NAME, employee.getEmployeeName());
            namedParameters.addValue(EMPLOYEE_EMAIL, employee.getEmployeeEmail());
            namedParameters.addValue(EMPLOYEE_SALARY,
                    employee.getEmployeeSalary());
            namedParameters.addValue(DEPARTMENT_ID, employee.getDepartmentId());
            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(
                    employeeInsert, namedParameters, generatedKeyHolder);
            employee.setEmployeeId(generatedKeyHolder.getKey().intValue());
        } else {
            throw new IllegalArgumentException(
                    "Employee with the same name already exists in DB.");
        }
        return employee;
    }

    /**
     * Updating information about employee.
     * @param employee - prepared instance of the object Employee to update.
     */
    @Override
    public final void updateEmployee(final Employee employee) {
        LOGGER.debug("updateEmployee({})", employee);
        SqlParameterSource namedParameter =
                new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(employeeUpdate, namedParameter);
    }

    /**
     * Deleting the employee from DB.
     * @param employeeId - id of the employee that was deleted from DB.
     */
    @Override
    public final void removeEmployee(final Integer employeeId) {
        LOGGER.debug("removeEmployee({})", employeeId);
        namedParameterJdbcTemplate.getJdbcOperations().update(
                employeeRemove, employeeId);
    }
}
