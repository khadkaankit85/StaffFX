package com.example.stafffx.Model;

import java.sql.Timestamp;

public class Admin {
    private int id; // Unique identifier for the employee
    private String name; // Employee's name
    private String email; // Employee's email address
    private String password; // Employee's password
    private String position; // Employee's position
    private Timestamp createdAt; // Timestamp of when the employee was created
    private int employeeId; // Employee ID reference for salary
    private double amount; // Salary amount
    private Timestamp paymentDate; // Date of salary payment

    // Constructor
    public Admin() {}
    // Getters and Setters
    public int getId() {
        return id;
    }
    public boolean isCreatable() {
        return name.length() > 3 && email.length() > 3 && amount > 0;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getYearlySalary(){
        return amount*12;
    }

}
