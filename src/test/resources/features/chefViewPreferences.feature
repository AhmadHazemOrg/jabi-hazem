Feature: Customize Meals According to Customer Preferences

  As a chef,
  I want to view customer dietary preferences
  So that I can customize meals accordingly.

  Scenario: Chef views a customer's dietary preferences
    Given Given the Chef "yahya jara" with password "2006" is logged in
    And a customer "hazem mahamdeh" has dietary preferences "Vegan, lahs" and allergies "Peanut, Dairy"
    When the chef views the dietary preferences of customer "hazem mahamdeh"
    Then the system should display "Vegan, lahs" as the dietary preference
    And display "Peanut, Dairy" as allergies


  Scenario: Chef customizes a meal according to the customer's preferences
    Given Given the Chef "yahya jara" with password "2006" is logged in
    And a customer "hazem mahamdeh" has dietary preferences "Vegan, mango" and allergies "Peanut, Dairy"
    When the chef creates a meal named "Green Delight" with ingredients "Spinach, Tofu, Olive Oil" and dietary tags "Vegan, mango"
    Then the meal should match the customer's dietary preference
    And the meal should not contain any allergens

  Scenario: Chef tries to customize a meal that contains an allergen
    Given  Given the Chef "yahya jara" with password "2006" is logged in
    And  a customer "hazem mahamdeh" has dietary preferences "Vegan" and allergies "Peanut, Dairy"
    When the chef attempts to create a meal named "Vegan Salad" with possible allergen ingredients "lettuce, peanuts, tomato" and tags "vegan"
    Then the system should warn about allergens in the meal



