package org.rsm.task.db;

import org.rsm.task.db.adapters.EmployeeAdapter;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MySqlDatabase {

    /**
     * Close result set if still open
     * @param resultSet sql data set
     */
    private void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            System.out.println("Cannot close result set: " + e.getMessage());
        }
    }

    /** Close connection if still valid and open
     * @param connection SQL connection
     */
    private void closeDbConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.out.println("Cannot close connection: " + e.getMessage());
        }
    }

    /**
     * Convert result set to map employee id -> manager id
     * @param resultSet sql data set
     * @return map employee id -> manager id
     */
    private Map<Integer, Integer> processEmployeeData(ResultSet resultSet) {
        Map<Integer, Integer> employees = new HashMap<>();
        try {
            if (resultSet == null || resultSet.isClosed()) {
                return employees;
            }
            EmployeeAdapter employeeAdapter = new EmployeeAdapter();
            employees = employeeAdapter.toEmployeeManagerMapping(resultSet);
        } catch (SQLException e) {
            System.out.println("Cannot collect employee data: " + e.getMessage());
        }
        return employees;
    }

    /**
     * Get employee data like a map employee id -> manager id
     * @param url db connection url
     * @param user db user name
     * @param password db user password
     * @return map employee id -> manager id
     */
    public Map<Integer, Integer> getEmployeeData(String url, String user, String password) {
        Map<Integer, Integer> employeeDataMap = new HashMap<>();
        ResultSet resultSet = null;
        String sqlStmt = "select * from employee;";
        Connection dbConnection = null;
        try {
            MySqlConnection mySqlConnection = new MySqlConnection(url, user, password);
            dbConnection = mySqlConnection.getConnection();

            if (dbConnection == null || dbConnection.isClosed()) {
                return employeeDataMap;
            }
            Statement sqlStatement = dbConnection.createStatement();
            resultSet = sqlStatement.executeQuery(sqlStmt);
            employeeDataMap = processEmployeeData(resultSet);
        } catch (SQLException e) {

        }
        closeResultSet(resultSet);
        closeDbConnection(dbConnection);
        return employeeDataMap;
    }

    /**
     * Update reporting information in database
     * @param mapEmployeeManager map employee id -> reporting employees
     * @param url db connection url
     * @param user db user name
     * @param password db user password
     * @return success flag
     */
    public boolean updateReportingData(Map<Integer, Set<Integer>> mapEmployeeManager, String url, String user, String password) {
        boolean result = true;
        // if mapping manager -> reporting employees empty -> exit
        if (mapEmployeeManager == null || mapEmployeeManager.isEmpty()) {
            return false;
        }
        String updateSql = "update employee set reporting_employees = ? where id = ?";
        MySqlConnection mySqlConnection = new MySqlConnection(url, user, password);
        Connection connection = mySqlConnection.getConnection();
        // loop through mapping and update reporting data in db
        for (Map.Entry<Integer, Set<Integer>> employeeManagerMapping : mapEmployeeManager.entrySet()) {
            if (employeeManagerMapping.getValue().size() > 0) {
                try {
                    PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                    updateStmt.setInt(1, employeeManagerMapping.getValue().size());
                    updateStmt.setInt(2, employeeManagerMapping.getKey());
                    updateStmt.executeUpdate();
                } catch (SQLException e) {
                    result = false;
                    System.out.println("Cannot update table: " + e.getMessage());
                }
            }
        }
        closeDbConnection(connection);
        return result;
    }

}
