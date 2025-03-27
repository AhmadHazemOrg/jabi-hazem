Feature: Suggest alternative ingredients for unavailable or restricted items

  Scenario: System suggests alternative ingredients when an item is unavailable
    Given a customer is customizing a meal
    And the customer selects an ingredient that is unavailable
    When the system detects the ingredient is out of stock
    Then the system should suggest alternative ingredients that are available

  Scenario: System suggests alternatives for dietary restrictions
    Given a customer has dietary preferences and allergies saved in the system
    And the customer selects an ingredient that does not fit their dietary restrictions
    When the system detects the ingredient is restricted
    Then the system should warn the customer about the restriction
    And the system should suggest alternative ingredients that fit the customer's dietary preferences

  Scenario: Customer modifies the suggested alternative ingredient
    Given the system has suggested alternative ingredients
    When the customer reviews the suggested alternatives
    Then the customer can modify the ingredient selection before saving the meal
