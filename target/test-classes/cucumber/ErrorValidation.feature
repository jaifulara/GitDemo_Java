
@tag
Feature: Login Error Message Validation
  I want to use this template for my feature file

@ErrorValidation
  Scenario Outline: Positive Test of Submitting the Order
    Given I have landed on Ecommerce Page
    When  Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed on login

    Examples: 
      | name                    | password      |  productName      |
      | rahulshetty@gmail.com   | Iamking@0   |  ZARA COAT 3      |
