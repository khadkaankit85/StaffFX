package com.example.stafffx.Controller;

import com.example.stafffx.DAO.EmployeeDAO;
import com.example.stafffx.Model.EmployeeDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EmployeeController {
    @FXML
    private TableView<EmployeeDetail> employeeTable;

    @FXML
    private TableColumn<EmployeeDetail, Integer> idColumn;
    @FXML
    private TableColumn<EmployeeDetail, String> nameColumn;
    @FXML
    private TableColumn<EmployeeDetail, String> departmentColumn; // Ensure EmployeeDetail has department
    @FXML
    private TableColumn<EmployeeDetail, String> positionColumn; // Ensure EmployeeDetail has position
    @FXML
    private TableColumn<EmployeeDetail, Double> salaryColumn; // Ensure EmployeeDetail has salary

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField departmentField; // Add this field in EmployeeDetail
    @FXML
    private TextField positionField; // Add this field in EmployeeDetail
    @FXML
    private TextField salaryField;

    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private ObservableList<EmployeeDetail> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department")); // Assuming it's in EmployeeDetail
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position")); // Assuming it's in EmployeeDetail
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary")); // Assuming it's in EmployeeDetail

        // Load initial employee data
        updateTable();
    }

    @FXML
    public void handleCreateAction(ActionEvent actionEvent) {
        if (validateFields()) {
            EmployeeDetail employee = new EmployeeDetail();
            employee.setName(nameField.getText());

            // Uncomment and set these fields if needed
            // employee.setEmail(...);
            // employee.setPassword(...);
            // employee.setRole(...);

            employeeDAO.createEmployee(employee);
            updateTable();
            clearFields();
        }
    }

    @FXML
    public void handleUpdateAction(ActionEvent actionEvent) {
        if (validateFields() && !idField.getText().isEmpty()) {
            int id = Integer.parseInt(idField.getText());
            EmployeeDetail employee = new EmployeeDetail();
            employee.setId(id);
            employee.setName(nameField.getText());

            employeeDAO.updateEmployee(employee);
            updateTable();
            clearFields();
        }
    }

    @FXML
    public void handleDeleteAction(ActionEvent actionEvent) {
        if (!idField.getText().isEmpty()) {
            int id = Integer.parseInt(idField.getText());
            employeeDAO.deleteEmployee(id);
            updateTable();
            clearFields();
        } else {
            showAlert("Input Error", "Please enter an ID to delete an employee.");
        }
    }

    @FXML
    public void handleViewAction(ActionEvent actionEvent) {
        updateTable();
    }

    @FXML
    public void handleBackAction(ActionEvent actionEvent) {
        // Implement logic to navigate back to the previous screen
    }

    private void updateTable() {
        employeeList.clear();
        employeeList.addAll(employeeDAO.readAllEmployees());
        employeeTable.setItems(employeeList);
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        departmentField.clear();
        positionField.clear();
        salaryField.clear();
    }

    private boolean validateFields() {
        if (nameField.getText().isEmpty() || departmentField.getText().isEmpty() ||
                positionField.getText().isEmpty() || salaryField.getText().isEmpty()) {
            showAlert("Input Error", "All fields must be filled.");
            return false;
        }
        try {
            Double.parseDouble(salaryField.getText()); // Check if salary is a valid number
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Salary must be a valid number.");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
