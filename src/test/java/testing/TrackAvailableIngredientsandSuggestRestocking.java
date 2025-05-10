package testing;

import io.cucumber.java.en.*;
import org.example.IngredientInventorySystem;

import java.util.Map;

import static org.junit.Assert.*;

public class TrackAvailableIngredientsandSuggestRestocking {

    private IngredientInventorySystem system = new IngredientInventorySystem();
    private String alertMessage = "";
    private boolean restockSuggested = false;

    @Given("the system is connected to the kitchen inventory")
    public void connect_inventory() {
        system.connectInventory();
        System.out.println("System connected to kitchen inventory.");
    }

    @When("the kitchen manager opens the stock dashboard")
    public void open_dashboard() {
        system.openDashboard();
        System.out.println("Kitchen manager opens the dashboard.");
    }

    @Then("the system should display the current quantity of each ingredient in real time")
    public void display_real_time_inventory() {
        assertTrue(system.isDashboardOpened());
        System.out.println("Inventory:");
        for (Map.Entry<String, Integer> entry : system.getInventory().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " units");
        }
    }

    @Given("an ingredient falls below the minimum stock threshold")
    public void ingredient_below_threshold() {
        system.simulateLowStock("Garlic", 3);
    }

    @When("the threshold is breached")
    public void threshold_breached() {
        alertMessage = system.checkForLowStockAlerts();
    }

    @Then("the system should notify the kitchen manager with a restock alert")
    public void notify_restock_alert() {
        assertFalse(alertMessage.isEmpty());
        System.out.println(alertMessage);
    }

    @Given("the system monitors ingredient usage history")
    public void monitor_usage_history() {
        system.enableUsageMonitoring();
    }

    @When("the weekly usage exceeds average levels")
    public void weekly_usage_exceeds_average() {
        restockSuggested = system.shouldSuggestRestocking(30, 50);
    }

    @Then("the system should suggest restocking the ingredient earlier than usual")
    public void suggest_early_restocking() {
        assertTrue(restockSuggested);
        System.out.println("Usage is higher than average. Suggest early restocking.");
    }
}
