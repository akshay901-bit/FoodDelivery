Feature: Place a new order
  As a user
  I want to place a new order
  So that I can get food delivered

  Scenario: User places a new order successfully
    Given the following user and restaurant exist:
      | userId | restaurantId |
      | 1      | 1            |
    When I place a new order with the following details:
      | foodItems        | totalPrice |
      | Pasta, Garlic Bread | 25.00f     |
    Then the order should be placed successfully
    And the response status should be 201
    And the response should contain the order details
