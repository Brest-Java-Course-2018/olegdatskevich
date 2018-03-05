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
    private Integer salary;
    /**
     * Id of employee's department.
     */
    private Integer departmentId;

    @Override
    public final String toString() {
        return "Employee ["
                + "employeeId=" + employeeId
                + ", employeeName='" + employeeName + '\''
                + ", salary=" + salary
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
     * Getter for salary of employee.
     * @return salary of employee.
     */
    public final Integer getSalary() {
        return salary;
    }

    /**
     * Setter for salary of employee.
     * @param salary - salary of employee.
     */
    public final void setSalary(final Integer salary) {
        this.salary = salary;
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
