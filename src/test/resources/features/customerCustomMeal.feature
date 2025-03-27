Feature: Customize meals by selecting ingredients

  Scenario: Customer selects ingredients for a custom meal
    Given a customer is logged into the system
    When the customer navigates to the meal customization section
    And the customer selects their desired ingredients
    Then the system should display the selected ingredients in the custom meal preview

  Scenario: Customer saves and orders a customized meal
    Given a customer has selected ingredients for a meal
    When the customer confirms and saves the meal customization
    And the customer places an order
    Then the system should process the order with the selected ingredients
    And the system should notify the kitchen of the custom meal request

  Scenario: System applies dietary preferences to ingredient selection
    Given a customer has dietary preferences and allergies saved in the system
    When the customer customizes a meal
    Then the system should highlight ingredients that match their dietary preferences
    And the system should warn the customer about allergens before selection
