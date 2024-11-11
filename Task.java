package com.example.todoapp;

public class Task {
    private int id;
    private String title;
    private boolean isCompleted;

    // Constructor for creating a task with an id, title, and completion status
    public Task(int id, String title, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    // Constructor for creating a task with just a title (new tasks will not be completed by default)
    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    // Getter for the task id
    public int getId() {
        return id;
    }

    // Setter for the task id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for the task title
    public String getTitle() {
        return title;
    }

    // Setter for the task title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for checking if the task is completed
    public boolean isCompleted() {
        return isCompleted;
    }

    // Setter for the task completion status
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
