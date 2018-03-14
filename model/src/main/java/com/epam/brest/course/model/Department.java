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

    /**
     * Empty constructor fo Department class.
     */
    public Department() {
    }

    /**
     * Constructor for two parameters.
     * @param departmentName - name of the department.
     * @param departmentDescription - description of the departments.
     */
    public Department(final String departmentName,
                      final String departmentDescription) {
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

    /**
     * Getter for departmentId.
     * @return - id of the department.
     */
    public final Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Setter for departmentId.
     * @param departmentId - id of the department.
     */
    public final void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
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
     * @param departmentName - name of the department.
     */
    public final void setDepartmentName(final String departmentName) {
        this.departmentName = departmentName;
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
     * @param departmentDescription - description of the department.
     */
    public final void setDepartmentDescription(
            final String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (departmentId != null ? !departmentId.equals(that.departmentId) :
                that.departmentId != null)
            return false;
        if (departmentName != null ?
                !departmentName.equals(that.departmentName) :
                that.departmentName != null)
            return false;
        return departmentDescription != null ?
                departmentDescription.equals(that.departmentDescription) :
                that.departmentDescription == null;
    }

    @Override
    public int hashCode() {
        int result = departmentId != null ? departmentId.hashCode() : 0;
        result = 31 * result +
                (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + (departmentDescription != null ?
                departmentDescription.hashCode() : 0);
        return result;
    }
}
