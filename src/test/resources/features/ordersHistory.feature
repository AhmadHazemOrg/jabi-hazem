Feature: Track past orders and personalized meal plans

  Scenario: Customer views past meal orders
    Given a customer is logged into the system
    When the customer navigates to the order history section
    Then the system should display a list of past meal orders

  Scenario: Chef accesses customer order history
    Given a chef is logged into the system
    And a customer has past meal orders in the system
    When the chef views the customer's profile
    Then the system should display the customer's past meal orders

  Scenario: System suggests personalized meal plans based on past orders
    Given a customer has past meal orders in the system
    When the customer navigates to the personalized meal plans section
    Then the system should generate meal suggestions based on past orders
