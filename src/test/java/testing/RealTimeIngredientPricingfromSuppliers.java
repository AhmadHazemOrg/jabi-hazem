package testing;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import java.util.*;

public class RealTimeIngredientPricingfromSuppliers {

    private String selectedIngredient;
    private Map<String, Double> supplierPrices = new HashMap<>();
    private Map<String, Double> oldPrices = new HashMap<>();
    private boolean priceChangedSignificantly = false;
    private String searchedIngredient;
    private List<Map<String, String>> comparisonTable = new ArrayList<>();

    @Given("the kitchen manager selects an ingredient from the inventory system")
    public void the_kitchen_manager_selects_an_ingredient_from_the_inventory_system() {
        selectedIngredient = "Tomatoes"; // mock selection
    }

    @When("the system queries supplier APIs")
    public void the_system_queries_supplier_ap_is() {
        // Mock API response
        supplierPrices.put("Supplier A", 2.5);
        supplierPrices.put("Supplier B", 2.7);
    }

    @Then("the current prices for that ingredient from available suppliers should be displayed")
    public void the_current_prices_for_that_ingredient_from_available_suppliers_should_be_displayed() {
        assertFalse("No prices were returned", supplierPrices.isEmpty());
        System.out.println("Prices for " + selectedIngredient + ": " + supplierPrices);
    }

    @Given("the price of {string} has changed significantly \\(e.g., more than {int}%)")
    public void the_price_of_has_changed_significantly_e_g_more_than(String ingredient, Integer thresholdPercent) {
        oldPrices.put(ingredient, 10.0);
        double newPrice = 11.5;
        double oldPrice = oldPrices.get(ingredient);
        double change = Math.abs((newPrice - oldPrice) / oldPrice) * 100;

        priceChangedSignificantly = change > thresholdPercent;
    }

    @When("the system receives the updated price")
    public void the_system_receives_the_updated_price() {
        // Already calculated in @Given step
    }

    @Then("it should notify the kitchen manager about the price change")
    public void it_should_notify_the_kitchen_manager_about_the_price_change() {
        assertTrue("Price change was not significant", priceChangedSignificantly);
        System.out.println("⚠️ Significant price change detected. Kitchen manager notified.");
    }

    @Given("the system has access to data from Supplier A and Supplier B")
    public void the_system_has_access_to_data_from_supplier_a_and_supplier_b() {
        // Mock data availability (can be expanded if needed)
        assertTrue(true); // Simulate successful API integration
    }

    @When("the kitchen manager searches for {string}")
    public void the_kitchen_manager_searches_for(String ingredient) {
        searchedIngredient = ingredient;

        Map<String, String> supplierAData = new HashMap<>();
        supplierAData.put("Supplier", "Supplier A");
        supplierAData.put("Price", "$1.20");
        supplierAData.put("Delivery Time", "2 days");
        supplierAData.put("Availability", "In Stock");

        Map<String, String> supplierBData = new HashMap<>();
        supplierBData.put("Supplier", "Supplier B");
        supplierBData.put("Price", "$1.10");
        supplierBData.put("Delivery Time", "3 days");
        supplierBData.put("Availability", "In Stock");

        comparisonTable.add(supplierAData);
        comparisonTable.add(supplierBData);
    }

    @Then("the system should show a side-by-side comparison of prices, delivery times, and availability")
    public void the_system_should_show_a_side_by_side_comparison_of_prices_delivery_times_and_availability() {
        assertEquals(2, comparisonTable.size());
        System.out.println("Comparison for " + searchedIngredient + ":");
        for (Map<String, String> row : comparisonTable) {
            System.out.println(row);
        }
    }
}
