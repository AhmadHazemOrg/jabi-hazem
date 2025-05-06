package testing;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.example.Customer;
import org.example.Meal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

public class UpcomingMeals {

    private Customer customer = new Customer();
    private Date currentTime;
    private Meal meal;
    private final List<Meal> scheduledMeals = new ArrayList<>();
    private List<String> remindersSent = new ArrayList<>();
    private static final int REMINDER = 60;

    @Given("the customer {string} has a meal {string} scheduled for delivery at {string}")
    public void the_customer_has_a_meal_scheduled_for_delivery_at(String customerName, String mealName, String deliveryTimeStr) {
        customer.setUsername(customerName);
        scheduledMeals.clear();
        try {
            Date deliveryTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(deliveryTimeStr);
            meal=new Meal(mealName, deliveryTime);
            scheduledMeals.add(meal);
            System.out.println("Scheduled meal: " + mealName + " for customer: " + customerName + " at " + deliveryTimeStr);
        } catch (ParseException e) {
            fail("Failed to parse delivery time: " + deliveryTimeStr);
        }
    }

    @Given("the customer {string} has the following meals scheduled:")
    public void the_customer_has_the_following_meals_scheduled(String customerName, DataTable dataTable) {
        customer.setUsername(customerName);
        scheduledMeals.clear();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            try {
                String name = row.get("Meal Name");
                Date deliveryTime = format.parse(row.get("Delivery Time"));
                meal=new Meal(name, deliveryTime);
                scheduledMeals.add(meal);
                System.out.println("Scheduled meal: " + name + " at " + row.get("Delivery Time"));
            } catch (ParseException e) {
                fail("Failed to parse delivery time for meal: " + row.get("Meal Name"));
            }
        }
    }

    @When("the current time is {string}")
    public void the_current_time_is(String timeStr) {
        try {
            currentTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(timeStr);
            System.out.println("Current time: " + timeStr);
        } catch (ParseException e) {
            fail("Failed to parse current time: " + timeStr);
        }


        remindersSent.clear();
        remindersSent = meal.getMealsToNotify(scheduledMeals, currentTime, REMINDER);
    }


    @Then("the customer should receive a reminder for {string} delivery")
    public void the_customer_should_receive_a_reminder_for_delivery(String mealName) {
        assertTrue("Expected reminder for: " + mealName, remindersSent.contains(mealName));
        System.out.println("Reminder confirmed for: " + mealName);
    }


    @Then("no reminder should be sent")
    public void no_reminder_should_be_sent() {
        assertTrue("Expected no reminders, but some were sent: " + remindersSent, remindersSent.isEmpty());
        System.out.println("No reminders sent.");
    }

    @Then("no reminder should be sent for {string}")
    public void no_reminder_should_be_sent_for(String mealName) {
        assertFalse("No reminder should be sent for: " + mealName, remindersSent.contains(mealName));
        System.out.println("No reminder sent for: " + mealName);
    }
}
