Feature: Store and retrieve customer order history for analysis

  Scenario: System stores customer order history
    Given a customer places a new meal order
    When the order is confirmed and completed
    Then the system should save the order details in the customer's history

  Scenario: System administrator retrieves order history for analysis
    Given a system administrator is logged into the system
    When the administrator accesses the order analytics section
    Then the system should display customer order trends and history

  Scenario: System generates reports for service improvement
    Given the system has stored customer order data
    When the system administrator requests a trends report
    Then the system should generate an analysis of popular meals and customer preferences