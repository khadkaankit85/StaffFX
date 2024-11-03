package com.example.stafffx.DAO;

import com.example.stafffx.Model.Employee; // Assuming this has been modified to include new fields
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // Create a new employee record
    public void createEmployee(Employee employee) {
        String query = "INSERT INTO Employee (name, email, password, position, employee_id, amount) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, "default_pass");
            pstmt.setString(4, "unassigned"); // Changed to position
            pstmt.setInt(5, employee.getEmployeeId());
            pstmt.setDouble(6, employee.getAmount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all employee records
    public List<Employee> readAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setPassword(rs.getString("password"));
                employee.setPosition(rs.getString("position")); // Changed to position
                employee.setCreatedAt(rs.getTimestamp("created_at"));
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setAmount(rs.getDouble("amount"));
                employee.setPaymentDate(rs.getTimestamp("payment_date")); // Assuming you have this method
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Update an existing employee record
    public void updateEmployee(Employee employee) {
        String query = "UPDATE Employee SET name = ?, email = ?, password = ?, position = ?, employee_id = ?, amount = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getPassword());
            pstmt.setString(4, employee.getPosition()); // Changed to position
            pstmt.setInt(5, employee.getEmployeeId());
            pstmt.setDouble(6, employee.getAmount());
            pstmt.setInt(7, employee.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an employee record by ID
    public void deleteEmployee(int id) {
        String query = "DELETE FROM Employee WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find an employee by ID
    public Employee findEmployeeById(int id) {
        String query = "SELECT * FROM Employee WHERE id = ?";
        Employee employee = null;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee();
                    employee.setId(rs.getInt("id"));
                    employee.setName(rs.getString("name"));
                    employee.setEmail(rs.getString("email"));
                    employee.setPassword(rs.getString("password"));
                    employee.setPosition(rs.getString("position")); // Changed to position
                    employee.setCreatedAt(rs.getTimestamp("created_at"));
                    employee.setEmployeeId(rs.getInt("employee_id"));
                    employee.setAmount(rs.getDouble("amount"));
                    employee.setPaymentDate(rs.getTimestamp("payment_date")); // Assuming you have this method
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
