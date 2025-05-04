package testing;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class TrackAvailableIngredientsandSuggestRestocking {

    private Map<String, Integer> inventory = new HashMap<>();
    private boolean dashboardOpened = false;
    private String restockAlert = "";
    private boolean usageMonitored = false;
    private boolean restockSuggestion = false;

    @Given("the system is connected to the kitchen inventory")
    public void the_system_is_connected_to_the_kitchen_inventory() {
        inventory.put("Tomatoes", 50);
        inventory.put("Onions", 20);
        inventory.put("Garlic", 10);
        System.out.println("System connected to kitchen inventory.");
    }

    @When("the kitchen manager opens the stock dashboard")
    public void the_kitchen_manager_opens_the_stock_dashboard() {
        dashboardOpened = true;
        System.out.println("Kitchen manager opens the dashboard.");
    }

    @Then("the system should display the current quantity of each ingredient in real time")
    public void the_system_should_display_the_current_quantity_of_each_ingredient_in_real_time() {
        assertTrue("Dashboard not opened", dashboardOpened);
        System.out.println("Current Inventory:");
        inventory.forEach((ingredient, quantity) ->
                System.out.println(ingredient + ": " + quantity + " units")
        );
    }

    @Given("an ingredient falls below the minimum stock threshold")
    public void an_ingredient_falls_below_the_minimum_stock_threshold() {
        inventory.put("Garlic", 3); // Simulate Garlic going below threshold
        System.out.println("Garlic quantity falls below threshold.");
    }

    @When("the threshold is breached")
    public void the_threshold_is_breached() {
        int threshold = 5;
        if (inventory.get("Garlic") < threshold) {
            restockAlert = "Alert: Garlic stock is low. Please restock!";
        }
    }

    @Then("the system should notify the kitchen manager with a restock alert")
    public void the_system_should_notify_the_kitchen_manager_with_a_restock_alert() {
        assertFalse("No restock alert generated", restockAlert.isEmpty());
        System.out.println(restockAlert);
    }

    @Given("the system monitors ingredient usage history")
    public void the_system_monitors_ingredient_usage_history() {
        usageMonitored = true;
        System.out.println("System is monitoring usage history.");
    }

    @When("the weekly usage exceeds average levels")
    public void the_weekly_usage_exceeds_average_levels() {
        if (usageMonitored) {
            int averageWeeklyUsage = 30;
            int thisWeekUsage = 50;
            if (thisWeekUsage > averageWeeklyUsage) {
                restockSuggestion = true;
            }
        }
    }

    @Then("the system should suggest restocking the ingredient earlier than usual")
    public void the_system_should_suggest_restocking_the_ingredient_earlier_than_usual() {
        assertTrue("No restock suggestion was made", restockSuggestion);
        System.out.println("Usage is higher than average. Suggest early restocking.");
    }
}
