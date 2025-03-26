Feature: Assign tasks to chefs and kitchen staff

  Scenario: Kitchen manager assigns tasks to chefs
    Given a kitchen manager is logged into the system
    And there are pending cooking tasks in the system
    When the kitchen manager assigns a task to a chef
    Then the system should update the task assignment
    And the system should notify the assigned chef

  Scenario: Chef receives a notification for an assigned task
    Given a chef is logged into the system
    And a task has been assigned to the chef
    When the chef accesses their task list
    Then the system should display the assigned tasks

  Scenario: Chef marks a task as completed
    Given a chef has an assigned task
    When the chef marks the task as completed
    Then the system should update the task status to "Completed"
    And the kitchen manager should be notified of the completion
