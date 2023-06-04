import org.rsm.task.processors.EmployeeProcessor;

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