package org.example;

import java.util.ArrayList;
import java.util.List;

public class ChefData {
    private String name;
    private String expertise;
    private int currentTasks;
    private int maxTasks;
    private List<String> taskList;

    public ChefData(String name, String expertise, int currentTasks, int maxTasks) {
        this.name = name;
        this.expertise = expertise;
        this.currentTasks = currentTasks;
        this.maxTasks = maxTasks;
        this.taskList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getExpertise() {
        return expertise;
    }

    public int getCurrentTasks() {
        return currentTasks;
    }

    public int getMaxTasks() {
        return maxTasks;
    }

    public boolean assignTask(String taskName) {
        if (isAvailable()) {
            currentTasks++;
            taskList.add(taskName);
            System.out.println("Task assigned to " + name + ": " + taskName);
            return true;
        } else {
            System.out.println("Chef " + name + " is overloaded.");
            return false;
        }
    }



    public boolean isAvailable() {
        return currentTasks < maxTasks;
    }



    @Override
    public String toString() {
        return "Chef: " + name + ", Expertise: " + expertise + ", Current Tasks: " + currentTasks + "/" + maxTasks;
    }
}
