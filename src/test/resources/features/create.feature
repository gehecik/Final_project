Feature: Creating an ad
  Scenario: Successful creating an ad
    Given User is logged in
    And User clicks Create an ad button
    When User opens ad form
    And User enters data
    And User clicks Submit button
    Then Ad should be created