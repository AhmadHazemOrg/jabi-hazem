Feature: Ingredient Combination Validation

  As a system,
  I want to validate ingredient combinations
  so that customers do not select incompatible or unavailable ingredients.

  Scenario: Prevent selection of incompatible ingredients
    Given the customer has selected a lactose-free option
    When they attempt to add cheese to their meal
    Then the system should display an error message and block the selection

  Scenario: Handle selection of unavailable ingredients
    Given the ingredient "Lamb" is currently out of stock
    When the customer views the list of protein options
    Then "Lamb" should be grayed out or hidden from the selection

  Scenario: Enforce maximum number of add-ons
    Given the system allows a maximum of 5 add-ons per meal
    When the customer tries to select a 6th add-on
    Then the system should display a warning and prevent the selection