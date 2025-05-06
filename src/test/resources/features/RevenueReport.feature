Feature: Generate financial reports for revenue analysis and business performance tracking

  As a system administrator
  I want to generate financial reports
  So that I can analyze revenue and track business performance

  Scenario: Admin generates a report for the total revenue
    Given  the Administrator "ahmad saif" and password "2005" is logged in
     And  the system has recorded the following meal orders:
      | Customer Name    | Meal Name     | Price |
      | John Doe         | Vegan Burger  | 8.50  |
      | Alice Smith      | Salad         | 6.00  |
      | Bob Johnson      | Pizza         | 12.00 |
    When the admin generates a revenue report
    Then the report should display the total revenue as "26.50"

  Scenario: Admin generates a report for the revenue from a specific date range
    Given the system has recorded the following meal orders:
      | Customer Name    | Meal Name     | Price | Date       |
      | John Doe         | Vegan Burger  | 8.50  | 2025-05-01 |
      | Alice Smith      | Salad         | 6.00  | 2025-05-02 |
      | Bob Johnson      | Pizza         | 12.00 | 2025-05-03 |
    When the admin generates a revenue report for the date range "2025-05-02 to 2025-05-03"
    Then the report should display the total revenue for the range as "18"

  Scenario: Admin generates a report for revenue by meal category
    Given the system has recorded the following meal orders:
      | Customer Name    | Meal Name     | Price | Category   |
      | John Doe         | Vegan Burger  | 8.50  | Vegan      |
      | Alice Smith      | Salad         | 6.00  | Salad      |
      | Bob Johnson      | Pizza         | 12.00 | Pizza      |
      | Jane Doe         | Vegan Burger  | 9.00  | Vegan      |
    When the admin generates a revenue report by category
    Then the report should display the revenue for each category as:
      | Category   | Revenue |
      | Vegan      | 17.50   |
      | Salad      | 6.00    |
      | Pizza      | 12.00   |
