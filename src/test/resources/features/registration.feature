Feature: Registration
  Scenario: Successful registration
    Given User opens home page
    When User opens registration form
    And User enters valid registration data
    And User clicks Create Account button
    Then User should be authorized

  Scenario: Registration with exist user
    Given User exists
    And User opens home page
    When User opens registration form
    And User enters valid registration data
    And User clicks Create Account button
    Then User should see registration error

  Scenario: Registration without password
    Given User opens home page
    When User opens registration form
    And User enters email only
    And User clicks Create Account button
    Then User should be authorized

  Scenario: Registration without repeat password
    Given User opens home page
    When User opens registration form
    And User enters email and password
    And User clicks Enter button for registration and stay
    Then User should see registration error the passwords are different

  Scenario: Registration with wrong repeat password
    Given User opens home page
    When User opens registration form
    And User enters registration data with wrong repeating password
    And User clicks Enter button for registration and stay
    Then User should see registration error the passwords are different

