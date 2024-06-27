@activity4

Feature: Data driven test without Example

  Scenario:Test Login functionality
    Given user is on Login page
    When User enters "admin" and "password"
    Then Verify the confirmation message
    And close the browser