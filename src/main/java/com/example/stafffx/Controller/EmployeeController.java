package com.example.stafffx.Controller;

import com.example.stafffx.DAO.EmployeeDAO;
import com.example.stafffx.MainController;
import com.example.stafffx.Model.Admin;
import com.example.stafffx.Model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.net.URL;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    static final EmployeeDAO employeeDAO = new EmployeeDAO();
    public TableView<Employee> employeeTable;  // Specify the type for TableView
    public TableColumn<Employee, Integer> idColumn;
    public TableColumn<Employee, String> nameColumn;
    public TableColumn<Employee, String> emailColumn;
    public TableColumn<Employee, Double> salaryColumn;
    public TableColumn<Employee, Timestamp> createdColumn;
    public TableColumn salaryYearlyColumn;

    //dao for employee table

   final  MainController sceneController=MainController.getInstance();
    public Label usernameLabel;
    public ImageView logoutImage;
    public ImageView backButton;
    public ImageView savebutton;
    public ImageView refreshbutton;
    public ImageView addbutton;

    //for data
    public ObservableList<Employee> employeeList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        salaryYearlyColumn.setCellValueFactory(new PropertyValueFactory<>("yearlySalary"));

//        employeeTable.setEditable(true);
//        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        positionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//
//        salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));




        // Fetch data from DAO
        List<Employee> employees = employeeDAO.readAllEmployees();
        employeeList = FXCollections.observableArrayList(employees);

        // Populate the TableView
        employeeTable.setItems(employeeList);
        usernameLabel.setText(sceneController.getUsername());

        //height adjustment
        double rowHeight = 25;
        int rowCount = employeeTable.getItems().size();



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
            sceneController.changeSceneToAnother("login-view.fxml",employeeTable);
            sceneController.setUsername(null);
        }

    }

    public void ongoBackButtonPress(MouseEvent mouseEvent) {
        sceneController.changeSceneToAnother("dashboard-view.fxml",employeeTable);
    }

    public void ondelete(MouseEvent mouseEvent) {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
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
                employeeDAO.deleteEmployee(selectedEmployee.getId());
                onrefresh(mouseEvent);
            }
        }
    }

    public void onrefresh(MouseEvent mouseEvent) {
        sceneController.changeSceneToAnother("employee-view.fxml",employeeTable);
    }

    public void onclick(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount()==2){
            Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                // Open a dialog or print to console
                openEmployeeDialog(selectedEmployee, true).ifPresent(result -> {
                    Employee updatedEmployee = result.getKey();

                    employeeList.stream()
                            .filter(employee -> employee.getId() == updatedEmployee.getId())
                            .findFirst()
                            .ifPresent(employee -> {
                                int index = employeeList.indexOf(employee);
                                employeeList.set(index, updatedEmployee);
                                employeeDAO.updateEmployee(updatedEmployee);
                            });


                });

                // Show a sample update dialog
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update data for " + selectedEmployee.getName(), ButtonType.OK);
                alert.setTitle("Update Employee");
                alert.setHeaderText("Update Employee Details");
                alert.showAndWait();
        }
        }
    }
    public void oncreate(MouseEvent mouseEvent) {
        Employee newEmployee = new Employee();
        openEmployeeDialog(newEmployee, false).ifPresent(result -> {
            Employee employee = result.getKey();
            if(employee.isCreatable()) {
                employee.setId(employeeList.size() + 1);
                employee.setCreatedAt(new java.sql.Timestamp(new Date().getTime()));
                employeeList.add(employee);
                employeeDAO.createEmployee(employee);
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
    public Optional<Pair<Employee, Boolean>> openEmployeeDialog(Employee employee, boolean isUpdate) {
        // Create a new dialog
        Dialog<Pair<Employee, Boolean>> dialog = new Dialog<>();
        dialog.setTitle(isUpdate ? "Update Employee" : "Add New Employee");
        dialog.setHeaderText(isUpdate ? "Edit Employee Details" : "Enter New Employee Details");

        // Set dialog buttons
        ButtonType saveButtonType = new ButtonType(isUpdate ? "Update" : "Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create input fields
        Label idLabel = new Label(isUpdate?employee.getId()+"":employeeList.size()+1+"");
        TextField nameField = new TextField(employee.getName());
        nameField.setPromptText("Name");

        TextField emailField = new TextField(employee.getEmail());
        emailField.setPromptText("Email");

        TextField salaryField = new TextField(String.valueOf(employee.getAmount()));
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
                employee.setName(nameField.getText());
                employee.setEmail(emailField.getText());
                employee.setAmount(Double.parseDouble(salaryField.getText()));
                return new Pair<>(employee, isUpdate);
            }
            return null;
        });

        // Show the dialog and return the result
        return dialog.showAndWait();
    }
}
