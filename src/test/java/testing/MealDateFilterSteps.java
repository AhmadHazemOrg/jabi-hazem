package testing;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.example.Meal;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

public class MealDateFilterSteps {

    private final List<Meal> allMeals = new ArrayList<>();
    private List<Meal> filteredMeals = new ArrayList<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Given("the following meals are available:")
    public void the_following_meals_are_available(DataTable dataTable) {
        allMeals.clear();
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            try {
                String name = row.get("Meal Name");
                Date date = sdf.parse(row.get("Date"));
                allMeals.add(new Meal(name, date));
            } catch (Exception e) {
                fail("Failed to parse meal date");
            }
        }
    }

    @When("the user filters meals between {string} and {string}")
    public void the_user_filters_meals_between_and(String startDate, String endDate) {
        filteredMeals = Meal.filterMealsByDateRange(allMeals, startDate, endDate);
    }

    @Then("the filtered meals should include:")
    public void the_filtered_meals_should_include(DataTable expectedMealsTable) {
        List<String> expected = expectedMealsTable.asList();
        List<String> actual = new ArrayList<>();
        for (Meal m : filteredMeals) {
            actual.add(m.getName());
        }

        for (String expectedName : expected) {
            assertTrue("Expected meal not found: " + expectedName, actual.contains(expectedName));
        }
    }

    @Then("no meals should be returned")
    public void no_meals_should_be_returned() {
        assertTrue("Expected no meals, but got: " + filteredMeals, filteredMeals.isEmpty());
    }
}
