Feature: Change an ad
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
#  Scenario: Successful changing ad with deleting image
#    Given User is logged in
#    And Ad is created
#    When User clicks edit button
#    And User changes fields and deleting one of the "img1"
#    Then Ad should be changed and the "img1" is deleted