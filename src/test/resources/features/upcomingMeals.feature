Feature: Meal delivery reminders

  As a customer,
  I want to receive reminders for my upcoming meal deliveries
  so that I can be prepared to receive them.

  Scenario: Customer has a meal scheduled for delivery in 30 minutes
    Given the customer "Hazem Mahamdeh" has a meal "Grilled Chicken" scheduled for delivery at "2025-05-04T13:00:00"
    When the current time is "2025-05-04T12:30:00"
    Then the customer should receive a reminder for "Grilled Chicken" delivery

  Scenario: Customer has a meal scheduled for later today but it's not time for a reminder yet
    Given the customer "Hazem Mahamdeh" has a meal "Vegan Bowl" scheduled for delivery at "2025-05-04T18:00:00"
    When the current time is "2025-05-04T12:30:00"
    Then no reminder should be sent

  Scenario: Customer has multiple meals scheduled but only one qualifies for a reminder
    Given the customer "Hazem Mahamdeh" has the following meals scheduled:
      | Meal Name       | Delivery Time           |
      | Pasta Alfredo   | 2025-05-04T13:00:00     |
      | Quinoa Salad    | 2025-05-04T17:30:00     |
    When the current time is "2025-05-04T12:30:00"
    Then the customer should receive a reminder for "Pasta Alfredo" delivery
    And no reminder should be sent for "Quinoa Salad"


