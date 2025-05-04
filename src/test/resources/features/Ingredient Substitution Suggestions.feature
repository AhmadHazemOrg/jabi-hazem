Feature: Ingredient Substitution Suggestions

  As a customer,
  I want the system to suggest alternative ingredients
  if an ingredient is unavailable or does not fit my dietary restrictions
  so that I can enjoy my meal without compromising my health.

  Scenario: Suggest alternative for unavailable ingredient
    Given the ingredient "Shrimp" is out of stock
    When the customer selects "Shrimp" in the protein section
    Then the system should suggest similar alternatives like "Fish" or "Tofu"

  Scenario: Suggest substitutions for allergy-based restrictions
    Given the customer has marked a "Nut Allergy" in their profile
    When they select a sauce that contains nuts
    Then the system should block the sauce and suggest nut-free alternatives

  Scenario: Suggest replacements for vegan dietary preference
    Given the customer has chosen a "Vegan" dietary preference
    When they try to add cheese to their meal
    Then the system should suggest plant-based cheese alternatives