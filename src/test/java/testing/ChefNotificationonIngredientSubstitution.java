package testing;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class ChefNotificationonIngredientSubstitution {

    private boolean substitutionRequested = false;
    private boolean substitutionConfirmed = false;
    private boolean chefAlertReceived = false;
    private String originalIngredient;
    private String substitutedIngredient;
    private boolean chefApproved = false;
    private boolean chefModified = false;
    private String chefAlternativeIngredient;

    @Given("a customer has requested a substitution for an unavailable ingredient")
    public void a_customer_has_requested_a_substitution_for_an_unavailable_ingredient() {
        substitutionRequested = true;
        originalIngredient = "Chicken";
        substitutedIngredient = "Tofu";
    }

    @When("the substitution is confirmed in the system")
    public void the_substitution_is_confirmed_in_the_system() {
        if (substitutionRequested) {
            substitutionConfirmed = true;
            chefAlertReceived = true;
        }
    }

    @Then("the chef should receive an alert with the original and substituted ingredients")
    public void the_chef_should_receive_an_alert_with_the_original_and_substituted_ingredients() {
        assertTrue("Chef did not receive the alert", chefAlertReceived);
        assertNotNull("Original ingredient is missing", originalIngredient);
        assertNotNull("Substituted ingredient is missing", substitutedIngredient);
        System.out.println("Chef Alert: Substitute " + originalIngredient + " with " + substitutedIngredient);
    }

    @Given("the chef receives an alert for a substitution request")
    public void the_chef_receives_an_alert_for_a_substitution_request() {
        chefAlertReceived = true;
        originalIngredient = "Beef";
        substitutedIngredient = "Mushrooms";
    }

    @When("they review the proposed change")
    public void they_review_the_proposed_change() {
        // Simulate a chef reviewing the change
        System.out.println("Chef reviewing substitution: " + originalIngredient + " → " + substitutedIngredient);
    }

    @Then("they should be able to approve it and finalize the recipe for preparation")
    public void they_should_be_able_to_approve_it_and_finalize_the_recipe_for_preparation() {
        chefApproved = true;
        assertTrue("Chef approval failed", chefApproved);
        System.out.println("Chef approved substitution: " + substitutedIngredient);
    }

    @Given("the chef is reviewing a substitution alert")
    public void the_chef_is_reviewing_a_substitution_alert() {
        chefAlertReceived = true;
        originalIngredient = "Milk";
        substitutedIngredient = "Soy Milk";
    }

    @When("they decide the suggested alternative is not suitable")
    public void they_decide_the_suggested_alternative_is_not_suitable() {
        chefModified = true;
        chefAlternativeIngredient = "Almond Milk";
    }

    @Then("they should be able to select a different ingredient before preparing the meal")
    public void they_should_be_able_to_select_a_different_ingredient_before_preparing_the_meal() {
        assertTrue("Chef did not modify the substitution", chefModified);
        assertEquals("Almond Milk", chefAlternativeIngredient);
        System.out.println("Chef selected new substitution: " + chefAlternativeIngredient);
    }
}
