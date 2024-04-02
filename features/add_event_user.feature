
Feature: add event by user
  Background: user
    Given username is 'Hala@user.com'
  Scenario Outline:add succeed

    When Date and Time are available '<Date>''<Time>'
    Then add event to waiting list
    Examples:
      | Date    | Time |
      |15/7/2024|8-10  |


  Scenario Outline: add failed
    When Date or Time is unavailable or wrong '<Date>''<Time>'
    Then failed to add event
    Examples:
      | Date     | Time |
      |15/07/2002|8-10  |
      |11/6/2025 |11-13 |
      |16/9/2024 |7-9   |
