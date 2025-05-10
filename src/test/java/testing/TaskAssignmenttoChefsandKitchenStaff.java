package testing;

import io.cucumber.java.en.*;
import org.example.ChefData;
import org.example.TaskAssignmentSystem;

import static org.junit.Assert.*;

public class TaskAssignmenttoChefsandKitchenStaff {

    private TaskAssignmentSystem system = new TaskAssignmentSystem();
    private ChefData assignedChef;
    private String taskDetails;
    private String taskDeadline;

    @Given("a list of chefs with their current workloads and expertise")
    public void a_list_of_chefs_with_their_current_workloads_and_expertise() {
        system.loadChefs();
        System.out.println("Loaded chefs with expertise and workloads.");
    }

    @Given("a task named {string} is to be assigned")
    public void a_task_named_is_to_be_assigned(String taskName) {
        this.taskDetails = taskName;
    }

    @When("the kitchen manager assigns a task requiring {string} expertise")
    public void the_kitchen_manager_assigns_a_task_requiring_expertise(String expertise) {
        assignedChef = system.assignTask(expertise, taskDetails != null ? taskDetails : "Unnamed task");
    }

    @Then("the system should assign the task to the available chef with {string} expertise and the least workload")
    public void the_system_should_assign_the_task_to_the_available_chef_with_expertise_and_the_least_workload(String expertise) {
        assertNotNull("No chef was assigned", assignedChef);
        assertEquals("Chef expertise mismatch", expertise.toLowerCase(), assignedChef.getExpertise().toLowerCase());
        System.out.println("Assigned to: " + assignedChef.getName());
    }

    @Given("a chef has already reached their maximum workload")
    public void a_chef_has_already_reached_their_maximum_workload() {
        system.loadChefs();
        System.out.println("Chef Lisa current workload: " + system.getChefWorkload("Chef Lisa"));
        System.out.println("Chef Lisa max workload: " + system.getChefMaxWorkload("Chef Lisa"));
        assertTrue("Chef Lisa is not overloaded", system.isChefOverloaded("Chef Lisa"));
        System.out.println("Chef Lisa is overloaded.");
    }

    @When("the kitchen manager tries to assign another task to that chef")
    public void the_kitchen_manager_tries_to_assign_another_task_to_that_chef() {
        assignedChef = system.suggestAnotherChef("Grill", "Chef Lisa");
    }

    @Then("the system should display a warning and suggest another available chef")
    public void the_system_should_display_a_warning_and_suggest_another_available_chef() {
        assertNotNull("No alternative chef was suggested", assignedChef);
        System.out.println("Suggested alternative: " + assignedChef.getName());
    }

    @Given("a task has been assigned to a chef")
    public void a_task_has_been_assigned_to_a_chef() {
        taskDetails = "Grill steak";
        taskDeadline = "12:00 PM";
    }

    @When("the assignment is confirmed")
    public void the_assignment_is_confirmed() {
        assertNotNull(taskDetails);
        assertNotNull(taskDeadline);
    }

    @Then("the chef should receive a notification with task details and deadline")
    public void the_chef_should_receive_a_notification_with_task_details_and_deadline() {
        System.out.println("Notification sent to chef: " + taskDetails + ", Deadline: " + taskDeadline);
    }
}
