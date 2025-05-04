Feature: Chef Notification on Ingredient Substitution

  As a chef,
  I want to receive an alert when an ingredient substitution is applied
  so that I can approve or adjust the final recipe.

  Scenario: Notify chef when substitution occurs
    Given a customer has requested a substitution for an unavailable ingredient
    When the substitution is confirmed in the system
    Then the chef should receive an alert with the original and substituted ingredients

  Scenario: Chef approves a suggested substitution
    Given the chef receives an alert for a substitution request
    When they review the proposed change
    Then they should be able to approve it and finalize the recipe for preparation

  Scenario: Chef modifies the substitution manually
    Given the chef is reviewing a substitution alert
    When they decide the suggested alternative is not suitable
    Then they should be able to select a different ingredient before preparing the meal