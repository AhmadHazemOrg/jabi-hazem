package testing;

import io.cucumber.java.en.*;
import org.example.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerReorder {
    private final Customer customer = new Customer();
    private final List<Meal> pastOrders = new ArrayList<>();
    private Meal reorderedMeal;

    @Given("the Customer has previously ordered {string} with {string}")
    public void the_customer_has_previously_ordered(String mealName, String ingredients) {
        List<String> ingredientList = Arrays.asList(ingredients.split(","));
        Meal meal = new Meal(mealName);
        meal.setIngredients(ingredientList);
        customer.addPastOrder(meal);
    }

    @When("the Customer views their past orders")
    public void the_customer_views_their_past_orders() {
        pastOrders.addAll(customer.getPastOrders());
        assertNotNull("Past orders should not be null", pastOrders);

    }

    @Then("the system should display the list of past meals")
    public void the_system_should_display_the_list_of_past_meals() {
        assertFalse("Past orders should not be empty", pastOrders.isEmpty());
    }


    @When("the Customer reorders {string}")
    public void the_customer_reorders(String mealName) {
        reorderedMeal = customer.reorderMeal(mealName);
    }

    @Then("the system should confirm the reorder")
    public void the_system_should_confirm_the_reorder() {
        assertNotNull("Reordered meal should not be null", reorderedMeal);
    }

}
