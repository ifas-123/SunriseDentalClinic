# 🦷 Sunrise Dental Clinic Management System

A Java Swing desktop application developed for managing appointments, patient information, treatment details, billing, invoices, and manager appointment records for Sunrise Dental Clinic.

The application uses a MySQL database for persistent data storage and includes JUnit automated testing and GitHub Actions continuous integration.

---

## 📋 Project Overview

The **Sunrise Dental Clinic Management System** is a desktop-based application developed using Java Swing.

The system provides a simple interface for clinic staff to manage important appointment and billing activities. It allows users to log in, register appointments, search for existing appointments, generate invoices and allow managers to view and refresh appointment records.

The application follows a basic separation between the user-interface, model and repository/database components.

---

## ✨ Main Features

### 🔐 User Login

* User authentication using username and password.
* Different functionality can be accessed according to the logged-in user's role.
* Provides access to the main application menu after successful authentication.

### 📅 Appointment Management

* Register new patient appointments.
* Store patient name, address and contact number.
* Store dentist information.
* Select the required treatment type.
* Store appointment date and time.
* Save appointment information to the MySQL database.

### 🔎 Appointment Search

* Search for an appointment using its appointment number.
* Retrieve appointment information from the database.
* Display appointment details in a separate window.
* Handle searches for appointments that do not exist.

### 🧾 Billing and Invoice

* Search for appointment information for billing.
* Retrieve the relevant treatment price.
* Generate an invoice containing appointment and billing information.
* Display the calculated treatment amount.

### 👨‍💼 Manager Functionality

* Provide a manager-specific interface.
* View appointment records.
* Refresh appointment information from the database.

### 🧪 Automated Testing

* JUnit automated tests are included in the project.
* Eight automated tests are implemented.
* Tests cover appointment information, appointment searching and treatment pricing.
* The automated test suite is executed locally using the JUnit Platform Console Launcher.

### ⚙️ Continuous Integration

* GitHub Actions is configured for automated build and testing.
* The project is automatically built and the JUnit tests are executed through the GitHub Actions workflow.

---

## 🛠️ Technologies Used

| Technology            | Purpose                                    |
| --------------------- | ------------------------------------------ |
| ☕ Java 21             | Application development                    |
| 🖥️ Java Swing        | Desktop graphical user interface           |
| 🗄️ MySQL             | Database management                        |
| 🔌 JDBC               | Java-to-MySQL database connectivity        |
| 🧪 JUnit              | Automated testing                          |
| 🌐 GitHub             | Source-code repository and version control |
| ⚙️ GitHub Actions     | Continuous integration                     |
| 💻 Visual Studio Code | Development environment                    |

---

## 📁 Project Structure

```text
SunriseDentalClinic/
│
├── src/
│   ├── model/
│   │   ├── Appointment.java
│   │   └── User.java
│   │
│   ├── repository/
│   │   ├── DatabaseConnection.java
│   │   ├── UserRepository.java
│   │   └── AppointmentRepository.java
│   │
│   └── ui/
│       ├── Main.java
│       ├── LoginFrame.java
│       ├── MainMenuFrame.java
│       ├── AppointmentFrame.java
│       ├── AppointmentSearchFrame.java
│       ├── BillingFrame.java
│       └── ManagerFrame.java
│
├── test/
│   └── AppointmentTest.java
│
├── lib/
│   ├── mysql-connector-j-9.7.0.jar
│   └── junit-platform-console-standalone-6.0.1.jar
│
├── .github/
│   └── workflows/
│       └── build-and-test.yml
│
├── database/
│   └── sunrise_dental_clinic.sql
│
└── README.md
```

---

## 🗄️ Database Setup

The application uses a MySQL database named:

```text
sunrise_dental_clinic
```

A SQL database file is included in the repository under:

```text
database/sunrise_dental_clinic.sql
```

### Database Installation

1. Start the MySQL server using XAMPP or another MySQL installation.
2. Open phpMyAdmin or another MySQL database management tool.
3. Create a database named:

