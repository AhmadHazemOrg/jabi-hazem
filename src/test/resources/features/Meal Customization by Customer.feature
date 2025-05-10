Feature: Meal Customization by Customer
  As a customer,
  I want to build and save custom meals based on available ingredients and dietary filters,
  So that I can enjoy meals that suit my taste and health needs.

  Background:
    Given the customer is logged in and on the meal customization page
    And the available ingredients include: rice, chicken, grilled vegetables, garlic sauce, pasta, beef, soy sauce
    And the available dietary filters include: Gluten-Free, Vegan, Dairy-Free

  Scenario: Successfully build a custom meal and see updated price
    When the customer selects: rice, chicken, grilled vegetables, garlic sauce
    Then the custom meal summary should display the selected ingredients
    And the total price should be calculated and displayed as $10.50

  Scenario: Filter available ingredients based on "Gluten-Free" preference
    When the customer applies the "Gluten-Free" dietary filter
    Then the ingredient list should exclude: pasta, soy sauce
    And only gluten-free ingredients should be visible

  Scenario: Save a customized meal as favorite
    When the customer selects: rice, chicken, garlic sauce
    And clicks "Save as Favorite"
    Then the meal should appear in the customer's saved meals list
    And the name "rice, chicken, garlic sauce" should be shown in favorites
