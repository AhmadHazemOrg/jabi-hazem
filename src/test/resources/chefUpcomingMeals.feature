Feature: Chef cooking notifications
  As a chef,
  I want to get notified of scheduled cooking tasks,
  So that I can prepare meals on time.

  Scenario: Chef receives a notification for a cooking task scheduled within the next hour
    Given the chef "yahya jara" has a cooking task for "Spaghetti Bolognese" scheduled at "2025-05-05T13:00:00"
    When the current time is about "2025-05-05T12:00:00"
    Then the chef should receive a notification for "Spaghetti Bolognese"

  Scenario: Chef does not receive a notification for a cooking task scheduled more than an hour ahead
    Given the chef "yahya jara" has a cooking task for "Grilled Salmon" scheduled at "2025-05-05T15:00:00"
    When the current time is about "2025-05-05T13:30:00"
    Then no notification should be sent

  Scenario: Chef receives multiple notifications for cooking tasks scheduled within the next hour
    Given the chef "yahya jara" has the following cooking tasks scheduled:

      | Meal Name         | Scheduled Time         |
      | Chicken Alfredo   | 2025-05-05T14:40:00    |
      | Vegetable Stir Fry| 2025-05-05T14:45:00    |
      | Lamb Chops        | 2025-05-05T16:30:00    |

    When the current time is about "2025-05-05T13:45:00"
    Then the chef should receive a notification for "Chicken Alfredo"
    And the chef should receive a notification for "Vegetable Stir Fry"
    And no notification should be sent for "Lamb Chops"
