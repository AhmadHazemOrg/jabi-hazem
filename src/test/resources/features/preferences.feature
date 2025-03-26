Feature: Store dietary preferences and allergies

  Scenario: Customer inputs dietary preferences and allergies
    Given a customer is logged into the system
    When the customer navigates to the dietary preferences section
    And the customer selects their dietary preferences and allergies
    And the customer saves the preferences
    Then the system should store the dietary preferences and allergies
    And the system should confirm the preferences are saved successfully

  Scenario: Chef views customer dietary preferences
    Given a chef is logged into the system
    And a customer has stored their dietary preferences and allergies
    When the chef views the customer's profile
    Then the system should display the stored dietary preferences and allergies

  Scenario: System recommends meals based on dietary preferences
    Given a customer has saved their dietary preferences and allergies
    When the customer browses the menu
    Then the system should display only meals that match their preferences
    And the system should exclude meals that contain allergens

