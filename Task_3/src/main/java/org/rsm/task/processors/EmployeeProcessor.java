package org.rsm.task.processors;

import org.rsm.task.db.MySqlDatabase;

import java.util.*;

public class EmployeeProcessor {
    /**
     * Recursive DP function to find all employees who directly or indirectly
     * report to a given manager and store the result in `result`
     * @param managerId
     * @param managerEmployeeMapping
     * @param result
     * @return
     */
    // Recursive DP function to find all employees who directly or indirectly
    // report to a given manager and store the result in `result`
    private static Set<Integer> findAllReportingEmployees(Integer managerId,
        Map<Integer, Set<Integer>> managerEmployeeMapping,
        Map<Integer, Set<Integer>> result) {
        // if the subproblem is already seen before
        if (result.containsKey(managerId)) {
            // return the already computed mapping
            return result.get(managerId);
        }

        // find all employees reporting directly to the current manager
        Set<Integer> managerEmployees = managerEmployeeMapping.get(managerId);

        // find all employees reporting indirectly to the current manager
        for (Integer reportee : new ArrayList<>(managerEmployees))
        {
            // find all employees reporting to the current employee
            Set<Integer> employees = findAllReportingEmployees(reportee,
                    managerEmployeeMapping, result);

            // move those employees to the current manager
            if (employees != null) {
                managerEmployees.addAll(employees);
            }
        }

        // save the result to avoid recomputation and return it
        result.put(managerId, managerEmployees);
        return managerEmployees;
    }

    /**
     * Find all employees who directly or indirectly reports to a manager
     * @param employeeManagerMapping
     * @return
     */
    public static Map<Integer, Set<Integer>> findEmployees(Map<Integer, Integer> employeeManagerMapping)
    {
        // store manager to employee mappings in a new map.
        // `Set<Integer>` is used since a manager can have several employees mapped
        Map<Integer, Set<Integer>> managerToEmployeeMappings = new HashMap<Integer, Set<Integer>>();

        // fill the above map with the manager to employee mappings
        for (var entry: employeeManagerMapping.entrySet())
        {
            Integer employeeId = entry.getKey();
            Integer managerId = entry.getValue();

            managerToEmployeeMappings.putIfAbsent(managerId, new HashSet<>());
            managerToEmployeeMappings.putIfAbsent(employeeId, new HashSet<>());

            // don't map an employee with itself
            if (!employeeId.equals(managerId)) {
                managerToEmployeeMappings.get(managerId).add(employeeId);
            }
        }

        // construct an ordered map to store the result
        Map<Integer, Set<Integer>> result = new HashMap<Integer, Set<Integer>>();

        // find all reporting employees (direct and indirect) for every manager
        // and store the result in a map
        for (var entry: employeeManagerMapping.entrySet()) {
            findAllReportingEmployees(entry.getKey(), managerToEmployeeMappings,
                    result);
        }

        return result;
    }

    public boolean processEmployees(String url, String user, String password) {
        MySqlDatabase mySqlDatabase = new MySqlDatabase();
        // get mapping employee id -> manager id from database
        Map<Integer, Integer> mapEmployeeManager = mySqlDatabase.getEmployeeData(url, user, password);

        // if mapping not empty -> collect all reporting employees
        if (mapEmployeeManager != null && !mapEmployeeManager.isEmpty()) {
            Map<Integer, Set<Integer>> formattedMap = findEmployees(mapEmployeeManager);
            //update reporting information in db
            return mySqlDatabase.updateReportingData(formattedMap, url, user, password);
        }
        return true;
    }
}
