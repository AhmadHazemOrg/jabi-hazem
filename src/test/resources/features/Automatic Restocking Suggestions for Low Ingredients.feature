Feature: Automatic Restocking Suggestions for Low Ingredients

  As a system,
  I want to automatically suggest restocking when ingredients are low,
  so that kitchen managers can take action promptly.

  Scenario: Detect low ingredient level and suggest restocking
    Given the stock level of "Tomatoes" drops below the threshold
    When the system detects the low stock level
    Then it should generate a restocking suggestion for "Tomatoes" to the kitchen manager

  Scenario: Prevent duplicate restocking suggestions
    Given a restocking suggestion for "Cheese" was already generated today
    When the stock level remains low
    Then the system should not generate a duplicate suggestion within the same day

  Scenario: Prioritize restocking suggestions based on ingredient usage rate
    Given multiple ingredients are below their thresholds
    When generating restocking suggestions
    Then the system should prioritize ingredients that are used more frequently