Feature: Manage Dietary Preferences and Allergies

  Scenario Outline: Customer sets preferences and allergies
    Given the Customer "<username>" and "<password>" is logged in
    When the Customer sets dietary preferences to "<preferences>"
    And the Customer sets allergies to "<allergies>"
    Then the system should store the preferences and allergies successfully

    Examples:
      | username         | password | preferences | allergies |
      | hazem mahamdeh   | 2004     | Vegan       | Gluten    |

  Scenario Outline: Recommend meals that match customer preferences
    Given the Customer "<username>" and "<password>" is logged in
    And the Customer has preferences "<preferences>" and allergies "<allergies>"
    When the system recommends meals
    Then should match "<preferences>"

    Examples:
      | username         | password | preferences | allergies |
      | hazem mahamdeh   | 2004     | Vegan       | Gluten    |

  Scenario Outline: Do not recommend meals that conflict with allergies
    Given the Customer "<username>" and "<password>" is logged in
    And the Customer has preferences "<preferences>" and allergies "<allergies>"
    When the system recommends meals
    Then the system should exclude meals that contain "<allergies>"

    Examples:
      | username         | password | preferences | allergies |
      | hazem mahamdeh   | 2004     | Vegan       | Gluten    |
