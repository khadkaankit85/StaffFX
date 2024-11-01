package com.example.stafffx.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/HRManagementDB";
    private static final String USER = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, null);
    }
}
