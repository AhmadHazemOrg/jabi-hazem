package testing;

import io.cucumber.java.en.*;

import java.util.*;

import static org.junit.Assert.*;

public class IngredientSubstitutionSuggestions  {

    private String selectedProtein;
    private String unavailableIngredient;
    private String allergy;
    private String dietaryPreference;
    private List<String> suggestions = new ArrayList<>();
    private boolean sauceBlocked = false;
    private boolean plantBasedSuggested = false;

    @Given("the ingredient {string} is out of stock")
    public void the_ingredient_is_out_of_stock(String ingredient) {
        unavailableIngredient = ingredient;
    }

    @When("the customer selects {string} in the protein section")
    public void the_customer_selects_in_the_protein_section(String protein) {
        selectedProtein = protein;

        // simulate substitution
        if (protein.equals(unavailableIngredient)) {
            suggestions = Arrays.asList("Fish", "Tofu");
        }
    }

    @Then("the system should suggest similar alternatives like {string} or {string}")
    public void the_system_should_suggest_similar_alternatives_like_or(String alt1, String alt2) {
        assertTrue("Expected alternatives not found", suggestions.containsAll(Arrays.asList(alt1, alt2)));
    }

    @Given("the customer has marked a {string} in their profile")
    public void the_customer_has_marked_a_in_their_profile(String allergyType) {
        allergy = allergyType;
    }

    @When("they select a sauce that contains nuts")
    public void they_select_a_sauce_that_contains_nuts() {
        if ("Nut Allergy".equalsIgnoreCase(allergy)) {
            sauceBlocked = true;
            suggestions = Arrays.asList("Tomato Sauce", "Garlic Sauce");
        }
    }

    @Then("the system should block the sauce and suggest nut-free alternatives")
    public void the_system_should_block_the_sauce_and_suggest_nut_free_alternatives() {
        assertTrue("Sauce was not blocked", sauceBlocked);
        assertFalse("No suggestions given", suggestions.isEmpty());
    }

    @Given("the customer has chosen a {string} dietary preference")
    public void the_customer_has_chosen_a_dietary_preference(String preference) {
        dietaryPreference = preference;
    }

    @When("they try to add cheese to their meal")
    public void they_try_to_add_cheese_to_their_meal() {
        if ("Vegan".equalsIgnoreCase(dietaryPreference)) {
            plantBasedSuggested = true;
            suggestions = Arrays.asList("Vegan Cheese", "Cashew Cheese");
        }
    }

    @Then("the system should suggest plant-based cheese alternatives")
    public void the_system_should_suggest_plant_based_cheese_alternatives() {
        assertTrue("No plant-based alternatives suggested", plantBasedSuggested);
        assertTrue("Expected plant-based cheese not suggested", suggestions.contains("Vegan Cheese"));
    }
}
