package com.example.todo_list;

public class ToDoList {
   private  String id;
    private String taskName;
    private String taskDescription;
    private String taskTime;

    public ToDoList(String id, String taskDescription, String taskTime, String taskName) {
        this.id = id;
        this.taskDescription = taskDescription;
        this.taskTime = taskTime;
        this.taskName = taskName;
    }

    public ToDoList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
