package com.epam.brest.course.model;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 *
 * POJO Employee in model.
 */
public class Employee {

    /**
     * Id number of employee.
     */
    private Integer employeeId;

    /**
     * Name of employee.
     */
    @NotEmpty(message = "Employee name can not be null.")
    @Size(min = 5, max = 40, message =
            "Employee name must be between 5 and 40 characters.")
    private String employeeName;

    /**
     * Email of employee.
     */
    @NotEmpty(message = "Employee email can not be null.")
    @Email (message = "Nat a valid email.")
    private String employeeEmail;
    /**
     * Salary of employee.
     */
    @NotNull(message = "Employee salary can not be null.")
    @PositiveOrZero(message = "Employee salary can not be negative.")
    private Integer employeeSalary;
    /**
     * Id of employee's department.
     */
    @Valid
    private Integer departmentId;

    /**
     * Default constructor.
     *
     */
    public Employee() {
    }

    /**
     * Constructor with arguments.
     * @param empName - name of employee.
     * @param empSalary - salary of employee.
     * @param depId - id of employee's department.
     */
    public Employee(final String empName, final String empEmail,
                    final Integer empSalary, final Integer depId) {
        this.employeeName = empName;
        this.employeeEmail = empEmail;
        this.employeeSalary = empSalary;
        this.departmentId = depId;
    }

    /**
     * Getter for employee id.
     * @return id of employee.
     */
    public final Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Setter for employeeId.
     * @param empId - id of employee.
     */
    public final void setEmployeeId(final Integer empId) {
        this.employeeId = empId;
    }

    /**
     * Getter for employee name.
     * @return name of employee.
     */
    public final String getEmployeeName() {
        return employeeName;
    }

    /**
     * Setter for employeeName.
     * @param empName - name of employee.
     */
    public final void setEmployeeName(final String empName) {
        this.employeeName = empName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String empEmail) {
        this.employeeEmail = empEmail;
    }

    /**
     * Getter for employeeSalary of employee.
     * @return employeeSalary of employee.
     */
    public final Integer getEmployeeSalary() {
        return employeeSalary;
    }

    /**
     * Setter for employeeSalary of employee.
     * @param empSalary - employeeSalary of employee.
     */
    public final void setEmployeeSalary(final Integer empSalary) {
        this.employeeSalary = empSalary;
    }

    /**
     * Getter for employee's department.
     * @return employee's department.
     */
    public final Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Setter for employee's department.
     * @param depId - employee's department.
     */
    public final void setDepartmentId(final Integer depId) {
        this.departmentId = depId;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Employee employee = (Employee) o;

        if (employeeId != null ? !employeeId
                .equals(employee.employeeId) : employee.employeeId != null)
            return false;
        if (employeeName != null ? !employeeName
                .equals(employee.employeeName) : employee.employeeName != null)
            return false;
        if (employeeEmail != null ? !employeeEmail
                .equals(employee.employeeEmail)
                : employee.employeeEmail != null)
            return false;
        if (employeeSalary != null ? !employeeSalary
                .equals(employee.employeeSalary)
                : employee.employeeSalary != null)
            return false;
        return departmentId != null ? departmentId
                .equals(employee.departmentId) : employee.departmentId == null;
    }

    @Override
    public final int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (employeeName != null
                ? employeeName.hashCode() : 0);
        result = 31 * result + (employeeEmail != null
                ? employeeEmail.hashCode() : 0);
        result = 31 * result + (employeeSalary != null
                ? employeeSalary.hashCode() : 0);
        result = 31 * result + (departmentId != null
                ? departmentId.hashCode() : 0);
        return result;
    }

    @Override
    public final String toString() {
        return "Employee{"
                + "employeeId=" + employeeId
                + ", employeeName='" + employeeName + '\''
                + ", employeeEmail='" + employeeEmail + '\''
                + ", employeeSalary=" + employeeSalary
                + ", departmentId=" + departmentId
                + '}';
    }
}
