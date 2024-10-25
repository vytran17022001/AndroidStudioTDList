package com.example.todo_list;

public class ToDoList {
    private String taskName;
    private String taskDescription;
    private String taskTime;

    public ToDoList(String taskDescription, String taskTime, String taskName) {
        this.taskDescription = taskDescription;
        this.taskTime = taskTime;
        this.taskName = taskName;
    }

    public ToDoList() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
