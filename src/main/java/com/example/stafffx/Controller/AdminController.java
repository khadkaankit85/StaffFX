package com.example.stafffx.Controller;

import com.example.stafffx.DAO.EmployeeDAO;
import com.example.stafffx.Model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.security.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    static final EmployeeDAO employeeDAO = new EmployeeDAO();
    public TableView<Employee> employeeTable;  // Specify the type for TableView
    public TableColumn<Employee, Integer> idColumn;
    public TableColumn<Employee, String> nameColumn;
    public TableColumn<Employee, String> positionColumn;
    public TableColumn<Employee, Double> salaryColumn;
    public TableColumn<Employee, Timestamp> createdColumn;
    public TableColumn salaryYearlyColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        createdColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        salaryYearlyColumn.setCellValueFactory(new PropertyValueFactory<>("yearlySalary"));

        // Fetch data from DAO
        List<Employee> employees = employeeDAO.readAllEmployees();
        ObservableList<Employee> employeeList = FXCollections.observableArrayList(employees);

        // Populate the TableView
        employeeTable.setItems(employeeList);

    }

    public void handleCreateAction(ActionEvent actionEvent) {
        // Implement logic to create a new employee
    }

    public void handleUpdateAction(ActionEvent actionEvent) {
        // Implement logic to update an existing employee
    }

    public void handleDeleteAction(ActionEvent actionEvent) {
        // Implement logic to delete an employee
    }

    public void handleViewAction(ActionEvent actionEvent) {
        // Implement logic to view selected employee details
    }

    public void handleBackAction(ActionEvent actionEvent) {
        // Implement logic to go back to the previous screen
    }
}
