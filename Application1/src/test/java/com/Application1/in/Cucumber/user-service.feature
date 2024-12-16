Feature: User Service Management

  Scenario Outline: Add user
    Given I have a new user with name "<name>", email "<email>", and password "<password>"
    When I add the user
    Then the user should be added successfully with name "<name>" and email "<email>"

    Examples:
      | name         | email                   | password          |
      | John         | john.doe@example.com     | jobhcsjn$%        |
      | Alice        | alice.smith@example.com  | alice123$%        |



   # New scenario for getting a user by ID
  Scenario: Get user by ID
    Given a user exists with ID <id>
    When I retrieve the user by ID
    Then I should get a user with name "<name>" and email "<email>"

    Examples:
      | id  | name     | email                   |
      | 1   | John     | john.doe@example.com     |
      | 2   | Alice    | alice.smith@example.com  |
      
      
   #New scenario for getting all the users
  Scenario: Get all users
    Given there are users in the system name "<name>", email "<email>", and password "<password>"
    When I retrieve the list of users
    Then I should get a list of users with name "<name>" and email "<email>"

    Examples:
     | name    | email                    |password|
     | John    | john.doe@example.com      |jajahj#$%|
     | Alice   | alice.smith@example.com   |kihhjsj333|
     | Bob     | bob.jones@example.com     |wevr433ko|