package testing;

import io.cucumber.java.en.*;
import java.util.*;

public class Meal_Customization_by_Customer {

    List<String> selectedIngredients = new ArrayList<>();
    List<String> savedMeals = new ArrayList<>();
    List<String> glutenFreeOptions = Arrays.asList("rice", "chicken", "grilled vegetables", "gluten-free bread");

    @Given("the customer is on the meal customization page")
    public void the_customer_is_on_the_meal_customization_page() {
        selectedIngredients.clear();
    }

    @When("they select rice, chicken, grilled vegetables, and garlic sauce")
    public void they_select_rice_chicken_grilled_vegetables_and_garlic_sauce() {
        selectedIngredients = Arrays.asList("rice", "chicken", "grilled vegetables", "garlic sauce");
    }

    @Then("the system should display a custom meal summary with the updated price")
    public void the_system_should_display_a_custom_meal_summary_with_the_updated_price() {
        double price = selectedIngredients.size() * 2.5;
        System.out.println("Meal Summary: " + selectedIngredients);
        System.out.println("Total Price: $" + price);
    }

    @Given("the customer selects the {string} dietary filter")
    public void the_customer_selects_the_dietary_filter(String filter) {
        if (filter.equalsIgnoreCase("Gluten-Free")) {
            selectedIngredients = new ArrayList<>(glutenFreeOptions);
        }
    }

    @When("they view the list of available ingredients")
    public void they_view_the_list_of_available_ingredients() {
        System.out.println("Available Ingredients (Filtered): " + selectedIngredients);
    }

    @Then("only gluten-free options should be shown")
    public void only_gluten_free_options_should_be_shown() {
        for (String ingredient : selectedIngredients) {
            if (!glutenFreeOptions.contains(ingredient)) {
                throw new AssertionError("Non-gluten-free ingredient found: " + ingredient);
            }
        }
    }

    @Given("the customer has selected a full set of ingredients")
    public void the_customer_has_selected_a_full_set_of_ingredients() {
        selectedIngredients = Arrays.asList("rice", "chicken", "grilled vegetables", "garlic sauce");
    }

    @When("they click \"Save as Favorite\"")
    public void they_click_save_as_favorite() {
        savedMeals.add(String.join(", ", selectedIngredients));
    }

    @Then("the meal should be stored in their favorites list under their account")
    public void the_meal_should_be_stored_in_their_favorites_list_under_their_account() {
        if (savedMeals.isEmpty()) {
            throw new AssertionError("Meal was not saved to favorites");
        }
    }
}
