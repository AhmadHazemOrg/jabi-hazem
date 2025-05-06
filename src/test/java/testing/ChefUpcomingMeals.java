package testing;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import io.cucumber.java.it.Ma;
import org.example.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

public class ChefUpcomingMeals {

    private Chef chef = new Chef();
    private Date currentTime;
    private final List<Meal> scheduledTasks = new ArrayList<>();
    private List<String> notificationsSent = new ArrayList<>();
    private static final int NOTIFICATION_WINDOW_MINUTES = 60;
    private Meal meal;


    @Given("the chef {string} has a cooking task for {string} scheduled at {string}")
    public void the_chef_has_a_cooking_task_for_scheduled_at(String chefName, String mealName, String scheduledTime) {
       chef.setUsername(chefName);
        scheduledTasks.clear();
        try {
            Date deliveryTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(scheduledTime);
            meal =new Meal(mealName, deliveryTime);
            scheduledTasks.add(meal);
            System.out.println("Scheduled tasks for chef " + chefName + ":");
            for (Meal m : scheduledTasks) {
                System.out.println("- " + m.getName() + " at " + m.getDeliveryTime());
            }

        } catch (ParseException e) {
            fail("Failed to parse scheduled time: " + scheduledTime);
        }
    }

    @Given("the chef {string} has the following cooking tasks scheduled:")
    public void the_chef_has_the_following_cooking_tasks_scheduled(String chefName, DataTable dataTable) {
        chef.setUsername(chefName);
        scheduledTasks.clear();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            try {
                String mealName = row.get("Meal Name");
                Date scheduledTime = format.parse(row.get("Scheduled Time"));
                meal =new Meal(mealName, scheduledTime);
                scheduledTasks.add(meal);


            } catch (ParseException e) {
                fail("Failed to parse scheduled time for: " + row.get("Meal Name"));
            }
        }
        System.out.println("Scheduled tasks for chef " + chefName + ":");
        for (Meal m : scheduledTasks) {
            System.out.println("- " + m.getName() + " at " + m.getDeliveryTime());
        }
    }

    @When("the current time is about {string}")
    public void the_current_time_is_about(String timeStr) {
        try {
            currentTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(timeStr);
        } catch (ParseException e) {
            fail("Failed to parse current time: " + timeStr);
        }

        notificationsSent.clear();

        notificationsSent = meal.getMealsToNotify(scheduledTasks, currentTime, NOTIFICATION_WINDOW_MINUTES);

        System.out.println("Current time: " + currentTime);
        System.out.println("Notifications sent:");
        for (String mealName : notificationsSent) {
            System.out.println("- " + mealName);
        }


    }


    @Then("the chef should receive a notification for {string}")
    public void the_chef_should_receive_a_notification_for(String mealName) {
        assertTrue("Expected notification for: " + mealName, notificationsSent.contains(mealName));
        System.out.println("Reminder confirmed for: " + mealName);
    }

    @Then("no notification should be sent")
    public void no_notification_should_be_sent() {
        assertTrue("Expected no notifications, but some were sent: " + notificationsSent, notificationsSent.isEmpty());
        System.out.println("No reminders sent.");
    }

    @Then("no notification should be sent for {string}")
    public void no_notification_should_be_sent_for(String mealName) {
        assertFalse("No notification should be sent for: " + mealName, notificationsSent.contains(mealName));
        System.out.println("No reminders sent."+ mealName);
    }
}
