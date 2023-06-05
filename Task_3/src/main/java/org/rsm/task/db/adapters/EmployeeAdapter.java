package org.rsm.task.db.adapters;

import org.rsm.task.db.pojo.EmployeeData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeAdapter {

    /**
     * Convert db record into Employee object
     * @param resultSet
     * @return
     */
    public EmployeeData toEmployee(ResultSet resultSet) {
        EmployeeData result = null;
        if (resultSet == null) {
            return result;
        }
        try {
            result = new EmployeeData(
                    resultSet.getInt("id"),
                    (Integer) resultSet.getObject("manager_id"));
        } catch (SQLException e) {
            System.out.println("Cannot convert resultset to EmployeeData:" + e.getMessage());
        }
        return result;
    }

    /**
     * Convert result set data to list of Employee data
     * @param resultSet
     * @return
     */
    public List<EmployeeData> toEmployeeDataList(ResultSet resultSet) {
        List<EmployeeData> result = new ArrayList<EmployeeData>();
        try {
            if (resultSet == null || resultSet.isClosed()) {
                return result;
            }
            EmployeeData employeeData;
            while (resultSet.next()) {
                employeeData = toEmployee(resultSet);
                if (employeeData != null) {
                    result.add(employeeData);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return result;
    }

    /**
     * Convert result set to mapping employee id -> manager id
     * @param resultSet
     * @return
     */
    public Map<Integer, Integer> toEmployeeManagerMapping(ResultSet resultSet) {
        Map<Integer, Integer> employeeManagerMapping = new HashMap<Integer, Integer>();
        try {
            if (resultSet == null || resultSet.isClosed()) {
                return employeeManagerMapping;
            }
            EmployeeData employeeData;
            while (resultSet.next()) {
                employeeData = toEmployee(resultSet);
                if (employeeData != null) {
                    // if manager id is empty -> map to the employee id
                    employeeManagerMapping.put(employeeData.getEmployeeId(),
                            employeeData.getManagerId() != null ? employeeData.getManagerId() : employeeData.getEmployeeId());
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return employeeManagerMapping;
    }
}
