import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();
        int choice;

        do {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // CREATE
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter salary: ");
                    double salary = scanner.nextDouble();
                    dao.addEmployee(name, department, salary);
                    break;

                case 2:
                    // READ
                    dao.viewAllEmployees();
                    break;

                case 3:
                    // UPDATE
                    System.out.print("Enter ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new department: ");
                    String newDept = scanner.nextLine();
                    System.out.print("Enter new salary: ");
                    double newSalary = scanner.nextDouble();
                    dao.updateEmployee(updateId, newName, newDept, newSalary);
                    break;

                case 4:
                    // DELETE
                    System.out.print("Enter ID to delete: ");
                    int deleteId = scanner.nextInt();
                    dao.deleteEmployeeById(deleteId);
                    break;

                case 5:
                    System.out.println(" Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println(" Invalid choice. Please try again.");
            }

        } while (choice != 5);

        dao.close();
        scanner.close();
    }
}
