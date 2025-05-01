
Feature: User Login

  Scenario Outline: Customer logs in successfully
    Given the Customer is not logged in
    And the Customer username is "<CustomerUsername>"
    And the Customer password is "<CustomerPassword>"
    Then the login should succeed

    Examples:
      | CustomerUsername  | CustomerPassword |
      | hazem mahamdeh    | 2004             |
      | saleh simreen     | 1980             |

  Scenario Outline: Admin logs in successfully
    Given the Admin is not logged in
    And the Admin username is "<AdminUsername>"
    And the Admin password is "<AdminPassword>"
    Then the login should succeed

    Examples:
      | AdminUsername     | AdminPassword    |
      | ahmad saif       | 2005        |

  Scenario Outline: Chef logs in successfully
    Given the Chef is not logged in
    And the Chef username is "<ChefUsername>"
    And the Chef password is "<ChefPassword>"
    Then the login should succeed

    Examples:
      | ChefUsername      | ChefPassword     |
      | yahya jara      |   2006    |

  Scenario Outline: Chef Manager logs in successfully
    Given the Chef Manager is not logged in
    And the Chef Manager username is "<ChefManagerUsername>"
    And the Chef Manager password is "<ChefManagerPassword>"
    Then the login should succeed

    Examples:
      | ChefManagerUsername | ChefManagerPassword |
      | ahmad yasir        | 2007        |


  Scenario Outline: Login fails due to incorrect username
    Given the <Role> is not logged in
    And the <Role> username is "<IncorrectUsername>"
    And the <Role> password is "<Password>"
    Then the login should fail  "User not found" message

    Examples:
      | Role           | IncorrectUsername | Password     |
      | Customer       | unknown_customer  | 2004         |
      | Admin          | unknown_admin     | 2005         |
      | Chef           | unknown_chef      | 2006         |
      | Chef Manager   | unknown_manager   | 2007         |

  Scenario Outline: Login fails due to incorrect password
    Given the <Role> is not logged in
    And the <Role> username is "<Username>"
    And the <Role> password is "<IncorrectPassword>"
    Then the login should fail with an with "Incorrect password" message

    Examples:
      | Role           | Username         | IncorrectPassword |
      | Customer       | hazem mahamdeh   | wrongPass         |
      | Admin          | ahmad saif       | wrongAdmin        |
      | Chef           | yahya jara      | wrongChef         |
      | Chef Manager   | ahmad yasir      | wrongManager      |
