Feature: Login functionality

  Scenario: Invalid email format without @ symbol
    Given I am on the login page
    When I enter an email address without @ symbol
    And I enter a valid password
    And I click the Sign In button
    Then I should see an error message indicating an invalid email format

  Scenario Outline: Login with various invalid credentials
    Given I am on the login page
    When I enter an email "<email>"
    And I enter password "<password>"
    And I click the Sign In button
    Then I should see an error message indicating incorrect login credentials

    Examples:
      | email              | password    |
      | test123@test.com  | wrongpass   |
#      | wrong@mail.com    | 123456      |
#      | invalid@user.com  | password123 |
