package testing;

import io.cucumber.java.en.*;
import org.example.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class Meal_Customization_by_Customer {

    private Meal currentMeal;
    private List<String> visibleIngredients = new ArrayList<>();
    private List<String> savedMeals = new ArrayList<>();

    @Given("the customer is logged in and on the meal customization page")
    public void the_customer_is_logged_in_and_on_the_meal_customization_page() {
        currentMeal = new Meal("Custom Meal");
        System.out.println("Customer navigated to the customization page.");
    }

    @Given("the available ingredients include: rice, chicken, grilled vegetables, garlic sauce, pasta, beef, soy sauce")
    public void the_available_ingredients_are_loaded() {
        visibleIngredients = new ArrayList<>(MealData.ALL_INGREDIENTS);
        System.out.println("Ingredients loaded: " + visibleIngredients);
    }

    @Given("the available dietary filters include: Gluten-Free, Vegan, Dairy-Free")
    public void dietary_filters_are_loaded() {
        System.out.println("Dietary filters loaded: Gluten-Free, Vegan, Dairy-Free");
    }

    @When("the customer selects: rice, chicken, grilled vegetables, garlic sauce")
    public void customer_selects_ingredients_1() {
        currentMeal.setIngredients(Arrays.asList("rice", "chicken", "grilled vegetables", "garlic sauce"));
    }

    @When("the customer selects: rice, chicken, garlic sauce")
    public void customer_selects_ingredients_2() {
        currentMeal.setIngredients(Arrays.asList("rice", "chicken", "garlic sauce"));
    }

    @Then("the custom meal summary should display the selected ingredients")
    public void display_meal_summary() {
        System.out.println("Custom Meal Summary: " + currentMeal.getIngredients());
        assertFalse(currentMeal.getIngredients().isEmpty(), "No ingredients selected");
    }

    @Then("the total price should be calculated and displayed as ${double}")
    public void calculate_price(Double expectedPrice) {
        double total = currentMeal.getIngredients().stream()
                .mapToDouble(i -> MealData.INGREDIENT_PRICES.getOrDefault(i, 0.0))
                .sum();
        System.out.printf("Total Price: $%.2f%n", total);
        assertEquals(expectedPrice, total, 0.01);
    }

    @When("the customer applies the {string} dietary filter")
    public void apply_dietary_filter(String filter) {
        if (filter.equalsIgnoreCase("Gluten-Free")) {
            visibleIngredients = new ArrayList<>(MealData.GLUTEN_FREE_INGREDIENTS);
        }
    }

    @Then("the ingredient list should exclude: pasta, soy sauce")
    public void ingredient_list_excludes_gluten_items() {
        assertFalse(visibleIngredients.contains("pasta"), "Pasta should not be visible");
        assertFalse(visibleIngredients.contains("soy sauce"), "Soy sauce should not be visible");
    }

    @Then("only gluten-free ingredients should be visible")
    public void only_gluten_free_visible() {
        for (String ing : visibleIngredients) {
            assertTrue(MealData.GLUTEN_FREE_INGREDIENTS.contains(ing), "Non-gluten-free ingredient: " + ing);
        }
    }

    @When("clicks {string}")
    public void click_save_as_favorite(String button) {
        if (button.equalsIgnoreCase("Save as Favorite")) {
            savedMeals.add(String.join(", ", currentMeal.getIngredients()));
        }
    }

    @Then("the meal should appear in the customer's saved meals list")
    public void meal_should_appear_in_saved_list() {
        assertFalse(savedMeals.isEmpty(), "Saved meals list should not be empty");
        System.out.println("Saved meals: " + savedMeals);
    }

    @Then("the name {string} should be shown in favorites")
    public void meal_name_should_be_in_favorites(String expectedName) {
        assertTrue(savedMeals.contains(expectedName), "Meal not found in favorites: " + expectedName);
    }
}
