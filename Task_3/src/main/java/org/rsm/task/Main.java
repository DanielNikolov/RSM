package org.rsm.task;

import org.rsm.task.processors.EmployeeProcessor;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        EmployeeProcessor processor = new EmployeeProcessor();
        if (processor.processEmployees("jdbc:mysql://localhost:3306/rsm",
                "root", "root")) {
            System.out.println("All data updated successfully");
        } else {
            System.out.println("Errors occured during data processing");
        }
    }
}