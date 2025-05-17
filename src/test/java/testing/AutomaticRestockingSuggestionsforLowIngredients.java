package testing;

import io.cucumber.java.en.*;
import org.example.IngredientInventoryManager;

import static org.junit.Assert.*;

import java.util.List;

public class AutomaticRestockingSuggestionsforLowIngredients {

    private IngredientInventoryManager manager = new IngredientInventoryManager();
    private boolean suggestionGenerated = false;
    private List<String> prioritizedSuggestions;

    @Given("the stock level of {string} drops below the threshold")
    public void the_stock_level_of_drops_below_the_threshold(String ingredient) {
        manager.setThreshold(ingredient, 20);
        manager.updateStock(ingredient, 15);
    }

    @When("the system detects the low stock level")
    public void the_system_detects_the_low_stock_level() {
        suggestionGenerated = manager.generateSuggestion("Tomatoes");
    }

    @Then("it should generate a restocking suggestion for {string} to the kitchen manager")
    public void it_should_generate_a_restocking_suggestion_for_to_the_kitchen_manager(String ingredient) {
        assertTrue("No suggestion generated", suggestionGenerated);
        System.out.println("Restocking suggestion for " + ingredient);
    }

    @Given("a restocking suggestion for {string} was already generated today")
    public void a_restocking_suggestion_for_was_already_generated_today(String ingredient) {
        manager.setThreshold(ingredient, 20);
        manager.updateStock(ingredient, 10);
        manager.generateSuggestion(ingredient); // already suggested today
    }

    @When("the stock level remains low")
    public void the_stock_level_remains_low() {
        suggestionGenerated = manager.generateSuggestion("Cheese");
    }

    @Then("the system should not generate a duplicate suggestion within the same day")
    public void the_system_should_not_generate_a_duplicate_suggestion_within_the_same_day() {
        assertFalse("Duplicate suggestion generated", suggestionGenerated);
        System.out.println("No duplicate suggestion generated.");
    }

    @Given("multiple ingredients are below their thresholds")
    public void multiple_ingredients_are_below_their_thresholds() {
        manager.setThreshold("Tomatoes", 10);
        manager.updateStock("Tomatoes", 5);
        manager.setUsageRate("Tomatoes", 50);

        manager.setThreshold("Cheese", 10);
        manager.updateStock("Cheese", 8);
        manager.setUsageRate("Cheese", 30);

        manager.setThreshold("Onions", 10);
        manager.updateStock("Onions", 3);
        manager.setUsageRate("Onions", 70);
    }

    @When("generating restocking suggestions")
    public void generating_restocking_suggestions() {
        prioritizedSuggestions = manager.getPrioritizedSuggestions();
    }

    @Then("the system should prioritize ingredients that are used more frequently")
    public void the_system_should_prioritize_ingredients_that_are_used_more_frequently() {
        assertEquals("Onions", prioritizedSuggestions.get(0));
        System.out.println("Prioritized restocking order: " + prioritizedSuggestions);
    }
}
