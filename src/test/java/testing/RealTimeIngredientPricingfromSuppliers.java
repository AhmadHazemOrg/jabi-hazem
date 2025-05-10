package testing;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.RealTimePricingManager;
import org.junit.Assert;

public class RealTimeIngredientPricingfromSuppliers {

    private final RealTimePricingManager pricingManager = new RealTimePricingManager();
    private boolean significantChangeDetected;


    @Given("the kitchen manager selects an ingredient from the inventory system")
    public void the_kitchen_manager_selects_an_ingredient_from_the_inventory_system() {
        pricingManager.selectIngredient("Tomato");
        System.out.println("Ingredient selected: Tomato");
    }


    @Given("the system has access to data from Supplier A and Supplier B")
    public void the_system_has_access_to_data_from_supplier_a_and_supplier_b() {
        pricingManager.querySuppliers();
        System.out.println("Data from Supplier A and B fetched.");
    }


    @When("the system queries supplier APIs")
    public void the_system_queries_supplier_ap_is() {
        pricingManager.querySuppliers();
        System.out.println("Supplier APIs queried.");
    }


    @When("the system receives the updated price")
    public void the_system_receives_the_updated_price() {
        pricingManager.querySuppliers();
        System.out.println("Updated price received from suppliers.");
    }


    @Then("the current prices for that ingredient from available suppliers should be displayed")
    public void the_current_prices_for_that_ingredient_from_available_suppliers_should_be_displayed() {
        Assert.assertNotNull("Supplier prices should not be null", pricingManager.getCurrentPrices());
        System.out.println("Displaying current prices: " + pricingManager.getCurrentPrices());
    }

    @When("the kitchen manager searches for {string}")
    public void the_kitchen_manager_searches_for(String ingredient) {
        pricingManager.selectIngredient(ingredient);
        System.out.println("Searching for ingredient: " + ingredient);
    }


    @Then("the system should show a side-by-side comparison of prices, delivery times, and availability")
    public void the_system_should_show_a_side_by_side_comparison_of_prices_delivery_times_and_availability() {
        pricingManager.getComparisonTable(); // Use the correct method here
        System.out.println("Comparing suppliers for Tomato...");
    }

    @Given("the price of {string} has changed significantly \\(e.g., more than {int}%)")
    public void the_price_of_has_changed_significantly(String ingredient, Integer thresholdPercent) {
        pricingManager.selectIngredient(ingredient);


        double oldPrice = 10.0;
        double newPrice = 11.5;


        significantChangeDetected = pricingManager.hasSignificantPriceChange(oldPrice, thresholdPercent);
        significantChangeDetected = pricingManager.hasSignificantPriceChange(newPrice, thresholdPercent);

        System.out.printf("Old Price: %.2f, New Price: %.2f, Threshold: %d%%, Significant Change: %b%n",
                oldPrice, newPrice, thresholdPercent, significantChangeDetected);
    }

    @When("the system compares the latest prices from suppliers")
    public void the_system_compares_the_latest_prices_from_suppliers() {
        pricingManager.querySuppliers();
        System.out.println("Supplier prices: " + pricingManager.getCurrentPrices());
    }

    @Then("it should notify the kitchen manager about the price change")
    public void it_should_notify_the_kitchen_manager_about_the_price_change() {
        Assert.assertTrue("Expected significant price change, but none detected", significantChangeDetected);
        System.out.println("✅ Notification sent to kitchen manager about significant price change.");
    }
}
