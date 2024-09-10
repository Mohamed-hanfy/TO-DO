# To-Do Console Application

A simple **Java OOP**-based To-Do console application that connects to an **SQL Server** database to manage tasks. This application allows users to add, view, complete, delete, and prioritize tasks. Users can also login to access their tasks from different machines.

## Features

- **Task Creation**: 
  - Add tasks with a **title**, a **checkbox** for completion status, and an **optional description**.
  
- **Mark as Completed**: 
  - Mark tasks as **completed** using a checkbox.

- **Delete Tasks**: 
  - Remove tasks from your list with the **delete option**.

- **Task Listing**: 
  - View tasks in a **list format** for better readability, showing their title, description, priority, and status.

- **Task Priority**: 
  - **Pin tasks by priority** to show important tasks at the top of the list.

- **User Login**:
  - Secure **login system** that allows users to access their task data from any machine.

## Prerequisites

- **Java 11** or higher
- **SQL Server**
- **JDBC Driver** for SQL Server (e.g., `mssql-jdbc-12.8.1.jre11.jar`)

## Installation

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/Mohamed-hanfy/TODO.git
    cd todo-console-app
    ```

2. **Set Up SQL Server**:
    - Create a new database (e.g., `TodoApp`).
    - Run the following SQL to create the necessary tables:
    ```sql
    USE [TodoApp]
    GO

    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

    CREATE TABLE [dbo].[users](
        [Id] [int] IDENTITY(1,1) NOT NULL,
        T NULL,
     CONSTRKEY CLUSTERED 
    (
        [Id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

    USE [TodoApp]
    GO

    /****** Object:  Table [dbo].[tasks]    Script Date: 09/09/2024 11:22:48 م ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

    CREATE TABLE [dbo].[tasks](
        [Id] [int] IDENTITY(1,1) NOT NULL,
        [UserID] [int] NOT NULL,
          NOT NULL,
          NULL,
        [completed[Priority] [int] NUs] PRIMARY KEY CLUSTERED 
    (
        [Id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

    ALTER TABLE [dbo].[tasks]  WITH CHECK ADD  CONSTRAINT [FK_tasks_users] FOREIGN KEY([UserID])
    REFERENCES [dbo].[users] ([Id])
    GO

    ALTER TABLE [dbo].[tasks] CHECK CONSTRAINT [FK_tasks_users]
    GO
    ```

3. **Configure Database Connection**:
    - Edit the `DBConnection.java` file with your SQL Server connection details:
    ```java
    String url = "jdbc:sqlserver://localhost:1433;databaseName=TodoApp";
    String username = "your_sql_username";
    String password = "your_sql_password";
    ```

4. **Compile and Run the Application**:
    ```bash
    javac -cp "path_to_jdbc_driver/mssql-jdbc-9.4.0.jre11.jar" src/*.java
    java -cp ".:path_to_jdbc_driver/mssql-jdbc-9.4.0.jre11.jar" Main
    ```

## Usage

1. **Login**: Log in or create a new account.
2. **Add a Task**: Enter task title, description (optional), and priority.
3. **View Tasks**: List your tasks showing their details (title, description, priority, completion status).
4. **Complete a Task**: Mark tasks as completed using the checkbox.
5. **Delete a Task**: Remove tasks using the delete option.
6. **Pin Tasks**: Pin tasks by priority to show important tasks at the top of the list.
7. **Logout**: Save and log out to access your tasks on other machines.

## Built With

- **Java** - Object-Oriented Programming
- **SQL Server** - For data storage
- **JDBC** - For database connectivity
- **Stored Procedures** - For interacting with the database

## Future Enhancements

- **Task Reminders**: Add reminders for upcoming tasks.
- **Task Filtering**: Filter tasks by status, priority, or date.
- **GUI**: Develop a graphical user interface for improved usability.

## Author

- **Your Name** - [Your GitHub](https://github.com/Mohamed-hanfy)

##

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
