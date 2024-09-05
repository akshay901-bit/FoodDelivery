Feature: User Management API Testing

Scenario: Update User Profile
  Given User is registered
  When User updates the profile with valid data
  Then The response status code should be 200
  And The username should be "john_updated"
  And The email should be "john_updated@example.com"