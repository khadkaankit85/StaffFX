package com.example.stafffx.Controller;

import com.example.stafffx.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    public ImageView logoutImage;
    // Reference to the MainController to use its methods
    private MainController mainController =MainController.getInstance();
    @FXML
    Button loginButtonLabel;

    @FXML
    Label usernameLabel;


    public DashboardController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tooltip tooltip = new Tooltip("Logout");
        Tooltip.install(logoutImage, tooltip);
        usernameLabel.setText(mainController.getUsername());

        Platform.runLater(() -> usernameLabel.requestFocus());

    }


    @FXML
    public void handleAdminButtonAction(ActionEvent actionEvent) {
        // Change scene to employee-view.fxml
       String role= mainController.getRole();
       if(role.equals("admin")){
           mainController.changeSceneToAnother("employee-view.fxml", (Node) actionEvent.getSource());
       }
       else{
           Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Unauthorized");
           alert.setHeaderText(null);
           alert.setContentText("You don't have the permission to access this resource");
           alert.showAndWait();
       }
    }

    @FXML
    public void handleEmployeeButtonAction(ActionEvent actionEvent) {
        // Change scene to admin-view.fxml
        mainController.changeSceneToAnother("admin-view.fxml", (Node) actionEvent.getSource());
    }


    @FXML
    public void handleExitButtonAction(ActionEvent actionEvent) {
        // Close the application
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");
       Optional<ButtonType> confirmation= alert.showAndWait();
       if(confirmation.get()==ButtonType.OK){
           Stage stage = (Stage) loginButtonLabel.getScene().getWindow();
           stage.close();
       }
    }

    public void logoutButtonPress(ActionEvent mouseEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to Log Out?");
        Optional<ButtonType> confirmation= alert.showAndWait();
        if(confirmation.get()==ButtonType.OK){
            mainController.changeSceneToAnother("login-view.fxml",(Node) mouseEvent.getSource());
            mainController.setUsername(null);
        }

    }


}
