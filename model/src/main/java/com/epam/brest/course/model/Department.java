package com.epam.brest.course.model;

/**
 *
 * POJO Department for model.
 */

public class Department {
    /**
     * Id of department.
     */
    private Integer departmentId;
    /**
     * Name of department.
     */
    private String departmentName;
    /**
     * Description of department.
     */
    private String departmentDescription;

    public Department() {
    }

    /**
     * Constructor for two parameters.
     * @param departmentName - name of the department.
     * @param departmentDescription - description of the departments.
     */
    public Department(final String departmentName, final String departmentDescription) {
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
    }

    @Override
    public final String toString() {
        return "Department ["
                + "departmentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + ", departmentDescription='" + departmentDescription + '\''
                + ']';
    }

    public final Integer getDepartmentId() {
        return departmentId;
    }

    public final void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    public final String getDepartmentName() {
        return departmentName;
    }

    public final void setDepartmentName(final String departmentName) {
        this.departmentName = departmentName;
    }

    public final String getDepartmentDescription() {
        return departmentDescription;
    }

    public final void setDepartmentDescription(final String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }
}
