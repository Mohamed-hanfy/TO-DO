import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabseAppManger {


    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=TodoApp;"
            + "user=jav;"
            + "password=123;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("SQL Server JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }


    public static int addTask(Task task) throws SQLException {
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{? = CALL AddTask(?, ?, ?, ?)}")) {
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setInt(2, task.getUserId());
            stmt.setString(3, task.getTitle());
            stmt.setString(4, task.getDescription());
            stmt.setInt(5, task.getPriority());
            stmt.executeUpdate();
            int taskId = stmt.getInt(1);
            task.setId(taskId);
            return taskId;
        }
    }

    public static void updateTask(Task task) throws SQLException {
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL UpdateTask(?, ?, ?, ?, ?)}")) {
            stmt.setInt(1, task.getId());
            stmt.setString(2, task.getTitle());
            stmt.setString(3, task.getDescription());
            stmt.setBoolean(4, task.isCompleted());
            stmt.setInt(5, task.getPriority());
            stmt.executeUpdate();
        }
    }

    public static void deleteTask(int taskId) throws SQLException {
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL DeleteTask(?)}")) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        }
    }

    public static List<Task> getTasks(int userId) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL GetTasks(?)}")) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task(
                            rs.getInt("UserId"),
                            rs.getString("Title"),
                            rs.getString("Description"),
                            rs.getInt("Priority")

                    );
                    task.setId(rs.getInt("Id"));
                    task.setCompleted(rs.getBoolean("Completed"));
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    public static User getUser(String username, String password) throws SQLException {
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{CALL dbo.GetUser(?, ?)}")) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(rs.getString("Username"), rs.getString("Password"));
                    user.setId(rs.getInt("Id"));
                    return user;
                }
            }
        }
        return null;
    }

    public static int addUser(User user) throws SQLException {
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{? = CALL AddUser(?, ?)}")) {
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            int userId = stmt.getInt(1);
            user.setId(userId);
            return userId;
        }
    }
}

