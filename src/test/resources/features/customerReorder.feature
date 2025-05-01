Feature: Customer views and reorders past meal orders

  Scenario Outline: Customer views their past meal orders
    Given the Customer "hazem mahamdeh" and "2004" is logged in
    And the Customer has previously ordered "<Meal name>" with "<ingredients>"
    When the Customer views their past orders
    Then the system should display the list of past meals

    Examples:
    Examples:
      | Meal name      | ingredients                         |
      | Vegan Salad    | lettuce, tomato, cucumber, tofu     |
      | Green Delight  | spinach, avocado, lime, kale        |


  Scenario: Customer reorders a meal from past orders
    Given the Customer "hazem mahamdeh" and "2004" is logged in
    And the Customer has previously ordered "Vegan Salad" with " lettuce, tomato, cucumber, tofu "
    When the Customer reorders "Vegan Salad"
    Then the system should confirm the reorder

