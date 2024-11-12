# StaffFX - HR Management System

## Overview

StaffFX is a JavaFX-based HR Management System designed to simplify and streamline employee and admin management for small to medium-sized organizations. The application provides secure CRUD (Create, Read, Update, Delete) functionalities for managing employees and administrators, complete with authentication, search, and filtering capabilities, all within a modern, user-friendly interface.

## Features

- **Authentication**: Admin login to access and manage employee records.
- **Employee Management**: Easily create, view, update, and delete employee records.
- **Real-Time Table Views**: Display and sort employee data with dynamic updates.
- **Dialogues and Confirmation Prompts**: Confirm critical actions, like deletions, via dialog boxes.

## Technologies Used

- **Java** - Programming language
- **JavaFX** - GUI framework for creating responsive interfaces
- **FXML** - Defines the layout structure
- **MySQL** - Database for persistent storage
- **DAO Pattern** - Structured data handling and database interaction
- **CSS** - Styling for a modern, cohesive look and feel
- **Maven** - Dependency and project management

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/khadkaankit85/StaffFX.git
   cd StaffFX
   ```

2. **Setup Database**:
    - Create a new SQLite/MySQL database.
    - Run the SQL script in `db/setup.sql` to initialize tables.

3. **Build and Run the Project**:
   ```bash
   mvn clean install
   mvn javafx:run
   ```

## Screenshots

1. **Login Page and Sign Up page**  
   ![Login Page](https://raw.githubusercontent.com/khadkaankit85/Assets/refs/heads/master/HRManagement/2.png)
   ![Sign Up Page](https://raw.githubusercontent.com/khadkaankit85/Assets/refs/heads/master/HRManagement/1.png)

2. **Dashboard**  
   ![Dashboard](https://github.com/khadkaankit85/Assets/blob/master/HRManagement/6.png?raw=true)

3. **Admin and Employee Management**  
   ![Admin Management](https://github.com/khadkaankit85/Assets/blob/master/HRManagement/3.png?raw=true)

4. **Delete Confirmation**  
   ![Delete Confirmation](https://github.com/khadkaankit85/Assets/blob/master/HRManagement/5.png?raw=true)

5. **Create Employee Dialog**  
   ![Create Dialog](https://github.com/khadkaankit85/Assets/blob/master/HRManagement/1.png?raw=true)

6. **Database Structure**  
   ![Database](https://github.com/khadkaankit85/Assets/blob/master/HRManagement/7.png?raw=true)


## Usage

1. **Login**: Start the application and log in with your admin credentials.
2. **Manage Employees**: Add, update, and delete employee records directly from the dashboard.
3**Sort Records**: Click table headers to sort data by the desired field.

## Sample Data Insertion

You can insert 20 sample employee records for testing and demonstration. Below is an example of how you might use SQL for bulk insertion:

```sql
INSERT INTO admin (name, email, password, position, created_at, employee_id, amount, payment_date) VALUES 
('Rajesh Shrestha', 'rajesh@example.com', 'password123', 'Software Engineer', '2024-01-15 10:30:00', 1, 500.00, '2024-01-31 10:30:00'),
('Anjali Thapa', 'khdaklaanki', 'password456', 'Project Manager', '2024-01-20 09:00:00', 2, 6000.00, '2024-01-31 09:00:00'),
('Suman Gurung', 'suman@example.com', 'password789', 'HR Specialist', '2024-01-22 11:15:00', 3, 4000.00, '2024-01-31 11:15:00'),
('Priya Koirala', 'priya@example.com', 'password101', 'QA Tester', '2024-01-25 14:45:00', 4, 4500.00, '2024-01-31 14:45:00'),
('Bikram Bhandari', 'human@example.com', 'password202', 'DevOps Engineer', '2024-01-28 08:00:00', 5, 500.00, '2024-01-31 08:00:00'),
('Ankit Khadka', 'ankit@example.com', 'default_pass', 'Unassigned', '2024-11-02 19:01:56', 0, 0.05, '2024-11-02 19:01:56'),
('John Doe', 'john@example.com', 'password1', 'Manager', '2024-11-01 12:00:00', 1, 7000.00, '2024-11-10 12:00:00'),
('Jane Smith', 'jane@example.com', 'password2', 'Developer', '2024-11-01 13:00:00', 2, 8000.00, '2024-11-10 13:00:00'),
('Emily Johnson', 'emily@example.com', 'password3', 'Designer', '2024-11-01 14:00:00', 3, 6000.00, '2024-11-10 14:00:00'),
('Chris Lee', 'chris@example.com', 'password4', 'QA Tester', '2024-11-01 15:00:00', 4, 5000.00, '2024-11-10 15:00:00'),
('Sara Wilson', 'sara@example.com', 'password5', 'Project Manager', '2024-11-01 16:00:00', 5, 7500.00, '2024-11-10 16:00:00'),
('Mike Brown', 'mike@example.com', 'password6', 'DevOps Engineer', '2024-11-01 17:00:00', 6, 9000.00, '2024-11-10 17:00:00'),
('Olivia Davis', 'olivia@example.com', 'password7', 'HR Specialist', '2024-11-01 18:00:00', 7, 4500.00, '2024-11-10 18:00:00'),
('Liam Martinez', 'liam@example.com', 'password8', 'Software Engineer', '2024-11-01 19:00:00', 8, 6200.00, '2024-11-10 19:00:00'),
('Noah Hernandez', 'noah@example.com', 'password9', 'System Analyst', '2024-11-01 20:00:00', 9, 5800.00, '2024-11-10 20:00:00'),
('Emma Garcia', 'emma@example.com', 'password10', 'IT Support', '2024-11-01 21:00:00', 10, 3000.00, '2024-11-10 21:00:00'),
('Ava Jackson', 'ava@example.com', 'password11', 'Business Analyst', '2024-11-01 22:00:00', 11, 7000.00, '2024-11-10 22:00:00'),
('Sophia Taylor', 'sophia@example.com', 'password12', 'Marketing Specialist', '2024-11-01 23:00:00', 12, 8000.00, '2024-11-10 23:00:00');
```

## Learning Outcomes

- Implementing CRUD operations using JavaFX and MySQL.
- Utilizing the DAO pattern for structured data management.
- Applying the MVC architecture for cleaner and maintainable code.
