
Feature: Chef Notification for Assigned Cooking Tasks

  As a chef,
  I want to receive notifications about my assigned cooking tasks,
  so that I can prepare meals on time.

  Scenario: Receive notification when a new task is assigned
    Given the kitchen manager assigns a new cooking task to a chef
    When the assignment is completed
    Then the chef should receive a notification with task name, ingredients, and start time

  Scenario: Receive reminder before task deadline
    Given a chef has a task scheduled to start in 15 minutes
    When the current time is 15 minutes before the task
    Then the system should send a reminder notification to the chef

  Scenario: Notify chef of task changes
    Given a cooking task assigned to a chef has been modified
    When the task details are updated
    Then the chef should receive a notification describing the changes