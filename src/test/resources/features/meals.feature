Feature: Filter meals by date

  Scenario: Filter meals within a valid date range
    Given the following meals are available:
      | Meal Name | Date       |
      | Pasta     | 2025-05-01 |
      | Salad     | 2025-05-10 |
      | Curry     | 2025-05-20 |
    When the user filters meals between "2025-05-05" and "2025-05-15"
    Then the filtered meals should include:
      | Salad |

  Scenario: Filter with no matching meals
    Given the following meals are available:
      | Meal Name | Date       |
      | Burger    | 2025-04-01 |
      | Tacos     | 2025-04-05 |
    When the user filters meals between "2025-05-01" and "2025-05-31"
    Then no meals should be returned
