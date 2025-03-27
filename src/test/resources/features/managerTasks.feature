Feature: Assign tasks to chefs based on workload and expertise

  Scenario: Kitchen manager assigns tasks to chefs manually
    Given a kitchen manager is logged into the system
    When the kitchen manager assigns a cooking task to a chef
    Then the system should assign the task based on the chef’s expertise and availability
    And the system should update the chef's task queue

  Scenario: System suggests chef assignments based on workload
    Given multiple chefs are available in the kitchen
    And new meal orders are received
    When the system processes the workload distribution
    Then the system should assign tasks to chefs based on their current workload
    And the system should prioritize available chefs with relevant expertise

  Scenario: Kitchen manager reviews and adjusts assignments
    Given a kitchen manager is reviewing task assignments
    When the manager reassigns a task to a different chef
    Then the system should update the chef’s task queue accordingly
