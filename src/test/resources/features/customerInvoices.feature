Feature: Billing System
  As a customer
  I want to receive an invoice
  So that I can track my purchases

  Scenario Outline: Invoice is generated with date and customer name
    Given the Customer "hazem mahamdeh" and "2004" is logged in
    And the Customer "hazem mahamdeh" has added the meal "<Meal Name>" priced at "<Price>" to their order
    When the Customer places the order
    Then an invoice should be generated
    Examples:
      | Meal Name     | Price |
      | Vegan Burger  | 8.50  |

  Scenario: No invoice is generated when no meals are ordered
    Given the Customer "saleh simreen" and "1980" is logged in
    And the Customer has not added any meals to their order
    When the Customer places the order
    Then no invoice should be generated
    And an error message should be shown: "No items in order"

  Scenario: Invoice includes multiple meals
    Given the Customer " adel adel " and "2002" is logged in
    And the Customer "adel adel" has added the following meals to their order:
      | Meal Name     | Price |
      | Vegan Burger  | 8.50  |
      | Salad         | 6.00  |
    When the Customer places the order
    Then an invoice should be generated
    And the invoice should include the customer's name, the current date, and the total "14.50"
