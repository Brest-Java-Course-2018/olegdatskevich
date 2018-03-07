package com.epam.brest.course.model;

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
    private String employeeName;
    /**
     * Salary of employee.
     */
    private Integer employeeSalary;
    /**
     * Id of employee's department.
     */
    private Integer departmentId;

    public Employee() {
    }

    public Employee(String employeeName, Integer employeeSalary, Integer departmentId) {
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.departmentId = departmentId;
    }

    @Override
    public final String toString() {
        return "Employee ["
                + "employeeId=" + employeeId
                + ", employeeName='" + employeeName + '\''
                + ", employeeSalary=" + employeeSalary
                + ", departmentId=" + departmentId
                + ']';
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
     * @param employeeId - id of employee.
     */
    public final void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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
     * @param employeeName - name of employee.
     */
    public final void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
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
     * @param employeeSalary - employeeSalary of employee.
     */
    public final void setEmployeeSalary(final Integer employeeSalary) {
        this.employeeSalary = employeeSalary;
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
     * @param departmentId - employee's department.
     */
    public final void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }
}
