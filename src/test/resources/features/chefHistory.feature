Feature: Access customer order history for personalized meal plans

  Scenario: Chef views a customer's order history
    Given a chef is logged into the system
    And a customer has past meal orders stored in the system
    When the chef views the customer's profile
    Then the system should display the customer's order history

  Scenario: Chef suggests a personalized meal plan
    Given a chef has accessed a customer’s past orders
    When the chef prepares a meal plan for the customer
    Then the system should allow the chef to suggest meals based on past preferences
    And the system should notify the customer of the suggested meal plan