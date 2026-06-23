Feature: Deleting an ad
  Scenario: Successful deleting an ad
    Given User is logged in
    And Ad is created
    When User opens ad card
    And User clicks Delete button
    Then Ad should be deleted