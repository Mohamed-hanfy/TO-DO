# TO-DO Console Application

A Java-based console application for managing your tasks, with SQL Server integration.

## Features

* Add tasks with titles, checkboxes, and optional descriptions
* Mark tasks as completed
* Delete tasks
* View tasks in list format
* Pin task priority
* User authentication (login options)

## Prerequisites

* Java Development Kit (JDK) 11 or higher
* Microsoft SQL Server
* JDBC Driver for SQL Server

## Setup

1. Clone the repository:
   ````` bash
   git clone https://github.com/Mohamed-hanfy/TODO.git
   `````

2. Set up your SQL Server database:
   * Create a new database named `TodoApp`
   * Run the SQL script in `database_schema.sql` to create the necessary tables

3. Update the database connection details in `DatabaseAppManager.java`:
   ````` java
   private static final String DB_URL = "jdbc:sqlserver://localhost;databaseName=TodoApp;";
   private static final String USER = "your_username";
   private static final String PASS = "your_password";
   `````

4. Compile the Java files:
   ````` bash
   javac *.java
   `````

## Usage

Run the application:
````` bash
java TodoApp
`````

Follow the on-screen prompts to:
* Log in or create a new account
* Add, view, update, and delete tasks
* Set task priorities
* Mark tasks as completed

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the `LICENSE.md` file for details.
