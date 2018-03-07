package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String EMPLOYEE_NAME = "employeeName";
    private static final String EMPLOYEE_SALARY = "employeeSalary";
    private static final String DEPARTMENT_ID = "departmentId";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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


    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = namedParameterJdbcTemplate.getJdbcOperations().query(employeeSelectAll, new EmployeeRowMapper());
        return employees;
    }

    @Override
    public Employee getEmployeeById(final Integer employeeId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(EMPLOYEE_ID, employeeId);
        Employee employee = namedParameterJdbcTemplate.queryForObject(employeeSelectById,
                namedParameters, BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    @Override
    public Employee getEmployeeByName(String employeeName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(EMPLOYEE_NAME, employeeName);
        Employee employee = namedParameterJdbcTemplate.queryForObject(employeeSelectByName,
                namedParameters, BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource(EMPLOYEE_NAME, employee.getEmployeeName());
        Integer result = namedParameterJdbcTemplate.queryForObject(checkEmployee, namedParameters, Integer.class);

        if(result == 0) {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue(EMPLOYEE_NAME, employee.getEmployeeName());
            namedParameters.addValue(EMPLOYEE_SALARY, employee.getEmployeeSalary());
            namedParameters.addValue(DEPARTMENT_ID, employee.getDepartmentId());
            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(employeeInsert, namedParameters, generatedKeyHolder);
            employee.setEmployeeId(generatedKeyHolder.getKey().intValue());
        } else {
            throw new IllegalArgumentException("Employee with the same name already exists in DB.");
        }
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(employeeUpdate, namedParameter);
    }

    @Override
    public void removeEmployee(Integer employeeId) {
        namedParameterJdbcTemplate.getJdbcOperations().update(employeeRemove, employeeId);

    }

    private class EmployeeRowMapper implements RowMapper<Employee> {
        /**
         * Method for writing data from DB in a object of Department class.
         * @param resultSet - set of data from DB in i-row.
         * @param i - raw from DB
         * @return - department from DB.
         * @throws SQLException
         */
        @Override
        public Employee mapRow(final ResultSet resultSet, final int i)
                throws SQLException {
            Employee employee = new Employee();
            employee.setEmployeeId(resultSet.getInt(EMPLOYEE_ID));
            employee.setEmployeeName(resultSet.getString(EMPLOYEE_NAME));
            employee.setEmployeeSalary(resultSet.getInt(EMPLOYEE_SALARY));
            employee.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            return employee;
        }
    }
}