```text
sunrise_dental_clinic
```

4. Import the SQL file:

```text
database/sunrise_dental_clinic.sql
```

5. Verify that the required tables have been created.
6. Update the database connection settings in **DatabaseConnection.java** if required.

---

## 🔌 Database Connection

The application connects to MySQL using JDBC.

The MySQL JDBC driver is included in the project's `lib` folder:

```text
mysql-connector-j-9.7.0.jar
```

The database connection is handled by:

```text
DatabaseConnection.java
```

The connection configuration should contain the appropriate MySQL URL, username and password for the local development environment.

---

## ▶️ Running the Application

### Requirements

Before running the application, make sure the following are installed:

* ☕ Java Development Kit 21
* 🗄️ MySQL Server
* 🖥️ XAMPP or another MySQL environment
* 💻 Visual Studio Code or another Java-compatible IDE

### Steps

1. Clone or download this repository.
2. Start the MySQL server.
3. Create the `sunrise_dental_clinic` database.
4. Import the SQL file from the `database` folder.
5. Verify the database connection settings.
6. Make sure the required JAR libraries are available in the `lib` folder.
7. Compile the Java source files.
8. Run the `Main.java` class.

---

## 🧪 Automated Testing

The project contains an automated JUnit test class:

```text
test/AppointmentTest.java
```

The automated test suite contains eight tests covering:

* Appointment number
* Patient name
* Contact number
* Treatment type
* Existing appointment search
* Non-existing appointment search
* Treatment price
* Invalid treatment price

The final automated test execution produced:

```text
8 tests found
8 tests started
8 tests successful
0 tests failed
```

Therefore, all eight implemented automated tests successfully passed during final testing.

---

## ⚙️ GitHub Actions

GitHub Actions is used to automatically build and test the project.

The workflow is located at:

```text
.github/workflows/build-and-test.yml
```

The workflow performs the following general process:

```text
Git Commit
    ↓
GitHub Repository
    ↓
GitHub Actions
    ↓
Build Application
    ↓
Run JUnit Tests
    ↓
Test Result
```

A successful workflow confirms that the project can be built and that the automated test suite completes successfully in the GitHub Actions environment.

---

## 🔄 Version Control

Git is used to track changes made during development.

The GitHub repository provides:

* 📌 Project source-code storage
* 📝 Development commit history
* 🔄 Version tracking
* 🧪 Automated testing through GitHub Actions
* 📚 Project documentation

The commit history records the development stages of the Sunrise Dental Clinic Management System.

---

## 👥 System Functionality

The main application functionality includes:

| Functionality           | Description                                             |
| ----------------------- | ------------------------------------------------------- |
| 🔐 Login                | Authenticates registered users                          |
| 📅 Register Appointment | Creates and stores new appointments                     |
| 🔎 Search Appointment   | Retrieves existing appointment details                  |
| 🧾 Billing              | Retrieves treatment prices and billing information      |
| 🧾 Invoice              | Displays appointment and billing information            |
| 👨‍💼 Manager           | Allows managers to view and refresh appointment records |
| 🗄️ Database            | Stores application information using MySQL              |

---

## 🧪 Testing Summary

The application was tested using both manual and automated testing approaches.

Manual testing covered the major functional areas of the application, including login, appointment registration, validation, appointment searching, billing, manager functionality and database storage.

Automated testing was implemented using JUnit for selected appointment and treatment-related functionality.

The final automated testing execution resulted in:

**8/8 automated tests passed.**

GitHub Actions was also used to execute the automated build and testing process.

---

## 📌 Project Status

**Status: Completed**

The application includes the required core desktop functionality, database integration, automated testing, version control and continuous integration configuration.

---

## 👨‍💻 Development

**Project:** Sunrise Dental Clinic Management System
**Platform:** Java Desktop Application
**Database:** MySQL
**Testing:** JUnit
**CI:** GitHub Actions
