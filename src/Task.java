public class Task {
    private int id;
    private int userId;
    private String title;
    private String description;
    private boolean completed;
    private int priority;


    public Task(int userId, String title, String description, int priority) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.completed = false;
        this.priority = priority;

    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }



    @Override
    public String toString() {
        return String.format("[%s] (Id: %d) %s (Priority: %d) - %s",
                completed ? "X" : "O",id, title, priority, description);
    }
}

