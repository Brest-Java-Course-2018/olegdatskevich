package com.epam.brest.course.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    @NotEmpty(message = "Department name can not be empty.")
    @Size (min = 2, max = 50, message =
            "Department name must be between 2 and 50 characters.")
    private String departmentName;

    /**
     * Description of department.
     */
    @NotEmpty(message = "Department description can not be empty.")
    @Size (min = 2, max = 100, message =
            "Department description must be between 2 and 100 characters.")
    private String departmentDescription;

    /**
     * Empty constructor fo Department class.
     */
    public Department() {
    }

    /**
     * Constructor for two parameters.
     * @param depName - name of the department.
     * @param depDescr - description of the departments.
     */
    public Department(final String depName,
                      final String depDescr) {
        this.departmentName = depName;
        this.departmentDescription = depDescr;
    }

    @Override
    public final String toString() {
        return "Department ["
                + "departmentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + ", departmentDescription='" + departmentDescription + '\''
                + ']';
    }

    /**
     * Getter for departmentId.
     * @return - id of the department.
     */
    public final Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Setter for departmentId.
     * @param depId - id of the department.
     */
    public final void setDepartmentId(final Integer depId) {
        this.departmentId = depId;
    }

    /**
     * Getter for departmentName.
     * @return - name of the department.
     */
    public final String getDepartmentName() {
        return departmentName;
    }

    /**
     * Setter for departmentName.
     * @param depName - name of the department.
     */
    public final void setDepartmentName(final String depName) {
        this.departmentName = depName;
    }

    /**
     * Getter for departmentDescription.
     * @return - department's description.
     */
    public final String getDepartmentDescription() {
        return departmentDescription;
    }

    /**
     * Setter for departmentDescription.
     * @param depDescr - description of the department.
     */
    public final void setDepartmentDescription(
            final String depDescr) {
        this.departmentDescription = depDescr;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Department that = (Department) o;

        if (departmentId != null ? !departmentId.equals(that.departmentId)
                : that.departmentId != null)
            return false;
        if (departmentName != null
                ? !departmentName.equals(that.departmentName)
                : that.departmentName != null)
            return false;
        return departmentDescription != null
                ? departmentDescription.equals(that.departmentDescription)
                : that.departmentDescription == null;
    }

    @Override
    public final int hashCode() {
        int result = departmentId != null ? departmentId.hashCode() : 0;
        result = 31 * result
                + (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + (departmentDescription != null
                ? departmentDescription.hashCode() : 0);
        return result;
    }
}
