package testing;

import io.cucumber.java.en.*;
import org.example.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AdminReorder{




    private Admin administrator = new Admin();
    private Customer customer = new Customer();
    private List<Meal> storedOrderHistory;

    @Given("the Administrator {string} and password {string} is logged in")
    public void the_administrator_and_password_is_logged_in(String username, String password) {
        administrator.setUsername(username);
        administrator.setPassword(password);
        assertTrue("Administrator login failed", administrator.login());
    }

    @Given("the Customer named {string} has previously ordered {string} with {string}")
    public void the_customer_has_previously_ordered(String customerName, String mealName,String ingredients) {

        customer.setUsername(customerName);
        Meal meal = new Meal(mealName);
        List<String> ingredientList = Arrays.asList(ingredients.split(","));
        meal.setIngredients(ingredientList);
        customer.addPastOrder(meal);



    }
    @When("the Administrator stores the order history for {string}")
    public void the_administrator_stores_the_order_history_for(String customerName) {
        customer.setUsername(customerName);
        storedOrderHistory = customer.getPastOrders();


    }

    @Then("the order history should be stored successfully")
    public void the_order_history_should_be_stored_successfully() {
        assertFalse("Stored order history should not be empty", storedOrderHistory.isEmpty());
    }

    @When("the Administrator retrieves the order history for {string}")
    public void the_administrator_retrieves_the_order_history_for(String customerName) {
        customer.setUsername(customerName);
        storedOrderHistory = customer.getPastOrders();
    }

    @Then("the system should display the list of past meals for {string}")
    public void the_system_should_display_the_list_of_past_meals_for(String customerName) {
        assertNotNull("Order history is null", storedOrderHistory);
        assertFalse("Order history is empty", storedOrderHistory.isEmpty());

        System.out.println("Past meals for " + customerName + ":");
        for (Meal meal : storedOrderHistory) {
            System.out.println("- " + meal.getName());
        }

    }
    @Given("the Customer named {string} has not made any past orders")
    public void the_customer_has_not_made_any_past_orders(String customerName) {
        customer.setUsername(customerName);

        customer.clearPastOrders();
    }
    @Then("the system should display that there are no past meals for {string}")
    public void the_system_should_display_that_there_are_no_past_meals_for(String customerName) {
        assertTrue("Expected no past meals, but found some", storedOrderHistory.isEmpty());
        System.out.println("No past meals found for " + customerName);
    }


}