package testing;

import io.cucumber.java.en.*;
import org.example.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CustomerInvoices {
    private Customer customer = new Customer();
    private Invoice invoice;
    private String errorMessage;

    @Given("the Customer {string} has added the meal {string} priced at {string} to their order")
    public void the_customer_has_added_the_meal_priced_at_to_their_order(String customerName, String mealName, String price) {
        double mealPrice = Double.parseDouble(price);
        Meal meal = new Meal(mealName, mealPrice);
        customer.setUsername(customerName);
        customer.addPastOrder(meal);
    }

    @When("the Customer places the order")
    public void the_customer_places_the_order() {
        if (customer.getPastOrders().isEmpty()) {
            errorMessage = "No items in order";
            invoice = null;
            return;
        }
        String customerName = customer.getUsername();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        double totalPrice = customer.getPastOrders().stream()
                .mapToDouble(Meal::getPrice)
                .sum();

        invoice = new Invoice(customerName, currentDate, customer.getPastOrders(), totalPrice);
        errorMessage = null;
    }

    @Then("an invoice should be generated")
    public void an_invoice_should_be_generated() {
        assertNotNull("Invoice has not been generated!", invoice);
        System.out.println(invoice);
    }




    @Given("the Customer has not added any meals to their order")
    public void the_customer_has_not_added_any_meals_to_their_order() {
        customer.clearPastOrders();
    }

    @Then("no invoice should be generated")
    public void no_invoice_should_be_generated() {
        assertNull("Invoice should not be generated when no meals are ordered.", invoice);
    }

    @Then("an error message should be shown: {string}")
    public void an_error_message_should_be_shown(String expectedMessage) {
        assertEquals(expectedMessage, errorMessage);
        System.out.println("Error Message: " + errorMessage);
    }

    @Given("the Customer {string} has added the following meals to their order:")
    public void the_customer_has_added_the_following_meals_to_their_order(String name, io.cucumber.datatable.DataTable dataTable) {
        customer.setUsername(name);
        List<Map<String, String>> meals = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> mealData : meals) {
            String mealName = mealData.get("Meal Name");
            double price = Double.parseDouble(mealData.get("Price"));
            customer.addPastOrder(new Meal(mealName, price));
        }
    }

    @Then("the invoice should include the customer's name, the current date, and the total {string}")
        public void the_invoice_should_include_the_customer_s_name_the_current_date_and_the_total(String expectedTotal) {
            assertNotNull("Invoice should have been generated.", invoice);

            double expected = Double.parseDouble(expectedTotal);
            System.out.println("Expected Total: " + expected);
            System.out.println("Actual Total in Invoice: " + invoice.getTotalPrice());
            assertEquals(expected, invoice.getTotalPrice(), 0.001);
        }

    }


