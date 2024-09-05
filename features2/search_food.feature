Feature: Search food by name
  As a user
  I want to search for food items by name
  So that I can find the food I want

  Scenario: User searches for food by name successfully
    Given the food item "Pasta" exists in the system
    When I search for food by name "Pasta"
    Then I should receive a list containing "Pasta"
    And the response status should be 200