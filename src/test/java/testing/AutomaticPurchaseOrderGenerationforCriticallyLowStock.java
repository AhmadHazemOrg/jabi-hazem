package testing;

import io.cucumber.java.en.*;
import org.example.StockReplenishmentManager;

public class AutomaticPurchaseOrderGenerationforCriticallyLowStock {

    private final StockReplenishmentManager manager = new StockReplenishmentManager();
    private String currentIngredient;

    @Given("the stock level of {string} is below the critical threshold")
    public void the_stock_level_of_is_below_the_critical_threshold(String ingredient) {
        this.currentIngredient = ingredient;
        manager.setStockLevel(ingredient, 3);
    }

    @When("the system detects the low level")
    public void the_system_detects_the_low_level() {
        if (!manager.isCriticallyLow(currentIngredient)) {
            throw new IllegalStateException("Stock level is not critically low.");
        }
    }

    @Then("it should automatically create a purchase order for {string} and send it to the default supplier")
    public void it_should_automatically_create_a_purchase_order_for_and_send_it_to_the_default_supplier(String ingredient) {
        boolean created = manager.createPurchaseOrderIfNeeded(ingredient);
        if (!created) {
            throw new IllegalStateException("Duplicate order was blocked, order already pending.");
        }
        manager.sendOrderToSupplier(ingredient);
    }

    @Given("the system has generated a purchase order for {string}")
    public void the_system_has_generated_a_purchase_order_for(String ingredient) {
        this.currentIngredient = ingredient;
        manager.createPurchaseOrderIfNeeded(ingredient);
    }

    @When("the order is sent to the supplier")
    public void the_order_is_sent_to_the_supplier() {
        manager.sendOrderToSupplier(currentIngredient);
    }

    @Then("the kitchen manager should receive a confirmation notification with order details")
    public void the_kitchen_manager_should_receive_a_confirmation_notification_with_order_details() {
        boolean notified = manager.notifyManager(currentIngredient);
        if (!notified) {
            throw new IllegalStateException("Notification not sent or already sent.");
        }
    }

    @Given("a purchase order for {string} has already been generated and is pending")
    public void a_purchase_order_for_has_already_been_generated_and_is_pending(String ingredient) {
        the_system_has_generated_a_purchase_order_for(ingredient);
    }

    @When("the stock level remains critically low")
    public void the_stock_level_remains_critically_low() {
        if (!manager.isCriticallyLow(currentIngredient)) {
            throw new IllegalStateException("Stock is no longer critically low.");
        }
    }

    @Then("the system should not generate another order until the existing one is fulfilled or canceled")
    public void the_system_should_not_generate_another_order_until_the_existing_one_is_fulfilled_or_canceled() {
        boolean created = manager.createPurchaseOrderIfNeeded(currentIngredient);
        if (created) {
            throw new IllegalStateException("A duplicate order was created by mistake.");
        }
    }
}
