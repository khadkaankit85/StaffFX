package com.example.stafffx.Controller;

import com.example.stafffx.DAO.AdminDAO;
import com.example.stafffx.MainController;
import com.example.stafffx.Model.Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.net.URL;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    static final AdminDAO adminDAO = new AdminDAO();
    public TableView<Admin> adminTable;  // Specify the type for TableView
    public TableColumn<Admin, Integer> idColumn;
    public TableColumn<Admin, String> nameColumn;
    public TableColumn<Admin, String> emailColumn;
    public TableColumn<Admin, Double> salaryColumn;
    public TableColumn<Admin, Timestamp> createdColumn;
    public TableColumn salaryYearlyColumn;

    //dao for admin table

    final  MainController sceneController=MainController.getInstance();
    public Label usernameLabel;
    public ImageView logoutImage;
    public ImageView backButton;
    public ImageView savebutton;
    public ImageView refreshbutton;
    public ImageView addbutton;

    //for data
    public ObservableList<Admin> adminList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        salaryYearlyColumn.setCellValueFactory(new PropertyValueFactory<>("yearlySalary"));

//        adminTable.setEditable(true);
//        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        positionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//
//        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));






        // Fetch data from DAO
        List<Admin> admins = adminDAO.readAllAdmins();
        adminList = FXCollections.observableArrayList(admins);

        // Populate the TableView
        adminTable.setItems(adminList);
        usernameLabel.setText(sceneController.getUsername());

        //height adjustment
        double rowHeight = 25;
        int rowCount = adminTable.getItems().size();



        // Tooltips
        Tooltip.install(logoutImage, new Tooltip("Logout"));
        Tooltip.install(backButton, new Tooltip("Back"));
        Tooltip.install(savebutton, new Tooltip("Save"));
        Tooltip.install(refreshbutton, new Tooltip("Refresh"));
        Tooltip.install(addbutton, new Tooltip("Add"));


    }



    public void logoutButtonPress(MouseEvent mouseEvent) {

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to Log Out?");
        Optional<ButtonType> confirmation= alert.showAndWait();
        if(confirmation.get()==ButtonType.OK){
            sceneController.changeSceneToAnother("login-view.fxml",adminTable);
            sceneController.setUsername(null);
        }

    }

    public void ongoBackButtonPress(MouseEvent mouseEvent) {
        sceneController.changeSceneToAnother("dashboard-view.fxml",adminTable);
    }

    public void ondelete(MouseEvent mouseEvent) {
            Admin selectedEmployee = adminTable.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Fire " + selectedEmployee.getName());
                alert.setHeaderText(null);
                alert.setContentText("Are you sure about making " + selectedEmployee.getName() + " jobless?");

                ButtonType yesButton = new ButtonType("Yes, "+selectedEmployee.getName() +" is cooked");
                ButtonType noButton = new ButtonType("No");

                alert.getButtonTypes().setAll(yesButton, noButton);

                Optional<ButtonType> confirmation = alert.showAndWait();
                if (confirmation.isPresent() && confirmation.get() == yesButton) {
                    adminDAO.deleteAdmin(selectedEmployee.getId());
                    onrefresh(mouseEvent);
                }
            }
    }

    public void onrefresh(MouseEvent mouseEvent) {
        sceneController.changeSceneToAnother("admin-view.fxml",adminTable);
    }

    public void onclick(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2){
            Admin selectedAdmin = adminTable.getSelectionModel().getSelectedItem();
            if (selectedAdmin != null) {
                // Open a dialog or print to console
                openAdminDialog(selectedAdmin, true).ifPresent(result -> {
                    Admin updatedAdmin = result.getKey();

                    adminList.stream()
                            .filter(admin -> admin.getId() == updatedAdmin.getId())
                            .findFirst()
                            .ifPresent(admin -> {
                                int index = adminList.indexOf(admin);
                                adminList.set(index, updatedAdmin);
                                adminDAO.updateAdmin(updatedAdmin);
                            });


                });

                // Show a sample update dialog
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update data for " + selectedAdmin.getName(), ButtonType.OK);
                alert.setTitle("Update Admin");
                alert.setHeaderText("Update Admin Details");
                alert.showAndWait();
            }
        }
    }
    public void oncreate(MouseEvent mouseEvent) {
        Admin newAdmin = new Admin();
        openAdminDialog(newAdmin, false).ifPresent(result -> {
            Admin admin = result.getKey();
            if(admin.isCreatable()) {
                admin.setId(adminList.size() + 1);
                admin.setCreatedAt(new java.sql.Timestamp(new Date().getTime()));
                adminList.add(admin);
                adminDAO.createAdmin(admin);
            }
            else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid data found");
                alert.showAndWait();
            }
        });
    }
    public Optional<Pair<Admin, Boolean>> openAdminDialog(Admin admin, boolean isUpdate) {
        // Create a new dialog
        Dialog<Pair<Admin, Boolean>> dialog = new Dialog<>();
        dialog.setTitle(isUpdate ? "Update Admin" : "Add New Admin");
        dialog.setHeaderText(isUpdate ? "Edit Admin Details" : "Enter New Admin Details");

        // Set dialog buttons
        ButtonType saveButtonType = new ButtonType(isUpdate ? "Update" : "Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create input fields
        Label idLabel = new Label(isUpdate?admin.getId()+"":adminList.size()+1+"");
        TextField nameField = new TextField(admin.getName());
        nameField.setPromptText("Name");

        TextField emailField = new TextField(admin.getEmail());
        emailField.setPromptText("Email");

        TextField salaryField = new TextField(String.valueOf(admin.getAmount()));
        salaryField.setPromptText("Salary");

        // Arrange fields in a grid layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("ID:"), 0, 0);
        grid.add(idLabel, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(new Label("Salary:"), 0, 3);
        grid.add(salaryField, 1, 3);

        dialog.getDialogPane().setContent(grid);

//        // Enable/disable save button based on input validation
//        Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
//        saveButton.setDisable(nameField.getText().trim().isEmpty());
//
//        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
//            saveButton.setDisable(newValue.trim().isEmpty());
//        });

        // Handle the result of the dialog
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                admin.setName(nameField.getText());
                admin.setEmail(emailField.getText());
                admin.setAmount(Double.parseDouble(salaryField.getText()));
                return new Pair<>(admin, isUpdate);
            }
            return null;
        });

        // Show the dialog and return the result
        return dialog.showAndWait();
    }
}
