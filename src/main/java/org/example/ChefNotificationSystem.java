package org.example;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ChefNotificationSystem {

    private Map<String, TaskInfo> chefTasks = new HashMap<>();

    public void assignTaskToChef(ChefData chef, String taskName, String ingredients, LocalTime startTime) {
        chefTasks.put(chef.getName(), new TaskInfo(taskName, ingredients, startTime));
        System.out.println("Notification sent to " + chef.getName() + ": Task - " + taskName + ", Ingredients - " + ingredients + ", Start Time - " + startTime);
    }

    public void sendReminderIfDue(String chefName, LocalTime currentTime) {
        TaskInfo task = chefTasks.get(chefName);
        if (task != null) {
            long minutesBefore = java.time.Duration.between(currentTime, task.startTime).toMinutes();
            if (minutesBefore == 15) {
                System.out.println("Reminder to " + chefName + ": Your task '" + task.taskName + "' starts in 15 minutes.");
            }
        }
    }

    public void notifyTaskModification(String chefName, String updatedTaskDetails) {
        System.out.println("Notification to " + chefName + ": Task updated -> " + updatedTaskDetails);
    }

    static class TaskInfo {
        String taskName;
        String ingredients;
        LocalTime startTime;

        public TaskInfo(String taskName, String ingredients, LocalTime startTime) {
            this.taskName = taskName;
            this.ingredients = ingredients;
            this.startTime = startTime;
        }
    }
}
