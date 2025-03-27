Feature: Input dietary preferences and allergies

  Scenario: Customer sets dietary preferences
    Given a customer is logged into the system
    When the customer navigates to the dietary preferences section
    And the customer selects their dietary preferences
    And the customer saves the preferences
    Then the system should store the dietary preferences
    And the system should confirm the preferences are saved successfully

  Scenario: Customer sets allergy restrictions
    Given a customer is logged into the system
    When the customer navigates to the allergy settings section
    And the customer selects ingredients they are allergic to
    And the customer saves the allergy settings
    Then the system should store the allergy information
    And the system should confirm the allergy settings are saved successfully

  Scenario: System applies dietary preferences and allergy settings to meal recommendations
    Given a customer has saved their dietary preferences and allergy information
    When the customer browses the menu
    Then the system should display only meals that match their dietary preferences
    And the system should exclude meals that contain allergens
