package testing;

import io.cucumber.java.en.*;
import org.example.*;


import java.util.List;

import static org.junit.Assert.*;

public class ChefViewPreferences {
    private final Chef chef = new Chef();
    private final Customer customer = new Customer();
    String actualPreferences;
    private Meal meal = new Meal();
    String actualAllergies;
    String errorMessage;

    @Given("Given the Chef {string} with password {string} is logged in")
    public void given_the_chef_with_password_is_logged_in(String username, String password) {
        chef.setUsername(username);
        chef.setPassword(password);
        assertTrue(chef.login());
    }

    private void loginCustomer(String username) {
        customer.setUsername(username);
        customer.setPassword("2004");
        assertTrue(customer.login());
    }

    @Given("a customer {string} has dietary preferences {string} and allergies {string}")
    public void a_customer_has_dietary_preferences_and_allergies(String username, String preferences, String allergies) {
        loginCustomer(username);
        customer.setDietaryPreferences(preferences);
        customer.setAllergies(allergies);
    }

    @When("the chef views the dietary preferences of customer {string}")
    public void the_chef_views_the_dietary_preferences_of_customer(String username) {
        loginCustomer(username);
        actualPreferences = customer.getDietaryPreferences();
        actualAllergies = customer.getAllergies();
    }

    @Then("the system should display {string} as the dietary preference")
    public void the_system_should_display_as_the_dietary_preference(String expectedPreference) {
        assertEquals(expectedPreference, actualPreferences);
    }

    @Then("display {string} as allergies")
    public void display_as_allergies(String expectedAllergies) {
        assertEquals(expectedAllergies, actualAllergies);
    }


    @When("the chef creates a meal named {string} with ingredients {string} and dietary tags {string}")
    public void the_chef_creates_a_meal_named_with_ingredients_and_dietary_tags(String mealName, String ingredients, String dietaryTags) {

        mealCreator(mealName, ingredients, dietaryTags);

    }


    @Then("the meal should match the customer's dietary preference")
    public void the_meal_should_match_the_customer_s_dietary_preference() {
        List<String> customerPreferences = List.of(customer.getDietaryPreferences().split(",\\s*"));

        System.out.println("Meal dietary tags: " + meal.getDietaryTags());

        assertTrue("Meal should match all customer dietary preferences",
                meal.getDietaryTags().containsAll(customerPreferences));
    }


    @Then("the meal should not contain any allergens")
    public void the_meal_should_not_contain_any_allergens() {
        String[] allergens = customer.getAllergies().split(",\\s*");


        for (String allergen : allergens) {
            assertFalse(meal.getIngredients().contains(allergen));
        }
    }


    private void mealCreator(String mealName, String ingredients, String dietaryTags) {
        List<String> ingredientList = List.of(ingredients.split(",\\s*"));
        List<String> dietaryTagList = List.of(dietaryTags.split(",\\s*"));
        meal.setName(mealName);
        meal.setIngredients(ingredientList);
        meal.setDietaryTags(dietaryTagList);
    }


    @When("the chef attempts to create a meal named {string} with possible allergen ingredients {string} and tags {string}")
    public void theChefAttemptsToCreateAMealNamedWithPossibleAllergenIngredientsAndTags(String mealName, String ingredients, String dietaryTags) {
        mealCreator(mealName, ingredients, dietaryTags);
    }
    @Then("the system should warn about allergens in the meal")
    public void theSystemShouldWarnAboutAllergensInTheMeal() {
        String[] allergens = customer.getAllergies().split(",\\s*");
        List<String> ingredients = meal.getIngredients();

        boolean allergenFound = false;

        for (String allergen : allergens) {
            for (String ingredient : ingredients) {

                if (ingredient.toLowerCase().contains(allergen.toLowerCase()) || allergen.toLowerCase().contains(ingredient.toLowerCase())) {
                    errorMessage = "Warning: Allergen found - " + allergen;
                    System.out.println(errorMessage);
                    allergenFound = true;
                }
            }
        }

        assertTrue("Expected allergen warning, but none was triggered.", allergenFound);
    }

}
