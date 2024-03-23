Feature: Select Vendors

Background: vendors
  Given date is '15/7/2024' and time is'6-8'

  Scenario Outline: successfully add vendors
    When user choose vendors  '<type>'
    Then book vendor to the event
    Examples:
      | type       |
      |decoration  |
      |DJ          |
      |photographer|
