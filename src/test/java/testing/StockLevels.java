package testing;

import io.cucumber.java.en.*;
import org.example.*;

import java.util.*;
import static org.junit.Assert.*;

public class StockLevels{

    private Map<String, Ingredient> ingredientMap = new HashMap<>();
    private List<String> lowStockAlerts = new ArrayList<>();

    @Given("the ingredient {string} has a stock level of {int}")
    public void the_ingredient_has_a_stock_level_of(String ingredient, int stockLevel) {

        ingredientMap.putIfAbsent(ingredient, new Ingredient(ingredient, stockLevel, 0));
    }

    @And("the minimum threshold for {string} is {int}")
    public void the_minimum_threshold_for_is(String ingredient, int threshold) {
        Ingredient ing = ingredientMap.get(ingredient);
        ingredientMap.put(ingredient, new Ingredient(ing.getName(), ing.getStockLevel(), threshold));
    }

    @Given("the following ingredient stock levels and thresholds:")
    public void the_following_ingredient_stock_levels_and_thresholds(io.cucumber.datatable.DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps()) {
            String name = row.get("Ingredient");
            int stock = Integer.parseInt(row.get("Stock Level"));
            int threshold = Integer.parseInt(row.get("Threshold"));
            ingredientMap.put(name, new Ingredient(name, stock, threshold));
        }
    }

    @When("the system checks stock levels")
    public void the_system_checks_stock_levels() {
        lowStockAlerts.clear();
        for (Ingredient ingredient : ingredientMap.values()) {
            if (ingredient.isLowStock()) {
                lowStockAlerts.add(ingredient.getName());
            }
        }
    }

    @Then("the kitchen manager should receive a low-stock alert for {string}")
    public void the_kitchen_manager_should_receive_a_low_stock_alert_for(String ingredient) {
        assertTrue("Expected low-stock alert for " + ingredient, lowStockAlerts.contains(ingredient));
    }

    @Then("no low-stock alert should be sent for {string}")
    public void no_low_stock_alert_should_be_sent_for(String ingredient) {
        assertFalse("Did not expect low-stock alert for " + ingredient, lowStockAlerts.contains(ingredient));
    }

    @Then("the kitchen manager should receive low-stock alerts for:")
    public void the_kitchen_manager_should_receive_low_stock_alerts_for(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> row : rows) {
            String expected = row.get("Ingredient");
            assertTrue("Expected alert for " + expected, lowStockAlerts.contains(expected));
        }
    }
    @Then("no alert should be sent for {string}")
    public void no_alert_should_be_sent_for(String ingredient) {
        assertFalse("Did not expect alert for " + ingredient, lowStockAlerts.contains(ingredient));
    }
}

