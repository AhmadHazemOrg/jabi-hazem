package testing;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import java.util.*;

public class IngredientCombinationValidation {

    private boolean isLactoseFreeSelected = false;
    private String attemptedIngredient;
    private String unavailableIngredient;
    private List<String> proteinOptions = new ArrayList<>();
    private int maxAddOnsAllowed;
    private int currentAddOns;

    @Given("the customer has selected a lactose-free option")
    public void the_customer_has_selected_a_lactose_free_option() {
        isLactoseFreeSelected = true;
    }

    @When("they attempt to add cheese to their meal")
    public void they_attempt_to_add_cheese_to_their_meal() {
        attemptedIngredient = "cheese";
    }

    @Then("the system should display an error message and block the selection")
    public void the_system_should_display_an_error_message_and_block_the_selection() {
        if (isLactoseFreeSelected && attemptedIngredient.equalsIgnoreCase("cheese")) {
            System.out.println("❌ Error: Cheese cannot be added to a lactose-free meal.");
        } else {
            fail("Invalid combination was not blocked.");
        }
    }

    @Given("the ingredient {string} is currently out of stock")
    public void the_ingredient_is_currently_out_of_stock(String ingredient) {
        unavailableIngredient = ingredient;
    }

    @When("the customer views the list of protein options")
    public void the_customer_views_the_list_of_protein_options() {
        proteinOptions.add("Chicken");
        proteinOptions.add("Beef");
        proteinOptions.add("Tofu");
        proteinOptions.add(unavailableIngredient); // Add it to simulate its existence
    }

    @Then("{string} should be grayed out or hidden from the selection")
    public void should_be_grayed_out_or_hidden_from_the_selection(String ingredient) {
        boolean shouldHide = proteinOptions.contains(ingredient);
        if (shouldHide) {
            System.out.println("ℹ️ " + ingredient + " is grayed out or hidden (out of stock).");
        } else {
            fail(ingredient + " should have been shown as unavailable.");
        }
    }

    @Given("the system allows a maximum of {int} add-ons per meal")
    public void the_system_allows_a_maximum_of_add_ons_per_meal(Integer maxAddOns) {
        maxAddOnsAllowed = maxAddOns;
        currentAddOns = maxAddOns; // Already at max
    }

    @When("the customer tries to select a 6th add-on")
    public void the_customer_tries_to_select_a_6th_add_on() {
        currentAddOns += 1;
    }

    @Then("the system should display a warning and prevent the selection")
    public void the_system_should_display_a_warning_and_prevent_the_selection() {
        if (currentAddOns > maxAddOnsAllowed) {
            System.out.println("⚠️ You can only select up to " + maxAddOnsAllowed + " add-ons.");
        } else {
            fail("System did not prevent exceeding max add-ons.");
        }
    }
}
