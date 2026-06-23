Feature: Change an ad
  @cleanupAd
  Scenario Outline: Successful changing ad with deleting image
    Given User is logged in
    And Ad is created
    When User clicks edit button
    And User changes fields and deleting one of the "<image>"
    Then Ad should be changed and the "<image>" is deleted
    Examples:
      | image    |
      | img1     |
      | img2     |
      | img3     |

  @cleanupAd
  Scenario Outline: Successful change of image in ad
    Given User is logged in
    And Ad is created
    When User clicks edit button from ad card
    And User changes one of the "<image>"
    Then "<image>" of the ad should be changed
    Examples:
      | image    |
      | img1     |
      | img2     |
      | img3     |