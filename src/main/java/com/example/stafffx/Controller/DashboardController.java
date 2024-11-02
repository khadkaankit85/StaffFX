package com.example.stafffx.Controller;

import com.example.stafffx.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DashboardController {

    // Reference to the MainController to use its methods
    private MainController mainController =new MainController();

    public DashboardController() {
    }


    @FXML
    public void handleAdminButtonAction(ActionEvent actionEvent) {
        // Change scene to admin-view.fxml
        mainController.changeSceneToAnother("admin-view.fxml", (Node) actionEvent.getSource());
    }

    @FXML
    public void handleEmployeeButtonAction(ActionEvent actionEvent) {
        // Change scene to employee-view.fxml
        mainController.changeSceneToAnother("employee-view.fxml", (Node) actionEvent.getSource());
    }

    @FXML
    public void handleLogoutButtonAction(ActionEvent actionEvent) {
        // Optionally clear username or other data if needed
        mainController.setUsername(null); // Clear the saved username

        // Change scene to login-view.fxml (or wherever the login is)
        mainController.changeSceneToAnother("login-view.fxml", (Node) actionEvent.getSource());
    }

    @FXML
    public void handleExitButtonAction(ActionEvent actionEvent) {
        // Close the application
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
