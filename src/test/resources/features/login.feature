Feature: User login
  Scenario: Successful login
    Given User exists
    And User opens home page
    When User opens login form
    And User enters email and password
    And User clicks Enter button
    Then User should be authorized

  Scenario: Successful login from Create Ad button
    Given User exists
    And User opens home page
    When User clicks Create an ad button without login
    And User enters email and password
    And User clicks Enter button
    Then User should be authorized

  Scenario: User login with wrong password
    Given User exists
    And User opens home page
    When User opens login form
    And User enters email and wrong password
    And User clicks Enter button and stay
    Then User should see login error

  Scenario: User login with empty password
    Given User exists
    And User opens home page
    When User opens login form
    And User enters email only
    And User clicks Enter button and stay
    Then User should see login error

  Scenario: User login with non-exist email
    Given User opens home page
    When User opens login form
    And User enters wrong email
    And User clicks Enter button and stay
    Then User should see login error