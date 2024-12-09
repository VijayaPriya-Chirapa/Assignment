Feature: User Management

  Scenario: Add user
    Given I have a new user
    When I add the user
    Then the user should be added successfully
