package com.example.stafffx.DAO;

import com.example.stafffx.Model.Admin; // Assuming this has been modified to include new fields
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    // Create a new admin record
    public void createAdmin(Admin admin) {
        String query = "INSERT INTO Admin (name, email, password, position, employee_id, amount) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, "default_pass");
            pstmt.setString(4, "unassigned"); // Changed to position
            pstmt.setInt(5, admin.getId());
            pstmt.setDouble(6, admin.getAmount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all admin records
    public List<Admin> readAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM Admin";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setPosition(rs.getString("position")); // Changed to position
                admin.setCreatedAt(rs.getTimestamp("created_at"));
                admin.setAmount(rs.getDouble("amount"));
                admin.setPaymentDate(rs.getTimestamp("payment_date")); // Assuming you have this method
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    // Update an existing admin record
    public void updateAdmin(Admin admin) {
        String query = "UPDATE Admin SET name = ?, email = ?, password = ?, position = ?, employee_id = ?, amount = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, admin.getPassword());
            pstmt.setString(4, admin.getPosition()); // Changed to position
            pstmt.setInt(5, admin.getEmployeeId());
            pstmt.setDouble(6, admin.getAmount());
            pstmt.setInt(7, admin.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an admin record by ID
    public void deleteAdmin(int id) {
        String query = "DELETE FROM Admin WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find an admin by ID
    public Admin findAdminById(int id) {
        String query = "SELECT * FROM Admin WHERE id = ?";
        Admin admin = null;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    admin = new Admin();
                    admin.setId(rs.getInt("id"));
                    admin.setName(rs.getString("name"));
                    admin.setEmail(rs.getString("email"));
                    admin.setPassword(rs.getString("password"));
                    admin.setPosition(rs.getString("position")); // Changed to position
                    admin.setCreatedAt(rs.getTimestamp("created_at"));
                    admin.setId(rs.getInt("employee_id"));
                    admin.setAmount(rs.getDouble("amount"));
                    admin.setPaymentDate(rs.getTimestamp("payment_date")); // Assuming you have this method
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
