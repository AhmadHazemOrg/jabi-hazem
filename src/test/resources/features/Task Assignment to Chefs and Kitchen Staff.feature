Feature: Task Assignment to Chefs and Kitchen Staff

  As a kitchen manager,
  I want to assign tasks to chefs based on their workload and expertise,
  so that I can ensure balanced workloads and efficiency.

  Scenario: Assign task to available chef with matching expertise
    Given a list of chefs with their current workloads and expertise
    When the kitchen manager assigns a task requiring "Grill" expertise
    Then the system should assign the task to the available chef with "Grill" expertise and the least workload

  Scenario: Prevent overloading a chef
    Given a chef has already reached their maximum workload
    When the kitchen manager tries to assign another task to that chef
    Then the system should display a warning and suggest another available chef

  Scenario: Notify chef about assigned task
    Given a task has been assigned to a chef
    When the assignment is confirmed
    Then the chef should receive a notification with task details and deadline