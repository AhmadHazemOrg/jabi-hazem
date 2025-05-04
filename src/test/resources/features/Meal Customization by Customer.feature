Feature: Meal Customization by Customer
  As a customer, I want to select ingredients and customize my meal so that I can order
  meals according to my taste and dietary needs.


  Scenario: Successfully build a custom meal
    Given the customer is on the meal customization page
    When they select rice, chicken, grilled vegetables, and garlic sauce
    Then the system should display a custom meal summary with the updated price

  Scenario: Filter available ingredients based on dietary preferences
    Given the customer selects the "Gluten-Free" dietary filter
    When they view the list of available ingredients
    Then only gluten-free options should be shown

  Scenario: Save custom meal for future use
    Given the customer has selected a full set of ingredients
    When they click "Save as Favorite"
    Then the meal should be stored in their favorites list under their account