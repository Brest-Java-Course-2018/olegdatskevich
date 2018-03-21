package com.epam.brest.course.model.dto;

/**
 *
 * DTO for Department average salary.
 */
public class DepartmentAvgSalary {
    /**
     * Id of department.
     */
    private Integer departmentId;
    /**
     * Name of department.
     */
    private String departmentName;
    /**
     * Average salary of department.
     */
    private Integer averageSalary;

    public DepartmentAvgSalary(final Integer depId,
                               final String depName,
                               final Integer depAvgSalary) {
        this.departmentId = depId;
        this.departmentName = depName;
        this.averageSalary = depAvgSalary;
    }

    public DepartmentAvgSalary() {
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(final Integer depId) {
        this.departmentId = depId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(final String depName) {
        this.departmentName = depName;
    }

    public Integer getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(final Integer depAvgSalary) {
        this.averageSalary = depAvgSalary;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DepartmentAvgSalary that = (DepartmentAvgSalary) o;

        if (departmentId != null ? !departmentId.equals(that.departmentId)
                : that.departmentId != null)
            return false;
        if (departmentName != null ? !departmentName.equals(that.departmentName)
                : that.departmentName != null)
            return false;
        return averageSalary != null ? averageSalary.equals(that.averageSalary)
                : that.averageSalary == null;
    }

    @Override
    public final int hashCode() {
        int result = departmentId != null ? departmentId.hashCode() : 0;
        result = 31 * result + (departmentName != null
                ? departmentName.hashCode() : 0);
        result = 31 * result + (averageSalary != null
                ? averageSalary.hashCode() : 0);
        return result;
    }

    @Override
    public final String toString() {
        return "DepartmentAvgSalary{"
                + "departmentId=" + departmentId
                + ", departmentName='" + departmentName + '\''
                + ", averageSalary=" + averageSalary
                + '}';
    }
}
