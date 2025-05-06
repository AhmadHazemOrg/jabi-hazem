package testing;

import io.cucumber.java.en.*;
import org.example.Meal;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class RevenueReport {
    private List<Meal> meals = new ArrayList<>();
    private String errorMessage;
    private double lastComputedRevenue;


    @Given("the system has recorded the following meal orders:")
    public void the_system_has_recorded_the_following_meal_orders(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> orders = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> order : orders) {
            String mealName = order.get("Meal Name");
            double price = Double.parseDouble(order.get("Price"));
            String category = order.get("Category");
            String date = order.get("Date");
            Meal meal = new Meal(mealName, price, category, date);
            meals.add(meal);
        }
    }

    @When("the admin generates a revenue report")
    public void the_admin_generates_a_revenue_report() {
        double totalRevenue = meals.stream()
                .mapToDouble(Meal::getPrice)
                .sum();

        System.out.println("Total Revenue: " + totalRevenue);
        assertNotNull("Revenue report should be generated.", totalRevenue);
    }

    @Then("the report should display the total revenue as {string}")
    public void the_report_should_display_the_total_revenue_as(String expectedTotal) {
        double totalRevenue = meals.stream()
                .mapToDouble(Meal::getPrice)
                .sum();

        double expected = Double.parseDouble(expectedTotal);
        assertEquals("Revenue does not match expected value", expected, totalRevenue, 0.001);
    }

    @When("the admin generates a revenue report for the date range {string}")
    public void the_admin_generates_a_revenue_report_for_the_date_range(String dateRange) {

        String[] dates = dateRange.split(" to ");
        String startDate = dates[0];
        String endDate = dates[1];
        List<Meal> filteredMeals = Meal.filterMealsByDateRange(meals, startDate, endDate);

        lastComputedRevenue= filteredMeals.stream()
                .mapToDouble(Meal::getPrice)
                .sum();

        System.out.println("Total Revenue for Date Range: " + lastComputedRevenue);

        assertNotNull("Revenue report should be generated for the date range.", lastComputedRevenue);
    }

    @Then("the report should display the total revenue for the range as {string}")
    public void the_report_should_display_the_total_revenue_for_the_range_as(String expectedTotal) {
        double expected = Double.parseDouble(expectedTotal);
        assertEquals("Revenue does not match expected value for the range", expected, lastComputedRevenue, 0.001);



    }

    @When("the admin generates a revenue report by category")
    public void the_admin_generates_a_revenue_report_by_category() {
        Map<String, Double> revenueByCategory = meals.stream()
                .collect(Collectors.groupingBy(Meal::getCategory, Collectors.summingDouble(Meal::getPrice)));

        System.out.println("Revenue by Category: " + revenueByCategory);
        assertNotNull("Revenue by category report should be generated.", revenueByCategory);
    }

    @Then("the report should display the revenue for each category as:")
    public void the_report_should_display_the_revenue_for_each_category_as(io.cucumber.datatable.DataTable expectedCategories) {
        List<Map<String, String>> expected = expectedCategories.asMaps(String.class, String.class);

        Map<String, Double> expectedRevenue = new HashMap<>();
        for (Map<String, String> categoryData : expected) {
            String category = categoryData.get("Category");
            double revenue = Double.parseDouble(categoryData.get("Revenue"));
            expectedRevenue.put(category, revenue);
        }

        Map<String, Double> actualRevenue = meals.stream()
                .collect(Collectors.groupingBy(Meal::getCategory, Collectors.summingDouble(Meal::getPrice)));

        assertEquals("Revenue by category does not match expected values.", expectedRevenue, actualRevenue);
    }


}
