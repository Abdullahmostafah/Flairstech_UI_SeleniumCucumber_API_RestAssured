Feature: Admin Panel User Management

  Scenario: Add, Verify, and Delete a User
    Given I am on the login page
    When I log in with username "Admin" and password "admin123"
    Then I navigate to the Admin tab
    And I note the initial record count
    When I add a new user with username "testuser" and role "Admin"
    Then the record count should increase by 1
    When I search for "testuser"
    And I delete the user "testuser"
    Then the record count should decrease by 1