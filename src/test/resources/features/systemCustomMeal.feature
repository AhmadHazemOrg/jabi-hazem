Feature: Validate ingredient combinations for meal customization

  Scenario: System prevents selection of incompatible ingredients
    Given a customer is customizing a meal
    And the customer selects an ingredient that is incompatible with another selected ingredient
    When the customer attempts to save the custom meal
    Then the system should display an error message about the incompatible ingredients
    And the system should suggest alternative compatible ingredients

  Scenario: System prevents selection of unavailable ingredients
    Given a customer is customizing a meal
    And an ingredient is out of stock
    When the customer attempts to add the unavailable ingredient
    Then the system should notify the customer that the ingredient is unavailable
    And the system should suggest alternative ingredients

  Scenario: System validates meal customization before order confirmation
    Given a customer has selected ingredients for a meal
    When the customer attempts to confirm the order
    Then the system should validate that all selected ingredients are compatible and available
    And the system should prevent the order if any validation fails
    And the system should display appropriate warnings or suggestions