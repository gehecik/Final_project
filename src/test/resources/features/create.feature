Feature: Creating an ad
  Scenario Outline: Successful creating an ad without image
    Given User is logged in
    And User clicks Create an ad button
    When User opens ad form
    And User enters data "<category>" "<condition>" "<city>"
    And User clicks Submit button
    Then Ad should be created
    Examples:
      | category      | condition   | city            |
      | Авто          | Новый       | Санкт-Петербург |
      | Книги         | Б/У         | Москва          |
      | Садоводство   | Новый       | Новосибирск     |
      | Хобби         | Б/У         | Екатеринбург    |
      | Технологии    | Новый       | Нижний Новгород |
      | Авто          | Б/У         | Казань          |

  Scenario: Successful creating an ad
    Given User is logged in
    And User clicks Create an ad button
    When User opens ad form
    And User enters data with three image
    And User clicks Submit button
    Then Ad should be created

  Scenario Outline: Successful creating an ad with one image
    Given User is logged in
    And User clicks Create an ad button
    When User opens ad form
    And User enters data with one "<image>"
    And User clicks Submit button
    Then Ad should be created
    Examples:
      | image    |
      | img1     |
      | img2     |
      | img3     |

  Scenario: Successful creating default ad
    Given User is logged in
    And User clicks Create an ad button
    When User opens ad form
    And User clicks Submit button
    Then Ad should be created

  Scenario: Creating ad without login
    Given User opens home page
    When User clicks Create an ad button without login
    Then User should be see creating error

