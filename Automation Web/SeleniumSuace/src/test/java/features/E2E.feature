Feature: E2E Testing for SauceDemo

  Scenario: Successful login and checkout process
    Given I am on the login page
    When I enter valid username and password
    Then I should see the inventory page
    When I add the first and last items to the cart
    When I Click on Cart Button
    Then I should see 2 items in the cart
    When I proceed to checkout
    And I enter checkout information
    Then I should see the order summary
    When I confirm my order
    Then I should see the order confirmation message
    When I log out
