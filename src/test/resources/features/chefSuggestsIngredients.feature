Feature: Alert chefs when ingredient substitutions are applied

  Scenario: Chef receives an alert for ingredient substitutions
    Given a customer has placed an order with an ingredient substitution
    When the order is sent to the kitchen
    Then the system should notify the chef that an ingredient has been substituted

  Scenario: Chef reviews and approves the substitution
    Given a chef has received an alert about an ingredient substitution
    When the chef reviews the substituted ingredient
    Then the chef can approve the substitution

  Scenario: Chef modifies the substitution before preparation
    Given a chef has received an alert about an ingredient substitution
    When the chef reviews the substituted ingredient
    Then the chef can modify the final recipe before preparation

  Scenario: System logs ingredient substitutions for future reference
    Given a customer’s meal included an ingredient substitution
    When the order is completed
    Then the system should store the substitution details in the customer’s order history
    And the system should use this data for future meal recommendations
