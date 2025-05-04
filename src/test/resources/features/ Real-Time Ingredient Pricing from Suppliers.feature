Feature: Real-Time Ingredient Pricing from Suppliers

  As a kitchen manager,
  I want the system to fetch real-time ingredient prices from suppliers,
  so that I can make cost-effective purchasing decisions.

  Scenario: Display real-time prices for selected ingredients
    Given the kitchen manager selects an ingredient from the inventory system
    When the system queries supplier APIs
    Then the current prices for that ingredient from available suppliers should be displayed

  Scenario: Alert manager about price changes
    Given the price of "Olive Oil" has changed significantly (e.g., more than 10%)
    When the system receives the updated price
    Then it should notify the kitchen manager about the price change

  Scenario: Compare prices from multiple suppliers
    Given the system has access to data from Supplier A and Supplier B
    When the kitchen manager searches for "Flour"
    Then the system should show a side-by-side comparison of prices, delivery times, and availability