Feature: Track Available Ingredients and Suggest Restocking

  As a kitchen manager,
  I want to track ingredient stock levels in real time,
  so that I can prevent shortages and ensure continuous operations.

  Scenario: View real-time stock levels of ingredients
    Given the system is connected to the kitchen inventory
    When the kitchen manager opens the stock dashboard
    Then the system should display the current quantity of each ingredient in real time

  Scenario: Notify manager when stock is low
    Given an ingredient falls below the minimum stock threshold
    When the threshold is breached
    Then the system should notify the kitchen manager with a restock alert

  Scenario: Suggest restocking based on usage trends
    Given the system monitors ingredient usage history
    When the weekly usage exceeds average levels
    Then the system should suggest restocking the ingredient earlier than usual