Feature: Test the login functionality on CURA Healthcare Service

  Scenario Outline: The user should be able to login with username and password
    Given User is on the login page
    When The user enters username <username>
      And The user enters password <password>
    Then The user sees the Welcome page

    Examples:
      | username | password |
      | John Doe | ThisIsNotAPassword |
      | John DoeDoe | ThisIsAPassword |
      | John DoeDo | ThisIsAPass |
