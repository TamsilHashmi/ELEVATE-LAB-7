import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;

public class EmployeeDAO {
    private Connection conn;

    // Constructor - connects using db.properties
    public EmployeeDAO() {
        try {
            Properties props = new Properties();
            FileInputStream in = new FileInputStream("db.properties");
            props.load(in);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to insert a new employee
    public void addEmployee(String name, String department, double salary) {
        String sql = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setDouble(3, salary);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println(" Employee added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllEmployees() {
    String sql = "SELECT * FROM employee";

    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        System.out.println("\n All Employees:");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-5s %-15s %-15s %-10s\n", "ID", "Name", "Department", "Salary");
        System.out.println("-------------------------------------------------");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String dept = rs.getString("department");
            double salary = rs.getDouble("salary");

            System.out.printf("%-5d %-15s %-15s %-10.2f\n", id, name, dept, salary);
        }
        System.out.println("-------------------------------------------------\n");
                
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void updateEmployee(int id, String name, String department, double salary) {
    String sql = "UPDATE employee SET name = ?, department = ?, salary = ? WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, name);
        stmt.setString(2, department);
        stmt.setDouble(3, salary);
        stmt.setInt(4, id);

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println(" Employee with ID " + id + " updated successfully.");
        } else {
            System.out.println(" No employee found with ID " + id);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteEmployeeById(int id) {
    String sql = "DELETE FROM employee WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println(" Employee with ID " + id + " deleted successfully.");
        } else {
            System.out.println(" No employee found with ID " + id);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}




    // Close the connection
    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
