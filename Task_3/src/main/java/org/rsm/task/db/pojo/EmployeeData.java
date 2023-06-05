package org.rsm.task.db.pojo;

public class EmployeeData {
    private Integer employeeId;
    private Integer managerId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public EmployeeData(Integer id, Integer managerId) {
        this.employeeId = id;
        this.managerId = managerId;
    }

    public String toString() {
        return "[employeeId: " + this.employeeId + ", managerId: " + this.managerId + "]";
    }
}
