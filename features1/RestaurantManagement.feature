Feature: Restaurant Management

Scenario: Add a new restaurant
  Given I have restaurant details with name "Tasty Bites", address "123 Flavor St", cuisine type "Italian", and contact information "555-6789"
  When I send a POST request to "/restaurants"
  Then the response status code should be 201
  And the response should contain the restaurant name "Tasty Bites"

Scenario: Retrieve a specific restaurant
  Given I have the restaurant ID "1"
  When I send a GET request to "/restaurants/1"
  Then the response status code should be 200
  And the response should contain the restaurant name "Tasty Bites"
