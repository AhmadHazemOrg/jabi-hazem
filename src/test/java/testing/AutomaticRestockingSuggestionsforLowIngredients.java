package testing;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.*;

public class AutomaticRestockingSuggestionsforLowIngredients {

    private Map<String, Integer> stockLevels = new HashMap<>();
    private Map<String, Integer> thresholds = new HashMap<>();
    private Map<String, LocalDate> lastSuggestionDate = new HashMap<>();
    private List<String> suggestions = new ArrayList<>();
    private Map<String, Integer> usageRates = new HashMap<>();
    private List<String> prioritizedSuggestions = new ArrayList<>();

    @Given("the stock level of {string} drops below the threshold")
    public void the_stock_level_of_drops_below_the_threshold(String ingredient) {
        thresholds.put(ingredient, 20);
        stockLevels.put(ingredient, 15); // simulate low stock
    }

    @When("the system detects the low stock level")
    public void the_system_detects_the_low_stock_level() {
        for (String ingredient : stockLevels.keySet()) {
            if (stockLevels.get(ingredient) < thresholds.getOrDefault(ingredient, Integer.MAX_VALUE)) {
                suggestions.add(ingredient);
            }
        }
    }

    @Then("it should generate a restocking suggestion for {string} to the kitchen manager")
    public void it_should_generate_a_restocking_suggestion_for_to_the_kitchen_manager(String ingredient) {
        assertTrue("No restocking suggestion generated for " + ingredient, suggestions.contains(ingredient));
        System.out.println("Restocking suggestion generated for " + ingredient);
    }

    @Given("a restocking suggestion for {string} was already generated today")
    public void a_restocking_suggestion_for_was_already_generated_today(String ingredient) {
        lastSuggestionDate.put(ingredient, LocalDate.now());
        stockLevels.put(ingredient, 10); // still low
        thresholds.put(ingredient, 20);
    }

    @When("the stock level remains low")
    public void the_stock_level_remains_low() {
        for (String ingredient : stockLevels.keySet()) {
            if (stockLevels.get(ingredient) < thresholds.get(ingredient)) {
                LocalDate lastSuggested = lastSuggestionDate.get(ingredient);
                if (lastSuggested == null || !lastSuggested.equals(LocalDate.now())) {
                    suggestions.add(ingredient);
                    lastSuggestionDate.put(ingredient, LocalDate.now());
                }
            }
        }
    }

    @Then("the system should not generate a duplicate suggestion within the same day")
    public void the_system_should_not_generate_a_duplicate_suggestion_within_the_same_day() {
        assertTrue("No new suggestion should be generated", suggestions.isEmpty());
        System.out.println("Duplicate restocking suggestion was prevented.");
    }

    @Given("multiple ingredients are below their thresholds")
    public void multiple_ingredients_are_below_their_thresholds() {
        stockLevels.put("Tomatoes", 5);
        stockLevels.put("Cheese", 8);
        stockLevels.put("Onions", 3);

        thresholds.put("Tomatoes", 10);
        thresholds.put("Cheese", 10);
        thresholds.put("Onions", 10);

        usageRates.put("Tomatoes", 50);
        usageRates.put("Cheese", 30);
        usageRates.put("Onions", 70);
    }

    @When("generating restocking suggestions")
    public void generating_restocking_suggestions() {
        List<String> lowIngredients = new ArrayList<>();
        for (String ingredient : stockLevels.keySet()) {
            if (stockLevels.get(ingredient) < thresholds.get(ingredient)) {
                lowIngredients.add(ingredient);
            }
        }

        // Prioritize by usage rate
        lowIngredients.sort((a, b) -> usageRates.get(b) - usageRates.get(a));
        prioritizedSuggestions.addAll(lowIngredients);
    }

    @Then("the system should prioritize ingredients that are used more frequently")
    public void the_system_should_prioritize_ingredients_that_are_used_more_frequently() {
        assertEquals("Onions", prioritizedSuggestions.get(0)); // highest usage
        System.out.println("Prioritized restocking order: " + prioritizedSuggestions);
    }
}
