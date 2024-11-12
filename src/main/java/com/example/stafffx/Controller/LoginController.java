package com.example.stafffx.Controller;

import com.example.stafffx.DAO.DatabaseConnection;
import com.example.stafffx.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    public Button loginButton;
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label msgText;


    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        String role=validateUser(email,password);
        if (role.equals("admin")|| role.equals("staff")) {
            // Change scene on the JavaFX Application Thread
                 MainController mainController = MainController.getInstance();
                 mainController.setUsername(email);
                  mainController.setRole(role);
                mainController.changeSceneToAnother("dashboard-view.fxml", msgText);
        } else {
            msgText.setText("Invalid email or password. Please try again.");
        }
    }


    private String validateUser(String email, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String adminQuery = "SELECT * FROM Admin WHERE email = ? AND password = ?";
            String employeeQuery = "SELECT * FROM Employee WHERE email = ? AND password = ?";

            // Check Admin credentials
            if (checkCredentials(connection, adminQuery, email, password)) {
                return "admin"; // Successfully logged in as Admin
            }

            // Check Employee credentials
          else  if (checkCredentials(connection, employeeQuery, email, password)) {
                return "staff"; // Successfully logged in as Employee
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return "invalid"; // Login failed
    }

    private boolean checkCredentials(Connection connection, String query, String email, String password) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password); // In practice, use hashed passwords
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // If a record is found, login is successful
        } catch (SQLException e) {
            System.out.println("Error during credential check: " + e.getMessage());
            return false;
        }
    }


    public void gotoSignupPage(MouseEvent mouseEvent) {
        MainController mainController = MainController.getInstance();
        mainController.changeSceneToAnother("signup-view.fxml", msgText);

    }
}
