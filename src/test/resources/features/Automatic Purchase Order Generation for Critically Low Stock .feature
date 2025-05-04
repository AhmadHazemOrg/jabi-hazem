Feature: Automatic Purchase Order Generation for Critically Low Stock

  As a system,
  I want to automatically generate purchase orders when stock levels are critically low,
  so that supplies are replenished without manual intervention.

  Scenario: Generate purchase order for a critically low ingredient
    Given the stock level of "Milk" is below the critical threshold
    When the system detects the low level
    Then it should automatically create a purchase order for "Milk" and send it to the default supplier

  Scenario: Notify kitchen manager of generated order
    Given the system has generated a purchase order for "Eggs"
    When the order is sent to the supplier
    Then the kitchen manager should receive a confirmation notification with order details

  Scenario: Prevent duplicate orders for the same ingredient
    Given a purchase order for "Butter" has already been generated and is pending
    When the stock level remains critically low
    Then the system should not generate another order until the existing one is fulfilled or canceled