Feature: Notify users of low-stock ingredients

  As a kitchen manager,
  I want to receive alerts when stock levels are low
  so that I can reorder before running out of ingredients.

  Scenario: Ingredient falls below minimum threshold
    Given the ingredient "Tomatoes" has a stock level of 3
    And the minimum threshold for "Tomatoes" is 5
    When the system checks stock levels
    Then the kitchen manager should receive a low-stock alert for "Tomatoes"

  Scenario: Ingredient stock is exactly at the threshold
    Given the ingredient "Cheese" has a stock level of 5
    And the minimum threshold for "Cheese" is 5
    When the system checks stock levels
    Then the kitchen manager should receive a low-stock alert for "Cheese"

  Scenario: Ingredient stock is above the threshold
    Given the ingredient "Pasta" has a stock level of 10
    And the minimum threshold for "Pasta" is 5
    When the system checks stock levels
    Then no low-stock alert should be sent for "Pasta"

  Scenario: Multiple ingredients with mixed stock levels
    Given the following ingredient stock levels and thresholds:
      | Ingredient | Stock Level | Threshold |
      | Lettuce    | 2           | 5         |
      | Chicken    | 6           | 5         |
      | Onions     | 5           | 5         |
    When the system checks stock levels
    Then the kitchen manager should receive low-stock alerts for:
      | Ingredient |
      | Lettuce    |
      | Onions     |
    And no alert should be sent for "Chicken"
