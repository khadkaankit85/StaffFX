package com.example.stafffx.DAO;

import com.example.stafffx.Model.SalaryDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAO {

    // Create a new salary record
    public void createSalary(SalaryDetail salary) {
        String query = "INSERT INTO Salary (employee_id, admin_id, amount) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, salary.getEmployeeId());
            pstmt.setInt(2, salary.getAdminId());
            pstmt.setDouble(3, salary.getAmount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all salary records
    public List<SalaryDetail> readAllSalaries() {
        List<SalaryDetail> salaries = new ArrayList<>();
        String query = "SELECT * FROM Salary";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                SalaryDetail salary = new SalaryDetail();
                salary.setId(rs.getInt("id"));
                salary.setEmployeeId(rs.getInt("employee_id"));
                salary.setAdminId(rs.getInt("admin_id"));
                salary.setAmount(rs.getDouble("amount"));
                salaries.add(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salaries;
    }

    // Update an existing salary record
    public void updateSalary(SalaryDetail salary) {
        String query = "UPDATE Salary SET employee_id = ?, admin_id = ?, amount = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, salary.getEmployeeId());
            pstmt.setInt(2, salary.getAdminId());
            pstmt.setDouble(3, salary.getAmount());
            pstmt.setInt(4, salary.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a salary record by ID
    public void deleteSalary(int id) {
        String query = "DELETE FROM Salary WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find a salary record by ID
    public SalaryDetail findSalaryById(int id) {
        String query = "SELECT * FROM Salary WHERE id = ?";
        SalaryDetail salary = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    salary = new SalaryDetail();
                    salary.setId(rs.getInt("id"));
                    salary.setEmployeeId(rs.getInt("employee_id"));
                    salary.setAdminId(rs.getInt("admin_id"));
                    salary.setAmount(rs.getDouble("amount"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salary;
    }
}
