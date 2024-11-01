package com.example.stafffx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    private String username; // Field to store the username

    public void changeSceneToAnother(String fxmlName, Node refElement) {
        try {
            // Load the FXML file
            Parent newSceneRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlName)));
            Stage currentStage = (Stage) refElement.getScene().getWindow();
            System.out.println("New scene root loaded: " + newSceneRoot);

            // Create a new scene with the loaded FXML
            Scene newScene = new Scene(newSceneRoot);

            // Optional: Add CSS if needed
            // String stylesheetPath = Objects.requireNonNull(getClass().getResource("/Styles/main.css")).toExternalForm();
            // newScene.getStylesheets().add(stylesheetPath);

            // Set the new scene on the current stage
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            System.out.println("Error loading scene: " + e.getMessage());
        }
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }
}
