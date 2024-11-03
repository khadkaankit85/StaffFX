package com.example.stafffx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    private static MainController instance;  // Single instance of the class
    private String username; // Field to store the username
    private String role;

    // Private constructor to prevent instantiation
    private MainController() {}

    // Public method to provide access to the single instance
    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public void changeSceneToAnother(String fxmlName, Node refElement) {
        try {
            // Load the FXML file
            Parent newSceneRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlName)));
            Stage currentStage = (Stage) refElement.getScene().getWindow();
            // Create a new scene with the loaded FXML
            Scene newScene = new Scene(newSceneRoot, 1250, 700);

            newScene.getStylesheets().add("file:target/classes/Styles/main.css");

            // Set the new scene on the current stage
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            System.out.println("Error loading scene: " + e.getMessage());
        }
    }

    // Getter for username
    public String getUsername() {
        System.out.println("Returning username: " + username);
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        System.out.println("New username is " + username);
        this.username = username;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
