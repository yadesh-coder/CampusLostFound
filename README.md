# Campus Lost & Found Management System

A Java-based **Campus Lost & Found Management System** developed using JDBC and Oracle 11g XE to help students and administrators report, search, match, and manage lost and found items within a campus.

## Tech Stack

* **Java 21**
* **JDBC**
* **Oracle Database 11g XE**
* **SQL**
* **DAO Design Pattern**

## Features

### User Management

* User registration
* User login
* View and update user details
* Delete user account
* Password change
* Search users

### Authentication & Authorization

* Login authentication
* Role-based access control
* **ADMIN** and **USER** roles
* Session management
* Logout functionality

### Lost & Found Management

* Report a lost item
* Report a found item
* View lost and found reports
* Search items
* Match lost items with found items
* Update item status

### Claim Management

* Submit claims for found items
* Admin approval/rejection of claims
* Track claim status

### Database Management

* Oracle 11g XE database
* JDBC connectivity
* CRUD operations
* SQL queries
* Joins and relational operations
* Transaction-based database operations

## System Architecture

```text
                 ┌──────────────────────┐
                 │      Java Client     │
                 │      Main.java       │
                 └──────────┬───────────┘
                            │
                            ▼
                 ┌──────────────────────┐
                 │    Business Logic    │
                 │      Services        │
                 └──────────┬───────────┘
                            │
                            ▼
                 ┌──────────────────────┐
                 │      DAO Layer       │
                 │  Database Operations │
                 └──────────┬───────────┘
                            │
                         JDBC
                            │
                            ▼
                 ┌──────────────────────┐
                 │   Oracle 11g XE      │
                 │      Database        │
                 └──────────────────────┘
```

## Main Modules

```text
Campus Lost & Found Management System
│
├── User Management
│   ├── Registration
│   ├── Login
│   ├── Search
│   ├── Update
│   └── Delete
│
├── Authentication
│   ├── User Login
│   ├── Admin Login
│   ├── Role Management
│   └── Logout
│
├── Lost Item Management
│   ├── Report Lost Item
│   ├── Search Lost Items
│   └── View Lost Reports
│
├── Found Item Management
│   ├── Report Found Item
│   ├── Search Found Items
│   └── View Found Reports
│
├── Matching
│   └── Lost ↔ Found Matching
│
└── Claim Management
    ├── Submit Claim
    ├── Approve Claim
    └── Reject Claim
```

## Database

The application uses **Oracle 11g XE** as the backend database.

The system manages entities such as:

* Users
* Items
* Lost Reports
* Found Reports
* Claims

The database is accessed through **JDBC**.

## JDBC Connectivity

The application uses JDBC to connect the Java application with Oracle Database.

```text
Java Application
       ↓
     JDBC
       ↓
Oracle 11g XE
```

The database connection is centralized through a database utility layer, while DAO classes handle database operations.

## Role-Based Access

### USER

Users can:

* Register and login
* Report lost items
* Report found items
* Search items
* Submit claims
* View their reports

### ADMIN

Administrators can:

* Manage users
* View reports
* Review claims
* Approve claims
* Reject claims
* Manage system data

## Project Structure

```text
CampusLostAndFound/
│
├── src/
│   └── com/
│       └── lostfound/
│           ├── Main.java
│           ├── DBUtil.java
│           │
│           ├── dao/
│           │   ├── UserDAO.java
│           │   ├── ItemDAO.java
│           │   ├── LostReportDAO.java
│           │   ├── FoundReportDAO.java
│           │   └── ClaimDAO.java
│           │
│           ├── model/
│           │   ├── User.java
│           │   ├── Item.java
│           │   ├── LostReport.java
│           │   ├── FoundReport.java
│           │   └── Claim.java
│           │
│           └── service/
│               └── Business Logic
│
└── database/
    └── SQL Scripts
```

## Key Technologies

```text
Java 21
JDBC
Oracle 11g XE
SQL
DAO Pattern
CRUD Operations
Role-Based Access Control
Transaction Management
```

## Objective

The primary objective of this project is to provide a centralized system for managing lost and found items within a college campus, reducing manual coordination and making it easier for students and administrators to report, locate, match, and recover lost belongings.

## Future Enhancements

* Web-based frontend
* Email notifications
* Image upload for items
* Advanced item matching
* Search filters
* Admin dashboard
* Automated notifications for matched items
