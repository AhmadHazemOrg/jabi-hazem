package testing;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Customer;
import org.example.Meal;
import org.example.MealRecommender;

import java.util.List;

import static org.junit.Assert.*;

public class InputPreferences {

    private final Customer customer = new Customer();
    private final MealRecommender recommender = new MealRecommender();
    private List<Meal> recommendedMeals;

    @Given("the Customer {string} and {string} is logged in")
    public void the_customer_and_password_is_logged_in(String username, String password) {
        customer.setUsername(username);
        customer.setPassword(password);
        assertTrue("Login failed for user: " + username, customer.login());
    }

    @When("the Customer sets dietary preferences to {string}")
    public void the_customer_sets_dietary_preferences_to(String preferences) {
        customer.setDietaryPreferences(preferences);
    }

    @When("the Customer sets allergies to {string}")
    public void the_customer_sets_allergies_to(String allergies) {
        customer.setAllergies(allergies);
    }

    @Then("the system should store the preferences and allergies successfully")
    public void the_system_should_store_the_preferences_and_allergies_successfully() {
        assertNotNull("Dietary preferences should not be null", customer.getDietaryPreferences());
        assertNotNull("Allergies should not be null", customer.getAllergies());
    }

    @Given("the Customer has preferences {string} and allergies {string}")
    public void the_customer_has_preferences_and_allergies(String preferences, String allergies) {
        customer.setDietaryPreferences(preferences);
        customer.setAllergies(allergies);
    }

    @When("the system recommends meals")
    public void the_system_recommends_meals() {
        recommendedMeals = recommender.recommendMeals(customer);

        assertNotNull("Recommended meals list should not be null", recommendedMeals);



    }


    @Then("should match {string}")
    public void should_match(String preference) {
        for (Meal meal : recommendedMeals) {
            assertTrue("Meal '" + meal.getName() + "' does not match dietary preference: " + preference,
                    meal.getDietaryTags().contains(preference));
        }
    }

    @Then("the system should exclude meals that contain {string}")
    public void the_system_should_exclude_meals_that_contain(String allergen) {
        for (Meal meal : recommendedMeals) {
            assertFalse("Meal '" + meal.getName() + "' should not contain allergen: " + allergen,
                    meal.getIngredients().contains(allergen));
        }
    }
}
