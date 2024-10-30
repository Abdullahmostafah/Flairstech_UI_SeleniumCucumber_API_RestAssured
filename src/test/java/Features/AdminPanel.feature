@Sanity
Feature: Admin Panel User Management

  Scenario Outline: Add, Verify, and Delete a User
    Given I am on the login page
    When I log in with username "<username>" and password "<password>"
    Then I navigate to the Admin tab
    And I note the initial record count
    When I add a new user with username "<new username>" and role "<role name>"
    Then the record count should increase by 1
    When I search for "<new username>"
    And I delete the user "<new username>"
    Then the record count should decrease by 1


    Examples:
      | username | password | new username | role name |
      | Admin    | admin123 | testuser     | Admin     |