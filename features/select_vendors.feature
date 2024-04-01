
Feature: Select Vendors

  Background: vendors
    Given  date is '15/7/2024' and time is'6-8'

  Scenario Outline: successfully add vendors
    When user choose vendors  '<type>''<email>'
    Then book vendor to the event
    Examples:
      | type       |email|
      |decoration  |decoration@serviceprovider|
      |DJ          |DJ@serviceprovider.com    |
      |photographer|photographer@serviceprovider.com|
