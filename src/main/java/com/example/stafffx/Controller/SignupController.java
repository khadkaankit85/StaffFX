package com.example.stafffx.Controller;

import com.example.stafffx.MainController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.intellij.lang.annotations.RegExp;

public class SignupController {
    public Label msgText;
    public TextField emailField;
    public PasswordField passwordField;
    public Button signupButton;
    public TextField nameField;
    public PasswordField passwordField1;

    String namePattern = "^[a-zA-Z\\s]{2,50}$";
    String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";



   public boolean formDataIsValid(String name, String email, String password, String confirmPassword) {
        if (name == null || email == null || password == null || confirmPassword == null ||
                name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }
        return name.matches(namePattern) && email.matches(emailPattern) && password.matches(passwordPattern) && password.equals(confirmPassword);
    }

    public void handleSignupButtonPress(ActionEvent actionEvent) {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = passwordField1.getText();
        System.out.println(name + " " + email + " " + password + " " + confirmPassword);
        if (formDataIsValid(name, email, password, confirmPassword)) {
            msgText.setText("You have successfully registered!");
            msgText.setStyle("-fx-text-fill: green;");
        }
        else {
            msgText.setText("Not all form fields are valid!");
            msgText.setStyle("-fx-text-fill: red;");
        }
    }

    public void gotoLoginPage(MouseEvent mouseEvent) {
        MainController mainController = MainController.getInstance();
        mainController.changeSceneToAnother("login-view.fxml", msgText);
    }
}
