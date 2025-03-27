Feature: View customer dietary preferences for meal customization

  Scenario: Chef views a customer's dietary preferences and allergies
    Given a chef is logged into the system
    And a customer has stored their dietary preferences and allergies
    When the chef views the customer's profile
    Then the system should display the customer's dietary preferences
    And the system should display the customer's allergy information

  Scenario: Chef receives an alert for allergen conflicts
    Given a chef is logged into the system
    And a customer has an allergy to a specific ingredient
    When the chef prepares a meal for the customer
    Then the system should notify the chef of any allergen conflicts

  Scenario: Chef customizes a meal based on dietary preferences
    Given a chef is logged into the system
    And a customer has dietary preferences saved in the system
    When the chef prepares a meal for the customer
    Then the system should provide ingredient substitution suggestions based on the customer's preferences