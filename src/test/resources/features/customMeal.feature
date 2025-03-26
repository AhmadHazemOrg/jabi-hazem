Feature: Allow customers to create custom meal requests

  Scenario: Customer selects ingredients to customize a meal
    Given a customer is logged into the system
    When the customer navigates to the meal customization section
    And the customer selects ingredients for their custom meal
    Then the system should validate the selected ingredients
    And the system should display a preview of the custom meal

  Scenario: System rejects incompatible ingredient combinations
    Given a customer is logged into the system
    And the customer selects an incompatible combination of ingredients
    When the customer attempts to save the custom meal
    Then the system should display an error message about incompatible ingredients

  Scenario: Customer confirms and places a custom meal order
    Given a customer has selected valid ingredients for a custom meal
    When the customer confirms the order
    Then the system should save the custom meal request
    And the system should notify the kitchen staff about the new custom meal order

