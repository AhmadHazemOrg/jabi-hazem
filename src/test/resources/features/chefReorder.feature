Feature: Chef accesses customer order history to suggest personalized meal plans

  Scenario Outline: Chef views a customer's past orders
    Given the Chef "yahya jara" and password "2006" is logged in
    And the Customer "<CustomerName>" has previously ordered "<MealName>" with "<ingredients>"
    When the Chef views the order history for "<CustomerName>"
    Then the system should display the past meals including "<MealName>"

    Examples:
      | CustomerName     | MealName       |ingredients                     |
      | hazem mahamdeh   | Vegan Salad    | lettuce, tomato, cucumber, tofu|
      | saleh simreen    | Green Delight   |spinach, avocado, lime, kale   |



  Scenario Outline: Chef suggests a meal plan based on past orders
    Given the Chef "yahya jara" and password "2006" is logged in
    And the Customer "<CustomerName>" has previously ordered "<MealName>" with "<ingredients>"
    When the Chef views the order history for "<CustomerName>"
    And the Chef suggests a new meal plan for "<CustomerName>"
    Then the meal plan should include meals sharing ingredients with "<MealName>"

    Examples:
      | CustomerName     | MealName       |ingredients                     |
      | hazem mahamdeh   | Vegan Salad    | lettuce, tomato, cucumber, tofu|
      | saleh simreen    | Green Delight   |spinach, avocado, lime, kale   |

  Scenario: Chef attempts to view history for a customer with no orders
    Given the Chef "yahya jara" and password "2006" is logged in
    And the Customer "mazen kanaan" has no past orders
    When the Chef views the order history for "mazen kanaan"
    Then the system should notify that no past orders exist
