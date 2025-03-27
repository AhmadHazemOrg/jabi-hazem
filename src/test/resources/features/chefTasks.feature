Feature: Receive notifications for assigned cooking tasks

  Scenario: Chef receives a new task notification
    Given a chef is logged into the system
    And the chef has been assigned a new cooking task
    When the system updates the chef’s task list
    Then the system should send a notification to the chef about the new task

  Scenario: Chef receives priority task notifications
    Given a chef is logged into the system
    And a high-priority order has been assigned to them
    When the system updates the chef’s task list
    Then the system should mark the order as high-priority
    And the system should notify the chef with an urgent alert

  Scenario: Chef acknowledges task notifications
    Given a chef has received a new cooking task notification
    When the chef opens the notification
    Then the system should mark the task as acknowledged
    And the kitchen manager should be able to see the acknowledgment status
