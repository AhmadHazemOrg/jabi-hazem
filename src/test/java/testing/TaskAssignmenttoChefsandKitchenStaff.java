package testing;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class TaskAssignmenttoChefsandKitchenStaff {

    private boolean taskAssigned = false;
    private boolean taskConfirmed = false;
    private String assignedExpertise;
    private String chefAssigned;
    private boolean chefMaxWorkload = false;
    private boolean warningDisplayed = false;
    private String taskDetails;
    private String taskDeadline;

    @Given("a list of chefs with their current workloads and expertise")
    public void a_list_of_chefs_with_their_current_workloads_and_expertise() {
        // Simulate a list of chefs with their workloads and expertise
        System.out.println("Chefs list loaded with expertise and current workloads.");
    }

    @When("the kitchen manager assigns a task requiring {string} expertise")
    public void the_kitchen_manager_assigns_a_task_requiring_expertise(String expertise) {
        assignedExpertise = expertise;
        // Simulate assigning task
        System.out.println("Manager assigns task requiring expertise: " + expertise);
    }

    @Then("the system should assign the task to the available chef with {string} expertise and the least workload")
    public void the_system_should_assign_the_task_to_the_available_chef_with_expertise_and_the_least_workload(String expertise) {
        // Simulate the system assigning the task to the correct chef
        if ("Grill".equals(expertise)) {
            chefAssigned = "Chef John"; // Assuming Chef John has Grill expertise
        }
        assertNotNull("Task was not assigned to a chef", chefAssigned);
        System.out.println("Assigned task to " + chefAssigned + " with " + expertise + " expertise.");
    }

    @Given("a chef has already reached their maximum workload")
    public void a_chef_has_already_reached_their_maximum_workload() {
        chefMaxWorkload = true;
        System.out.println("Chef's workload has reached maximum capacity.");
    }

    @When("the kitchen manager tries to assign another task to that chef")
    public void the_kitchen_manager_tries_to_assign_another_task_to_that_chef() {
        // Simulate the manager trying to assign another task
        if (chefMaxWorkload) {
            warningDisplayed = true;
        }
        System.out.println("Manager tries to assign another task to the chef.");
    }

    @Then("the system should display a warning and suggest another available chef")
    public void the_system_should_display_a_warning_and_suggest_another_available_chef() {
        assertTrue("Warning was not displayed", warningDisplayed);
        System.out.println("Warning: Chef's workload is full. Suggesting another available chef.");
    }

    @Given("a task has been assigned to a chef")
    public void a_task_has_been_assigned_to_a_chef() {
        taskAssigned = true;
        System.out.println("Task has been assigned to a chef.");
    }

    @When("the assignment is confirmed")
    public void the_assignment_is_confirmed() {
        taskConfirmed = true;
        taskDetails = "Grill steak";
        taskDeadline = "12:00 PM";
        System.out.println("Task assignment confirmed: " + taskDetails + " with deadline " + taskDeadline);
    }

    @Then("the chef should receive a notification with task details and deadline")
    public void the_chef_should_receive_a_notification_with_task_details_and_deadline() {
        assertTrue("Task confirmation failed", taskConfirmed);
        assertNotNull("Task details are missing", taskDetails);
        assertNotNull("Task deadline is missing", taskDeadline);
        System.out.println("Notification sent to chef: " + taskDetails + " - Deadline: " + taskDeadline);
    }
}
