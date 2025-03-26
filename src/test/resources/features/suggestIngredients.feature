Feature: Suggest ingredient substitutions based on dietary restrictions

  Scenario: Customer receives ingredient substitution suggestions
    Given a customer is logged into the system
    And the customer has dietary restrictions stored in the system
    When the customer selects a meal that contains restricted ingredients
    Then the system should suggest alternative ingredients that fit the customer's dietary restrictions

  Scenario: Customer accepts a suggested ingredient substitution
    Given the system has suggested alternative ingredients for a meal
    When the customer accepts a suggested substitution
    Then the system should update the meal with the chosen alternative ingredient

  Scenario: Chef receives notification of ingredient substitution
    Given a customer has placed an order with a substituted ingredient
    When the chef accesses the order details
    Then the system should notify the chef of the ingredient substitution

