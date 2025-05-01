Feature: System Administrator stores and retrieves customer order history

  Scenario: Administrator stores customer order history
    Given the Administrator "ahmad saif" and password "2005" is logged in
    And the Customer named "hazem mahamdeh" has previously ordered "Vegan Salad" with "lettuce, tomato, cucumber, tofu"
    When the Administrator stores the order history for "hazem mahamdeh"
    Then the order history should be stored successfully

  Scenario: Administrator retrieves customer order history
    Given the Administrator "ahmad saif" and password "2005" is logged in
    And the Customer named "hazem mahamdeh" has previously ordered "Vegan Salad" with "lettuce, tomato, cucumber, tofu"
    When the Administrator retrieves the order history for "hazem mahamdeh"
    Then the system should display the list of past meals for "hazem mahamdeh"

  Scenario: Administrator retrieves order history for a customer with no past orders
    Given the Administrator "ahmad saif" and password "2005" is logged in
    And the Customer named "adel jaj" has not made any past orders
    When the Administrator retrieves the order history for "adel jaj"
    Then the system should display that there are no past meals for "adel jaj"

