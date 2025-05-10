package testing;

import io.cucumber.java.en.*;

public class ChefNotificationonIngredientSubstitution{

    @Given("a customer has requested a substitution for an unavailable ingredient")
    public void a_customer_has_requested_a_substitution_for_an_unavailable_ingredient() {
        // Simulate a customer substitution request
        System.out.println("Customer requested substitution.");
    }

    @When("the substitution is confirmed in the system")
    public void the_substitution_is_confirmed_in_the_system() {
        // Confirm substitution in backend or memory
        System.out.println("Substitution confirmed.");
    }

    @Then("the chef should receive an alert with the original and substituted ingredients")
    public void the_chef_should_receive_an_alert_with_the_original_and_substituted_ingredients() {
        // Simulate chef notification
        System.out.println("Chef receives alert.");
    }

    @Given("the chef receives an alert for a substitution request")
    public void the_chef_receives_an_alert_for_a_substitution_request() {
        System.out.println("Chef got a substitution request.");
    }

    @When("they review the proposed change")
    public void they_review_the_proposed_change() {
        System.out.println("Chef reviews the proposed change.");
    }

    @Then("they should be able to approve it and finalize the recipe for preparation")
    public void they_should_be_able_to_approve_it_and_finalize_the_recipe_for_preparation() {
        System.out.println("Chef approves and finalizes recipe.");
    }

    @Given("the chef is reviewing a substitution alert")
    public void the_chef_is_reviewing_a_substitution_alert() {
        System.out.println("Chef is reviewing the alert.");
    }

    @When("they decide the suggested alternative is not suitable")
    public void they_decide_the_suggested_alternative_is_not_suitable() {
        System.out.println("Chef rejects the suggested alternative.");
    }

    @Then("they should be able to select a different ingredient before preparing the meal")
    public void they_should_be_able_to_select_a_different_ingredient_before_preparing_the_meal() {
        System.out.println("Chef selects a different ingredient.");
    }
}
