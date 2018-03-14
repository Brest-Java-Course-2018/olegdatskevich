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

    /**
     * Default constructor.
     *
     */
    public Employee() {
    }

    /**
     * Constructor with arguments.
     * @param employeeName - name of employee.
     * @param employeeSalary - salary of employee.
     * @param departmentId - id of employee's department.
     */
    public Employee(final String employeeName,
                    final Integer employeeSalary,
                    final Integer departmentId) {
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
    public final void setEmployeeId(final Integer employeeId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employeeId != null ? !employeeId.equals(employee.employeeId) :
                employee.employeeId != null)
            return false;
        if (employeeName != null ? !employeeName.equals(employee.employeeName) :
                employee.employeeName != null)
            return false;
        if (employeeSalary != null ?
                !employeeSalary.equals(employee.employeeSalary) :
                employee.employeeSalary != null)
            return false;
        return departmentId != null ?
                departmentId.equals(employee.departmentId) :
                employee.departmentId == null;
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (employeeName != null ? employeeName.hashCode() : 0);
        result = 31 * result + (employeeSalary != null ? employeeSalary.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        return result;
    }
}
