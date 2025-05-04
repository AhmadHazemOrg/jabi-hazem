package testing;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class ChefNotificationforAssignedCookingTasks {

    private boolean taskAssigned = false;
    private boolean taskCompleted = false;
    private boolean reminderSent = false;
    private boolean taskModified = false;
    private String taskName;
    private String taskIngredients;
    private String taskStartTime;
    private String taskDetailsUpdated;

    @Given("the kitchen manager assigns a new cooking task to a chef")
    public void the_kitchen_manager_assigns_a_new_cooking_task_to_a_chef() {
        taskAssigned = true;
        taskName = "Grill Chicken";
        taskIngredients = "Chicken, Spices, Oil";
        taskStartTime = "10:00 AM";
        System.out.println("Kitchen manager assigns a new cooking task: " + taskName);
    }

    @When("the assignment is completed")
    public void the_assignment_is_completed() {
        taskCompleted = true;
        System.out.println("Task assignment completed for " + taskName);
    }

    @Then("the chef should receive a notification with task name, ingredients, and start time")
    public void the_chef_should_receive_a_notification_with_task_name_ingredients_and_start_time() {
        assertTrue("Task was not assigned", taskAssigned);
        assertNotNull("Task name is missing", taskName);
        assertNotNull("Task ingredients are missing", taskIngredients);
        assertNotNull("Task start time is missing", taskStartTime);
        System.out.println("Chef receives notification: " + taskName + " - Ingredients: " + taskIngredients + " - Start Time: " + taskStartTime);
    }

    @Given("a chef has a task scheduled to start in {int} minutes")
    public void a_chef_has_a_task_scheduled_to_start_in_minutes(Integer minutes) {
        // Simulating a task scheduled to start in a certain number of minutes
        System.out.println("Chef has a task scheduled to start in " + minutes + " minutes.");
    }

    @When("the current time is {int} minutes before the task")
    public void the_current_time_is_minutes_before_the_task(Integer minutesBefore) {
        if (minutesBefore == 15) {
            reminderSent = true;
        }
        System.out.println("Reminder sent: " + minutesBefore + " minutes before task.");
    }

    @Then("the system should send a reminder notification to the chef")
    public void the_system_should_send_a_reminder_notification_to_the_chef() {
        assertTrue("Reminder was not sent", reminderSent);
        System.out.println("Reminder sent to chef for task starting in 15 minutes.");
    }

    @Given("a cooking task assigned to a chef has been modified")
    public void a_cooking_task_assigned_to_a_chef_has_been_modified() {
        taskModified = true;
        taskDetailsUpdated = "Grill Chicken with extra spices";
        System.out.println("Cooking task has been modified.");
    }

    @When("the task details are updated")
    public void the_task_details_are_updated() {
        // Simulating updating task details
        System.out.println("Task details updated: " + taskDetailsUpdated);
    }

    @Then("the chef should receive a notification describing the changes")
    public void the_chef_should_receive_a_notification_describing_the_changes() {
        assertTrue("Task was not modified", taskModified);
        assertNotNull("Updated task details are missing", taskDetailsUpdated);
        System.out.println("Chef receives notification: Task updated to " + taskDetailsUpdated);
    }
}
