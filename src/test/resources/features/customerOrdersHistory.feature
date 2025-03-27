Feature: View and reorder past meal orders

  Scenario: Customer views past meal orders
    Given a customer is logged into the system
    When the customer navigates to the order history section
    Then the system should display a list of the customer’s past meal orders

  Scenario: Customer reorders a past meal
    Given a customer has past meal orders in the system
    When the customer selects a meal from their order history
    And the customer confirms the reorder
    Then the system should create a new order with the same meal
    And the system should notify the kitchen of the new order

