Feature: Test the login functionality on CURA Healthcare Service

  Background:
    Given A user is on the login page

  Scenario: The user should be able to login with correct username and correct password
    When The user enters correct username
      And The user enters correct password
    Then The user sees the welcome page
