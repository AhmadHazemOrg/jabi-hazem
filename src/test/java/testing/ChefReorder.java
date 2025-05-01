package testing;

import io.cucumber.java.en.*;
import org.example.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ChefReorder {

    private final Chef chef = new Chef();
    private final Customer customer = new Customer();
    private final List<Meal> customerOrderHistory = new ArrayList<>();
    private List<Meal> suggestedMealPlan;
    private final MealRecommender recommender = new MealRecommender();
    private List<Meal> displayedMeals;

    @Given("the Chef {string} and password {string} is logged in")
    public void the_chef_and_password_is_logged_in(String username, String password) {
        chef.setUsername(username);
        chef.setPassword(password);
        assertTrue("Chef login failed", chef.login());
    }

    @Given("the Customer {string} has previously ordered {string} with {string}")
    public void the_customer_has_previously_ordered(String customerName, String mealName,String ingredients) {

        customer.setUsername(customerName);
        Meal meal = new Meal(mealName);
        List<String> ingredientList = Arrays.asList(ingredients.split(","));
        meal.setIngredients(ingredientList);
        customer.addPastOrder(meal);
        customerOrderHistory.add(meal);


    }

    @When("the Chef views the order history for {string}")
    public void the_chef_views_the_order_history_for(String customerName) {
        customer.setUsername(customerName);
        displayedMeals = customer.getPastOrders();

    }

    @Then("the system should display the past meals including {string}")
    public void the_system_should_display_the_past_meals_including(String mealName) {
        boolean found = displayedMeals.stream().anyMatch(meal -> meal.getName().equals(mealName));
        assertTrue("Expected meal not found in past orders", found);
    }

    @When("the Chef suggests a new meal plan for {string}")
    public void the_chef_suggests_a_new_meal_plan_for(String customerName) {
        customer.setUsername(customerName);

        suggestedMealPlan = recommender.suggestedMeals("suggestedMeals.txt");

        assertNotNull("Suggested meal plan should not be null", suggestedMealPlan);
    }

    @Then("the meal plan should include meals sharing ingredients with {string}")
    public void the_meal_plan_should_include_meals_sharing_ingredients_with(String mealName) {
        Meal referenceMeal = null;
        for (Meal meal : customer.getPastOrders()) {
            if (meal.getName().equals(mealName)) {
                referenceMeal = meal;
                break;
            }
        }

        assertNotNull("Reference meal not found in past orders", referenceMeal);
        List<String> referenceIngredients = referenceMeal.getIngredients();

        boolean hasSharedIngredients = suggestedMealPlan.stream().anyMatch(
                meal -> meal.getIngredients().stream().anyMatch(referenceIngredients::contains)
        );

        assertTrue("No meals in the suggested plan share ingredients with the reference meal", hasSharedIngredients);
    }

    @Given("the Customer {string} has no past orders")
    public void the_customer_has_no_past_orders(String customerName) {
        customer.setUsername(customerName);
    }

    @Then("the system should notify that no past orders exist")
    public void the_system_should_notify_that_no_past_orders_exist() {
        List<Meal> orders = customer.getPastOrders();
        assertTrue("Expected no past orders, but some exist", orders.isEmpty());
    }
}
