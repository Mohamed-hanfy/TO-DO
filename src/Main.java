import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class Main {
    private static User currentUser;
    private static Scanner sc = new Scanner(System.in);
    private static PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) throws Exception {

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }


    private static void showMainMenu() throws Exception {
        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Update Task Priority");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = 0;
            try {
                choice = sc.nextInt();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    markTaskAsCompleted();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    updateTaskPriority();
                    break;
                case 6:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void login() throws Exception {
        System.out.print("Enter username: ");
        String username = null;
        String password = null;

        username = sc.nextLine();
        System.out.print("Enter password: ");

        password = sc.nextLine();

        try {
            User user = DatabseAppManger.getUser(username, password);
            if (user != null) {
                currentUser = user;
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void register() throws Exception {
        System.out.print("Enter new username: ");
        String username = null;
        try {
            username = sc.nextLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Enter new password: ");
        String password = sc.nextLine();

        try {
            User newUser = new User(username, password);
            DatabseAppManger.addUser(newUser);
            System.out.println("Registration successful! Please login.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void addTask() throws Exception {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();
        System.out.print("Enter task description (optional): ");
        String description = sc.nextLine();
        System.out.print("Enter task priority (1-5): ");
        int priority = sc.nextInt();


        try {
            Task newTask = new Task(currentUser.getId(), title, description, priority);
            DatabseAppManger.addTask(newTask);
            System.out.println("Task added successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void viewTasks() throws Exception {
        try {
            List<Task> tasks = DatabseAppManger.getTasks(currentUser.getId());
            if (tasks.isEmpty()) {
                System.out.println("No tasks found.");
            } else {
                for (Task task : tasks) {
                    System.out.println(task);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void markTaskAsCompleted() throws Exception {
        viewTasks();
        System.out.print("Enter the ID of the task to mark as completed: ");
        int taskId = sc.nextInt();

        try {
            List<Task> tasks = DatabseAppManger.getTasks(currentUser.getId());
            Task taskToUpdate = tasks.stream()
                    .filter(t -> t.getId() == taskId)
                    .findFirst()
                    .orElse(null);

            if (taskToUpdate != null) {
                taskToUpdate.setCompleted(true);
                DatabseAppManger.updateTask(taskToUpdate);
                System.out.println("Task marked as completed!");
            } else {
                System.out.println("Task not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void deleteTask() throws Exception {
        viewTasks();
        System.out.print("Enter the ID of the task to delete: ");
        int taskId = sc.nextInt();

        try {
            DatabseAppManger.deleteTask(taskId);
            System.out.println("Task deleted successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void updateTaskPriority() throws Exception {
        viewTasks();
        System.out.print("Enter the ID of the task to update priority: ");
        int taskId = sc.nextInt();
        System.out.print("Enter new priority (1-5): ");
        int newPriority = sc.nextInt();

        try {
            List<Task> tasks = DatabseAppManger.getTasks(currentUser.getId());
            Task taskToUpdate = tasks.stream()
                    .filter(t -> t.getId() == taskId)
                    .findFirst()
                    .orElse(null);

            if (taskToUpdate != null) {
                taskToUpdate.setPriority(newPriority);
                DatabseAppManger.updateTask(taskToUpdate);
                System.out.println("Task priority updated!");
            } else {
                System.out.println("Task not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }



    private static void showLoginMenu() throws Exception {
        System.out.println("\n1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }


    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader r) {
            br = new BufferedReader(r);
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }
}

