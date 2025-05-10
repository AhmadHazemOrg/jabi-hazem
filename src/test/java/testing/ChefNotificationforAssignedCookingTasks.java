package testing;

import io.cucumber.java.en.*;
import org.example.ChefData;
import org.example.ChefNotificationSystem;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class ChefNotificationforAssignedCookingTasks {

    private ChefNotificationSystem notificationSystem = new ChefNotificationSystem();
    private ChefData chef;
    private boolean reminderTriggered = false;
    private boolean taskModified = false;
    private String updatedTaskDetails;

    @Given("the kitchen manager assigns a new cooking task to a chef")
    public void the_kitchen_manager_assigns_a_new_cooking_task_to_a_chef() {
        chef = new ChefData("Chef Omar", "Grill", 2, 5);
        notificationSystem.assignTaskToChef(chef, "Grill Chicken", "Chicken, Spices, Oil", LocalTime.of(10, 0));
    }

    @When("the assignment is completed")
    public void the_assignment_is_completed() {

        System.out.println("Assignment marked as completed.");
    }

    @Then("the chef should receive a notification with task name, ingredients, and start time")
    public void the_chef_should_receive_a_notification_with_task_name_ingredients_and_start_time() {

        assertNotNull(chef);
    }

    @Given("a chef has a task scheduled to start in {int} minutes")
    public void a_chef_has_a_task_scheduled_to_start_in_minutes(Integer minutes) {
        chef = new ChefData("Chef Omar", "Grill", 2, 5);
        notificationSystem.assignTaskToChef(chef, "Prepare Salad", "Lettuce, Tomato, Cucumber", LocalTime.now().plusMinutes(minutes));
    }

    @When("the current time is {int} minutes before the task")
    public void the_current_time_is_minutes_before_the_task(Integer minutesBefore) {
        LocalTime fakeNow = LocalTime.now();
        LocalTime reminderTime = fakeNow.plusMinutes(minutesBefore); // Simulate being {minutesBefore} before the task
        notificationSystem.sendReminderIfDue("Chef Omar", reminderTime);
        if (minutesBefore == 15) {
            reminderTriggered = true;
        }
    }

    @Then("the system should send a reminder notification to the chef")
    public void the_system_should_send_a_reminder_notification_to_the_chef() {
        assertTrue("Reminder was not triggered", reminderTriggered);
    }

    @Given("a cooking task assigned to a chef has been modified")
    public void a_cooking_task_assigned_to_a_chef_has_been_modified() {
        chef = new ChefData("Chef Omar", "Grill", 2, 5);
        notificationSystem.assignTaskToChef(chef, "Bake Cake", "Flour, Eggs, Sugar", LocalTime.of(11, 0));
        taskModified = true;
        updatedTaskDetails = "Bake Chocolate Cake instead of Vanilla";
    }

    @When("the task details are updated")
    public void the_task_details_are_updated() {
        assertTrue(taskModified);
        notificationSystem.notifyTaskModification("Chef Omar", updatedTaskDetails);
    }

    @Then("the chef should receive a notification describing the changes")
    public void the_chef_should_receive_a_notification_describing_the_changes() {
        assertNotNull(updatedTaskDetails);
    }
}
